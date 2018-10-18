package com.davita.hackathon.meaningfulalerts.clinicalbus.repository;

import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.AlertDataCategory;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.AlertDataType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlertDataTypeRepository extends CrudRepository<AlertDataType, Long> {

    @Query(value = "select rc from AlertDataType rc " +
            "where rc.name = :name  ")
    Optional<AlertDataType> getAlertDataTypeByName(@Param("name") final String name);

}
