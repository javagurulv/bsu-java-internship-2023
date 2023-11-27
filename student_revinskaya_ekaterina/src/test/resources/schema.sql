
CREATE TABLE classifiers (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  description VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_classifiers_title ON classifiers(title);

CREATE TABLE classifier_values (
  id BIGINT NOT NULL AUTO_INCREMENT,
  classifier_id BIGINT NOT NULL,
  ic VARCHAR(200) NOT NULL,
  description VARCHAR(500) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE classifier_values
ADD FOREIGN KEY (classifier_id) REFERENCES classifiers(id);

CREATE UNIQUE INDEX ix_classifier_values_ic
ON classifier_values(ic);

create table if not exists country_default_day_rate
(id bigint not null auto_increment,
country_ic varchar(200) not null,
default_day_rate numeric(10,2) not null,
primary key(id)
);
create unique index ix_country_default_day_rate_country_ic
on country_default_day_rate(country_ic);

create table if not exists age_coefficient
(
id bigint not null auto_increment,
age_from int not null,
age_to int not null,
coefficient numeric(2,1) not null,
primary key(id)
);
CREATE UNIQUE INDEX ix_age_coefficient_age_from
ON age_coefficient (age_from);
CREATE UNIQUE INDEX ix_age_coefficient_age_to
ON age_coefficient (age_to);

create table if not exists medical_risk_limit_level
(
id bigint not null auto_increment,
medical_risk_limit_level_ic varchar(200) not null,
coefficient numeric(10,1) not null,
primary key(id)
);
CREATE UNIQUE INDEX ix_medical_risk_limit_level_ic
ON medical_risk_limit_level (medical_risk_limit_level_ic);