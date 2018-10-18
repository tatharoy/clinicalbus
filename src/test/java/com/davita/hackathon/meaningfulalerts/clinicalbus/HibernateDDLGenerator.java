
package com.davita.hackathon.meaningfulalerts.clinicalbus;

import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.AlertDataType;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.Clinician;
import com.davita.hackathon.meaningfulalerts.clinicalbus.entity.Patient;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;


/**
 * Hibernate DDL generator.
 */
public final class HibernateDDLGenerator {
    /**
     * Constructor.
     */
    private HibernateDDLGenerator() {

    }

    /**
     * main for running generator.
     *
     * @param args - Doesn't take any
     */
    public static void main(final String[] args) {

        new HibernateDDLGenerator().execute(new H2Dialect(), Clinician.class, Patient.class, AlertDataType.class);
    }

    /**
     * Method to create the DDL.
     *
     * @param dialect - DB dialect
     * @param classes - Classes to generate DDLs for
     */
    private void execute(final Dialect dialect, final Class<?>... classes) {
    	
    	 final MetadataSources metadata = new MetadataSources(
                 new StandardServiceRegistryBuilder()
                         .applySetting("hibernate.dialect", H2Dialect.class)
                         .build());
       

     
        for (final Class<?> entityClass : classes) {
        	metadata.addAnnotatedClass(entityClass);
        }
        
        /*final SchemaExport schemaExport =  new SchemaExport((MetadataImplementor)metadata.buildMetadata());

       *//* final SchemaExport schemaExport = new SchemaExport(
        		 metadata.buildMetadata()
        		);*//*
        schemaExport.setFormat(true);
        schemaExport.setDelimiter(";");
        schemaExport.setOutputFile(String.format("%s_%s.%s ", new Object[] { "ddl", "cms".toLowerCase(), "sql" }));
        final boolean consolePrint = true;
        final boolean exportInDatabase = false;
        schemaExport.create(consolePrint, exportInDatabase);*/
    }


}