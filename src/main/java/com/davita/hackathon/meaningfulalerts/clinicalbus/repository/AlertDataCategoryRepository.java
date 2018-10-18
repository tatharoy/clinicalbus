package com.davita.hackathon.meaningfulalerts.clinicalbus.repository;

import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.AlertDataCategory;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.Clinician;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertDataCategoryRepository extends CrudRepository<AlertDataCategory, Long> {
}
