package com.davita.hackathon.meaningfulalerts.clinicalbus.service;

import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.AlertDataCategory;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.AlertDataType;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.Clinician;
import com.davita.hackathon.meaningfulalerts.clinicalbus.repository.AlertDataCategoryRepository;
import com.davita.hackathon.meaningfulalerts.clinicalbus.repository.ClinicianRepository;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClinicianService {

    @Autowired
    private ClinicianRepository clinicianRepository;

    @Autowired
    private AlertDataCategoryRepository alertDataCategoryRepository;

    public List<Clinician> getAllClinicians(){
        return clinicianRepository.fetchClinicians();
    }

    public List<AlertDataCategory> getSubscibedAlerts(Long clinicinId){
        return clinicianRepository.fetchSubscribedAlerts(clinicinId);
    }

    public ResponseEntity addAlertCategory(Long id, Long alertCategoryId){
        Optional<AlertDataCategory> alertData =  alertDataCategoryRepository.findById(alertCategoryId);
        Optional<Clinician> clinician = clinicianRepository.findById(id);
        if(clinician.isPresent()){
            if(alertData.isPresent()){
                Clinician clinicianObj  = clinician.get();
                Set<AlertDataCategory> clinicianalerts =  clinicianObj.getSubscribedAlerts();
                clinicianalerts.add(alertData.get());
                clinicianRepository.save(clinicianObj);
                return new ResponseEntity(HttpStatus.CREATED);
            }else{
                return new ResponseEntity("alert id "+alertCategoryId+" does not exists",HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity("clinician id "+id+" does not exists",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity removeAlertCategory(Long id, Long alertCategoryId){
        Optional<AlertDataCategory> alertData =  alertDataCategoryRepository.findById(alertCategoryId);
        Optional<Clinician> clinician = clinicianRepository.findById(id);
        if(clinician.isPresent()){
            if(alertData.isPresent()){
                Clinician clinicianObj  = clinician.get();
                Set<AlertDataCategory> clinicianalerts =  clinicianObj.getSubscribedAlerts();
                clinicianalerts.remove(alertData.get());
                clinicianRepository.save(clinicianObj);
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity("alert id "+alertCategoryId+" does not exists",HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity("clinician id "+id+" does not exists",HttpStatus.BAD_REQUEST);
        }
    }


}
