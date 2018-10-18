package com.davita.hackathon.meaningfulalerts.clinicalbus.repository;


import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
}
