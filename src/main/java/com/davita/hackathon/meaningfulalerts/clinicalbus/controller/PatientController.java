package com.davita.hackathon.meaningfulalerts.clinicalbus.controller;

import com.davita.hackathon.meaningfulalerts.clinicalbus.service.PatientService;
import com.davita.hackathon.meaningfulalerts.clinicalbus.to.ClinicalData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
@Slf4j
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/{id}/data")
    public ResponseEntity addClinicalData(@PathVariable Long id, @RequestBody ClinicalData clinicalData) {
        log.info("data coming from raspberry "+clinicalData);
        return patientService.process(id,clinicalData);

    }
}
