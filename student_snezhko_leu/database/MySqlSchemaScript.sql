CREATE SCHEMA IF NOT EXISTS `java-real-practice-insurance` DEFAULT CHARACTER SET utf8;
USE `java-real-practice-insurance`;

CREATE TABLE IF NOT EXISTS classifiers (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL UNIQUE,
  description VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_classifiers_title ON classifiers(title);

CREATE TABLE IF NOT EXISTS classifier_values (
  id BIGINT NOT NULL AUTO_INCREMENT,
  classifier_id BIGINT NOT NULL,
  ic VARCHAR(200) NOT NULL UNIQUE,
  description VARCHAR(500) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE classifier_values
ADD FOREIGN KEY (classifier_id) REFERENCES classifiers(id);

CREATE UNIQUE INDEX ix_classifier_values_ic
ON classifier_values(ic);

CREATE TABLE IF NOT EXISTS COUNTRY_DEFAULT_DAY_RATE (
    id BIGINT NOT NULL AUTO_INCREMENT,
    country_ic  VARCHAR(200) NOT NULL UNIQUE,
    country_default_day_rate DECIMAL(10,2) NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE COUNTRY_DEFAULT_DAY_RATE
ADD FOREIGN KEY (country_ic) REFERENCES classifier_values(ic);

CREATE TABLE IF NOT EXISTS AGE_COEFFICIENT (
    id BIGINT NOT NULL AUTO_INCREMENT,
    age_from INT NOT NULL UNIQUE,
    age_to INT NOT NULL UNIQUE,
    coefficient DECIMAL(10,2) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS MEDICAL_RISK_LIMIT_LEVEL (
    id BIGINT NOT NULL AUTO_INCREMENT,
    MEDICAL_RISK_LIMIT_LEVEL_IC VARCHAR(200) NOT NULL UNIQUE,
    COEFFICIENT DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE MEDICAL_RISK_LIMIT_LEVEL
ADD FOREIGN KEY (MEDICAL_RISK_LIMIT_LEVEL_IC) REFERENCES classifier_values(ic);