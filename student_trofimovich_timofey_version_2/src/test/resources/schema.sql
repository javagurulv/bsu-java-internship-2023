
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

CREATE TABLE country_default_day_rate
(
    id                       BIGINT       NOT NULL AUTO_INCREMENT,
    country_ic               VARCHAR(200) NOT NULL,
    country_default_day_rate DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
    );


CREATE UNIQUE INDEX ix_country_default_day_rate
    ON country_default_day_rate (country_ic);

CREATE TABLE age_coefficient
(
     id         BIGINT         NOT NULL AUTO_INCREMENT,
    age_from    INT            NOT NULL,
    age_to      INT            NOT NULL,
    coefficient DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE INDEX ix_age_coefficient_age_from_age_to
ON age_coefficient(age_from, age_to);

CREATE TABLE medical_risk_limit_level
(
    id                          BIGINT         NOT NULL AUTO_INCREMENT,
    medical_risk_limit_level_ic VARCHAR(200)   NOT NULL,
    coefficient                 DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
    );


CREATE UNIQUE INDEX ix_medical_risk_limit_level
    ON `medical_risk_limit_level` (medical_risk_limit_level_ic);

CREATE TABLE persons
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    person_code VARCHAR(200) NOT NULL,
    birth_date TIMESTAMP NOT NULL ,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_unique_persons on persons(first_name, last_name, person_code);

CREATE TABLE agreements
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  date_from TIMESTAMP NOT NULL ,
  date_to TIMESTAMP NOT NULL ,
  country VARCHAR(100) NOT NULL ,
  premium DECIMAL(10, 2) NOT NULL ,
  uuid VARCHAR(100) UNIQUE NOT NULL ,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_unique_agreements_id
ON agreements(id);

CREATE UNIQUE INDEX ix_agreements_uuid
ON agreements(uuid);


CREATE TABLE selected_risks
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    agreement_id BIGINT NOT NULL,
    risk_ic VARCHAR(100) NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (agreement_id) REFERENCES agreements(id)
);

CREATE TABLE agreement_persons
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    agreement_id BIGINT NOT NULL ,
    person_id BIGINT NOT NULL ,
    medical_risk_limit_level VARCHAR(100) NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (agreement_id) REFERENCES agreements(id),
    FOREIGN KEY (person_id) REFERENCES persons(id)
);

CREATE TABLE agreement_person_risks
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    agreement_person_id BIGINT NOT NULL ,
    risk_ic VARCHAR(200) NOT NULL ,
    premium DECIMAL(10,2) NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (agreement_person_id) REFERENCES agreement_persons(id)
);

CREATE TABLE IF NOT EXISTS travel_cost_coefficient
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    travel_cost_from DECIMAL(10,2) NOT NULL ,
    travel_cost_to DECIMAL(10,2) NOT NULL ,
    coefficient DECIMAL(10,2) NOT NULL
);

ALTER TABLE age_coefficient
RENAME to travel_medical_age_coefficient;

ALTER TABLE country_default_day_rate
RENAME to travel_medical_country_default_day_rate;

ALTER TABLE medical_risk_limit_level
RENAME to travel_medical_risk_limit_level;

ALTER TABLE travel_cost_coefficient
RENAME TO travel_cancellation_trip_cost_coefficient;


CREATE TABLE travel_cancellation_age_coefficient
(
    id         BIGINT         NOT NULL AUTO_INCREMENT,
    age_from    INT            NOT NULL,
    age_to      INT            NOT NULL,
    coefficient DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE INDEX ix_travel_cancellation_age_coefficient_age_from_age_to
    ON travel_cancellation_age_coefficient(age_from, age_to);



CREATE TABLE travel_cancellation_country_safety_rating_coefficient
(
    id         BIGINT         NOT NULL AUTO_INCREMENT,
    country_ic    VARCHAR(100)            NOT NULL,
    coefficient DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_travel_cancellation_country_safety_rating_country_ic
ON travel_cancellation_country_safety_rating_coefficient(country_ic);




