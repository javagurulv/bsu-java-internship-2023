SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 1;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 1;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';


CREATE SCHEMA IF NOT EXISTS `java-real-practice-insurance` DEFAULT CHARACTER SET utf8;
USE `java-real-practice-insurance`;


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
    PRIMARY KEY (id)
);

ALTER TABLE agreements
ADD uuid VARCHAR(200) NOT NULL;

CREATE UNIQUE INDEX ix_unique_agreements_uuid
ON agreements(uuid);

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


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
