package com.davita.hackathon.meaningfulalerts.clinicalbus.controller;

import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.AlertDataCategory;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.AlertDataType;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.Clinician;
import com.davita.hackathon.meaningfulalerts.clinicalbus.service.ClinicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clinicians")
public class ClinicianRestController {

    @Autowired
    private ClinicianService clinicianService;


    @GetMapping()
    public List<Clinician> getClinicians() {
        return clinicianService.getAllClinicians();

    }

    @GetMapping("/{id}/subscribedAlerts")
    public List<AlertDataCategory> getSubscribedAlerts(@PathVariable Long id) {
        return clinicianService.getSubscibedAlerts(id);

    }

    @GetMapping("/{id}/subscribe/{alertCategoryId}")
    public ResponseEntity subscribedAlerts(@PathVariable Long id, @PathVariable Long alertCategoryId,@RequestParam(value = "action") final String action) {
        if(action.equalsIgnoreCase("add")){
            return addSubscribedAlerts(id,alertCategoryId);
        }else if(action.equalsIgnoreCase("delete")){
            return removeSubscribedAlerts(id,alertCategoryId);
        }
        return new ResponseEntity("action "+action+" is not supported", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/{id}/subscribe/{alertCategoryId}")
    public ResponseEntity addSubscribedAlerts(@PathVariable Long id,@PathVariable Long alertCategoryId) {
        return clinicianService.addAlertCategory(id,alertCategoryId);

    }

    @DeleteMapping("/{id}/subscribe/{alertCategoryId}")
    public ResponseEntity removeSubscribedAlerts(@PathVariable Long id,@PathVariable Long alertCategoryId) {
        return clinicianService.removeAlertCategory(id,alertCategoryId);

    }


}
