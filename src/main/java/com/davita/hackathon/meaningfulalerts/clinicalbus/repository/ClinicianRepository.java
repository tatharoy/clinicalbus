package com.davita.hackathon.meaningfulalerts.clinicalbus.repository;

import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.AlertDataCategory;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.Clinician;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicianRepository extends CrudRepository<Clinician, Long> {

    @Query(value = "select rc from Clinician rc")
    List<Clinician> fetchClinicians();

    @Query(value = "select rc.subscribedAlerts from Clinician rc " +
            "where rc.id = :clinicianId  ")
    List<AlertDataCategory> fetchSubscribedAlerts(@Param("clinicianId") final Long clinicianId);

}
