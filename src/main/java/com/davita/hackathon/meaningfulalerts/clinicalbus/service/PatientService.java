package com.davita.hackathon.meaningfulalerts.clinicalbus.service;

import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.AlertDataCategory;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.AlertDataType;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.Clinician;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.Patient;
import com.davita.hackathon.meaningfulalerts.clinicalbus.repository.AlertDataTypeRepository;
import com.davita.hackathon.meaningfulalerts.clinicalbus.repository.PatientRepository;
import com.davita.hackathon.meaningfulalerts.clinicalbus.to.ClinicalAlert;
import com.davita.hackathon.meaningfulalerts.clinicalbus.to.ClinicalData;
import com.davita.hackathon.meaningfulalerts.clinicalbus.util.RuleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Configuration
@Service
@Slf4j
public class PatientService {

    @Autowired
    private AlertDataTypeRepository alertDataTypeRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${meaningfulalerts.url}")
    protected String alertUrl;

    public ResponseEntity process(Long patientId, ClinicalData clinicalData){

      Optional<Patient> patientOptional = patientRepository.findById(patientId);


      Patient patient = null;

      if(patientOptional.isPresent()){
          patient  = patientOptional.get();
      }else{
          return new ResponseEntity("patient id "+patientId+" does not exists", HttpStatus.BAD_REQUEST);
      }

        Optional<AlertDataType> alertDataTypeOptional = alertDataTypeRepository.getAlertDataTypeByName(clinicalData.getName());

        AlertDataType alertDataType = null;
        if(alertDataTypeOptional.isPresent()){
            alertDataType  = alertDataTypeOptional.get();
        }else{
            return new ResponseEntity("alert name "+clinicalData+" does not exists", HttpStatus.BAD_REQUEST);
        }

        Set<Clinician> clinicians =  patient.getClinicians();

        for(Clinician clinician : clinicians){
            Set<AlertDataCategory> alertCategorySubscribed = clinician.getSubscribedAlerts();
            for(AlertDataCategory category : alertCategorySubscribed){

                if(category.equals(alertDataType.getAlertDataCategory())){
                    log.debug("clinician "+clinician.getName()+" subscribed alert category "+category.getName());

                    Optional<ClinicalAlert> alertToBeCreated = alertToBeCreated(alertDataType,clinicalData.getValue(),patient,clinician);
                    if(alertToBeCreated.isPresent()){
                        HttpEntity<ClinicalAlert> entity1 = new HttpEntity<>(alertToBeCreated.get());
                        URI uri1 = UriComponentsBuilder.fromHttpUrl(alertUrl).build().toUri();
                        ResponseEntity<ClinicalAlert> response1 = restTemplate.postForEntity(uri1,alertToBeCreated.get(),ClinicalAlert.class);
                        /*ResponseEntity<ClinicalAlert> response = restTemplate.exchange(alertUrl, HttpMethod.POST,
                                entity, ClinicalAlert.class);*/
                        log.info(" Alerts sent for clinical data" +clinicalData+" with response "+response1.getStatusCode());
                    }else{
                        log.info(" no alerts were sent for clinical data" +clinicalData);
                    }



                }
            }
        }

        return new ResponseEntity( HttpStatus.OK);
    }

    private Optional<ClinicalAlert> alertToBeCreated(AlertDataType alertDataType, String value, Patient patient, Clinician clinician){
        if(alertDataType.getAlertDataCategory().getName().equalsIgnoreCase("Urinalysis")){
            return urinalysisCheck(alertDataType,value,patient,clinician);
        }else if(alertDataType.getAlertDataCategory().getName().equalsIgnoreCase("Fluidwise")){
            return fluidwiseCheck(alertDataType,value,patient,clinician);
        }else{
            return labsCheck(alertDataType,value,patient,clinician);
        }
    }

    private Optional<ClinicalAlert> urinalysisCheck(AlertDataType alertDataType, String value, Patient patient, Clinician clinician){
     AlertDataCategory  category =  alertDataType.getAlertDataCategory();
     RuleCategory ruleCategory = alertDataType.getCategory();
        boolean createAlert = false;
        String output = null;
     if(ruleCategory.getName().equals("RangeAlert")){
         double maxlimit =  alertDataType.getMaxValidUnit();
         double minLimit =  alertDataType.getMinValidUnit();

         if(Double.valueOf(value)>maxlimit){
             createAlert = true;
             output = "High";
         }else if(Double.valueOf(value)<minLimit){
             createAlert = true;
             output = "Low";
         }

     }else if(ruleCategory.getName().equals("HigherRangeAlert")){
         double maxlimit =  alertDataType.getMaxValidUnit();
         if(Double.valueOf(value)>maxlimit){
             createAlert = true;
             output = "Positive";
         }
     }
     if(createAlert){
        return Optional.of(ClinicalAlert.builder().
                 patientName(patient.getLastName()+","+patient.getFirstName())
                 .alertCategory(category.getName())
                 .alertDate(new Date())
                 .alertName(alertDataType.getName())
                 .physicianId(clinician.getId())
                 .value(value+" "+(alertDataType.getUnit()!=null?alertDataType.getUnit():""))
                 .output(output).build());

     }
        return  Optional.empty();
    }

    private Optional<ClinicalAlert> fluidwiseCheck(AlertDataType alertDataType, String value, Patient patient, Clinician clinician){
        AlertDataCategory  category =  alertDataType.getAlertDataCategory();
        RuleCategory ruleCategory = alertDataType.getCategory();
        boolean createAlert = false;
        String output = null;
        if(ruleCategory.getName().equals("TrueAlert")){


            if("true".equalsIgnoreCase(value)){
                createAlert = true;
                output = "MVP";
            }
        }
        if(createAlert){
            return Optional.of(ClinicalAlert.builder().
                    patientName(patient.getLastName()+","+patient.getFirstName())
                    .alertCategory(category.getName())
                    .alertDate(new Date())
                    .alertName(alertDataType.getName())
                    .physicianId(clinician.getId())
                    .value(value)
                    .output(output).build());

        }
        return  Optional.empty();
    }

    private Optional<ClinicalAlert> labsCheck(AlertDataType alertDataType, String value, Patient patient, Clinician clinician){
        AlertDataCategory  category =  alertDataType.getAlertDataCategory();
        RuleCategory ruleCategory = alertDataType.getCategory();
        boolean createAlert = false;
        String output = null;
        if(ruleCategory.getName().equals("HigherRangeAlert")){
            double maxlimit =  alertDataType.getMaxValidUnit();
            if(Double.valueOf(value)>maxlimit){
                createAlert = true;
                output = "Positive";
            }
        }
        if(createAlert){
            return Optional.of(ClinicalAlert.builder().
                    patientName(patient.getLastName()+","+patient.getFirstName())
                    .alertCategory(category.getName())
                    .alertDate(new Date())
                    .alertName(alertDataType.getName())
                    .physicianId(clinician.getId())
                    .value(value+" "+alertDataType.getUnit())
                    .output(output).build());

        }
        return  Optional.empty();
    }


}
