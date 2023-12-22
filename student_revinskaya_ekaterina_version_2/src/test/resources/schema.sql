CREATE TABLE if not exists classifiers (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  description VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX if not exists ix_classifiers_title ON classifiers(title);

CREATE TABLE if not exists classifier_values (
  id BIGINT NOT NULL AUTO_INCREMENT,
  classifier_id BIGINT NOT NULL,
  ic VARCHAR(200) NOT NULL,
  description VARCHAR(500) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE classifier_values
ADD FOREIGN KEY (classifier_id) REFERENCES classifiers(id);

CREATE UNIQUE INDEX if not exists ix_classifier_values_ic
ON classifier_values(ic);

create table if not exists country_default_day_rate
(id bigint not null auto_increment,
country_ic varchar(200) not null,
default_day_rate numeric(10,2) not null,
primary key(id)
);
create unique index if not exists ix_country_default_day_rate_country_ic
on country_default_day_rate(country_ic);

create table if not exists age_coefficient
(
id bigint not null auto_increment,
age_from int not null,
age_to int not null,
coefficient numeric(2,1) not null,
primary key(id)
);
CREATE UNIQUE INDEX if not exists ix_age_coefficient_age_from
ON age_coefficient (age_from);
CREATE UNIQUE INDEX if not exists ix_age_coefficient_age_to
ON age_coefficient (age_to);

create table if not exists medical_risk_limit_level
(
id bigint not null auto_increment,
medical_risk_limit_level_ic varchar(200) not null,
coefficient numeric(10,1) not null,
primary key(id)
);
CREATE UNIQUE INDEX if not exists ix_medical_risk_limit_level_ic
ON medical_risk_limit_level (medical_risk_limit_level_ic);

create table if not exists persons
(
id bigint not null auto_increment,
first_name varchar(200) not null,
last_name varchar(200) not null,
personal_code varchar(200) not null,
birthday timestamp not null,
primary key(id)
);
CREATE UNIQUE INDEX if not exists ix_person
ON persons (personal_code);


create table if not exists agreements
(
id bigint not null auto_increment,
date_from timestamp not null,
date_to timestamp not null,
country varchar(200) not null,
premium decimal(10,2) not null,
primary key(id)
);

create table if not exists selected_risks
(
id bigint not null auto_increment,
agreement_id BIGINT not null,
risk_ic varchar(200) not null,
primary key(id)
);
ALTER TABLE selected_risks
ADD FOREIGN KEY (agreement_id) REFERENCES agreements(id);

CREATE UNIQUE INDEX if not exists ix_selected_risk_ic_agreement_id
ON selected_risks(agreement_id, risk_ic);

create table if not exists person_agreements
(
id bigint not null auto_increment,
person_id BIGINT not null,
agreement_id BIGINT not null,
medical_risk_limit_level varchar(200) not null,
primary key(id)
);

ALTER TABLE person_agreements
ADD FOREIGN KEY (person_id) REFERENCES persons(id);

ALTER TABLE person_agreements
ADD FOREIGN KEY (agreement_id) REFERENCES agreements(id);

CREATE UNIQUE INDEX if not exists ix_person_id_agreement_id
ON person_agreements(person_id, agreement_id);

create table if not exists person_agreement_risks
(
id bigint not null auto_increment,
person_agreement_id bigint not null ,
risk_ic varchar(200) not null,
premium decimal(10,2) not null,
primary key(id)
);
ALTER TABLE person_agreement_risks
ADD FOREIGN KEY (person_agreement_id) REFERENCES person_agreements(id);

CREATE UNIQUE INDEX if not exists ix_person_agreement_id_risk_ic
ON person_agreement_risks(person_agreement_id, risk_ic);