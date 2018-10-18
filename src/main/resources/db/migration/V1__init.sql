create sequence hibernate_sequence start with 1 increment by 1;

create table alert_data_category (
  alert_category_id bigint not null,
  alert_category_name varchar(255),
  primary key (alert_category_id)
);

create table alert_data_type (
  alert_data_id bigint not null,
  boolean_type varchar(255),
  rule_category varchar(255),
  alert_unit varchar(255),
  max_valid DOUBLE PRECISION,
  min_valid DOUBLE PRECISION,
  alert_data_name varchar(255),
  alert_category varchar(255) not null,
  primary key (alert_data_id)
);

create table clinician (
  clinician_id bigint not null,
  clinician_name varchar(255),
  clinician_type varchar(255),
  primary key (clinician_id)
);

create table clinician_alerts_category (
  clinician_id bigint not null,
  alert_category_id varchar(255) not null,
  primary key (clinician_id, alert_category_id)
);

create table patient (
  patient_id bigint not null,
  age integer, first_name varchar(255),
  gender varchar(255),
  last_name varchar(255),
  primary key (patient_id)
);

create table patient_clinicians (
  patient_id varchar(255) not null,
  clinician_id bigint not null,
  primary key (patient_id, clinician_id)
);

alter table alert_data_type add constraint FKl9tom6qm18p1xy6dgfb6nqfng foreign key (alert_category) references alert_data_category;

alter table clinician_alerts_category add constraint FKncgt7m7xc3kedf8yrgo0ychm9 foreign key (alert_category_id) references alert_data_category;

alter table clinician_alerts_category add constraint FKnlym964caqj09hd94s29s4p9p foreign key (clinician_id) references clinician;

alter table patient_clinicians add constraint FKo5d4hk08bc39hnj8i5shbm2ai foreign key (clinician_id) references clinician;

alter table patient_clinicians add constraint FK78u46ofs0hlhrr82qrkish5vv foreign key (patient_id) references patient;