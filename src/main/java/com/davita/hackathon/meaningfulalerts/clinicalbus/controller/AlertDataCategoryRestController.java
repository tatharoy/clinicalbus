package com.davita.hackathon.meaningfulalerts.clinicalbus.controller;

import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.AlertDataCategory;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.Clinician;
import com.davita.hackathon.meaningfulalerts.clinicalbus.repository.AlertDataCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/alertcategories")
public class AlertDataCategoryRestController {

    @Autowired
    private AlertDataCategoryRepository alertDataCategoryRepository;

    @GetMapping()
    public List<AlertDataCategory> getAllCategories() {
        List<AlertDataCategory> cltn = new ArrayList<AlertDataCategory>();

        alertDataCategoryRepository.findAll().forEach(cltn::add);

        return cltn;

    }
}

