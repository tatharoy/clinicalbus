-- insert alert category

INSERT INTO alert_data_category(alert_category_id, alert_category_name) VALUES (1, 'Urinalysis');
INSERT INTO alert_data_category(alert_category_id, alert_category_name) VALUES (2, 'Fluidwise');
INSERT INTO alert_data_category(alert_category_id, alert_category_name) VALUES (3, 'Labs');

-- insert alert data type

INSERT INTO alert_data_type(alert_data_id,alert_category,alert_data_name,rule_category,alert_unit, max_valid,min_valid,boolean_type) VALUES (1,1,'SpecificGravity','RangeAlert',null,1.035,1.001,null);
INSERT INTO alert_data_type(alert_data_id,alert_category,alert_data_name,rule_category,alert_unit, max_valid,min_valid,boolean_type) VALUES (2,1,'pH','RangeAlert',null,8.0,5.0,null);
INSERT INTO alert_data_type(alert_data_id,alert_category,alert_data_name,rule_category,alert_unit, max_valid,min_valid,boolean_type) VALUES (3,1,'Protein','HigherRangeAlert','mg/dL',150,null,null);
INSERT INTO alert_data_type(alert_data_id,alert_category,alert_data_name,rule_category,alert_unit, max_valid,min_valid,boolean_type) VALUES (4,1,'Ketones','HigherRangeAlert','mg/dL',40,null,null);
INSERT INTO alert_data_type(alert_data_id,alert_category,alert_data_name,rule_category,alert_unit, max_valid,min_valid,boolean_type) VALUES (5,1,'RBC','HigherRangeAlert','RBC',3,null,null);
INSERT INTO alert_data_type(alert_data_id,alert_category,alert_data_name,rule_category,alert_unit, max_valid,min_valid,boolean_type) VALUES (6,1,'Glucose','HigherRangeAlert','mg/dL',130,null,null);

INSERT INTO alert_data_type(alert_data_id,alert_category,alert_data_name,rule_category,alert_unit, max_valid,min_valid,boolean_type) VALUES (7,3,'Creatinine','HigherRangeAlert','mg/dL',1.2,0.0,null);
INSERT INTO alert_data_type(alert_data_id,alert_category,alert_data_name,rule_category,alert_unit, max_valid,min_valid,boolean_type) VALUES (8,3,'BUN','HigherRangeAlert','mg/dL',20,0.0,null);
INSERT INTO alert_data_type(alert_data_id,alert_category,alert_data_name,rule_category,alert_unit, max_valid,min_valid,boolean_type) VALUES (9,3,'UricAcid','HigherRangeAlert','mg/dL',7.0,0.0,null);

INSERT INTO alert_data_type(alert_data_id,alert_category,alert_data_name,rule_category,alert_unit, max_valid,min_valid,boolean_type) VALUES (10,2,'Fluidwise','TrueAlert',null,0.0,0.0,true);

-- insert clinician

INSERT INTO clinician(clinician_id, clinician_name, clinician_type) VALUES (1, 'Mary Smith','Physician');
INSERT INTO clinician(clinician_id, clinician_name, clinician_type) VALUES (2, 'John Doe', 'Nurse Practitioner');
INSERT INTO clinician(clinician_id, clinician_name, clinician_type) VALUES (3, 'Ricky Ponting', 'Dietician');


-- insert patients
INSERT INTO patient(patient_id, age, first_name,last_name,gender) VALUES (1,23,'Tom','Brady', 'Male');
INSERT INTO patient(patient_id, age, first_name,last_name,gender) VALUES (2,43,'Julia','Roberts', 'Female');
INSERT INTO patient(patient_id, age, first_name,last_name,gender) VALUES (3,70,'Robert','Redford', 'Male');
INSERT INTO patient(patient_id, age, first_name,last_name,gender) VALUES (4,18,'Kate','Johnson', 'Female');

-- insert patient_clinicians

INSERT INTO patient_clinicians(patient_id, clinician_id) VALUES (1,1);
INSERT INTO patient_clinicians(patient_id, clinician_id) VALUES (1,3);
INSERT INTO patient_clinicians(patient_id, clinician_id) VALUES (2,1);
INSERT INTO patient_clinicians(patient_id, clinician_id) VALUES (3,2);
INSERT INTO patient_clinicians(patient_id, clinician_id) VALUES (3,3);
INSERT INTO patient_clinicians(patient_id, clinician_id) VALUES (4,1);
INSERT INTO patient_clinicians(patient_id, clinician_id) VALUES (4,2);
INSERT INTO patient_clinicians(patient_id, clinician_id) VALUES (4,3);




