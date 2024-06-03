SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


CREATE SCHEMA IF NOT EXISTS `java-real-practice-insurance` DEFAULT CHARACTER SET utf8 ;
USE `java-real-practice-insurance` ;

CREATE TABLE IF NOT EXISTS `classifiers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

CREATE UNIQUE INDEX `ix_classifiers_title` ON `classifiers` (`title`);

CREATE TABLE IF NOT EXISTS `classifier_values` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `classifier_id` BIGINT NOT NULL,
  `ic` VARCHAR(200) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

ALTER TABLE `classifier_values`
ADD FOREIGN KEY (`classifier_id`) REFERENCES `classifiers`(`id`);

CREATE UNIQUE INDEX `ix_classifier_values_ic`
ON `classifier_values` (`ic`);


CREATE TABLE IF NOT EXISTS `country_default_day_rate` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `country_ic` VARCHAR(200) NOT NULL,
  `default_day_rate` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

CREATE UNIQUE INDEX `ix_country_default_day_rate_country_ic`
ON `country_default_day_rate` (`country_ic`);


CREATE TABLE IF NOT EXISTS `travel_medical_age_coefficient` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `age_from` INT NOT NULL,
  `age_to` INT NOT NULL,
  `coefficient` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;


CREATE TABLE IF NOT EXISTS `medical_risk_limit_level` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `medical_risk_limit_level_ic` VARCHAR(200) NOT NULL,
  `coefficient` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

CREATE UNIQUE INDEX `ix_medical_risk_limit_level_limit_level_ic`
ON `medical_risk_limit_level` (`medical_risk_limit_level_ic`);

CREATE TABLE IF NOT EXISTS persons (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    ic UUID NOT NULL UNIQUE,
    birth_date DATE NOT NULL,
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX ix_persons
ON persons (first_name, last_name, ic);

CREATE TABLE IF NOT EXISTS agreements (
    id BIGINT NOT NULL AUTO_INCREMENT,
    uuid UUID NOT NULL UNIQUE,
    date_from DATE not null,
    date_to DATE not null,
    country VARCHAR(30) not null,
    premium DECIMAL(19,2) not null,
    PRIMARY KEY(id)
);

CREATE TABLE selected_risks (
    id BIGINT NOT NULL AUTO_INCREMENT,
    risk_ic VARCHAR(200) NOT NULL,
    agreement BIGINT NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE selected_risks
ADD FOREIGN KEY(agreement) REFERENCES agreements(id);

CREATE TABLE IF NOT EXISTS agreement_persons(
    id BIGINT NOT NULL AUTO_INCREMENT,
    agreement BIGINT NOT NULL,
    person BIGINT NOT NULL,
    medical_risk_limit_level VARCHAR(200),
    premium DECIMAL(10,2) NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE agreement_persons
ADD FOREIGN KEY(agreement) REFERENCES agreements(id);

ALTER TABLE agreement_persons
ADD FOREIGN KEY(person) REFERENCES persons(id);

CREATE TABLE IF NOT EXISTS person_risks(
    id BIGINT NOT NULL AUTO_INCREMENT,
    risk_ic VARCHAR(200) NOT NULL,
    premium DECIMAL(10, 2) NOT NULL,
    person BIGINT NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE person_risks
ADD FOREIGN KEY(person) REFERENCES agreement_persons(id);

CREATE TABLE travel_cost_coefficient(
    id BIGINT NOT NULL AUTO_INCREMENT,
    cost_from DECIMAL(10,2) NOT NULL,
    cost_to DECIMAL(10,2) NOT NULL,
    cost_coefficient DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX ix_travel_cost_coefficient_from_and_to
ON travel_cost_coefficient(cost_from, cost_to);

ALTER TABLE travel_cost_coefficient
ADD CONSTRAINT tc_cost_from_must_be_less_than_cost_to CHECK(cost_from < cost_to);

CREATE TABLE travel_cancellation_age_coefficient(
    id BIGINT NOT NULL AUTO_INCREMENT,
    age_from INT NOT NULL,
    age_to INT NOT NULL,
    coefficient DECIMAL(10,2) NOT NULL,
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX ix_travel_cancellation_age_coefficient_from_and_to
ON travel_cancellation_age_coefficient(age_from, age_to);

ALTER TABLE travel_cancellation_age_coefficient
ADD CONSTRAINT tc_age_from_must_be_less_than_age_to CHECK(age_from < age_to);

CREATE TABLE travel_cancellation_country_safety_rating_coefficient
(
    id BIGINT AUTO_INCREMENT,
    country VARCHAR(30) NOT NULL UNIQUE,
    rating INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX ix_country_for_safety_rating_unique
ON travel_cancellation_country_safety_rating_coefficient(country);

ALTER TABLE travel_cancellation_country_safety_rating_coefficient
ADD CONSTRAINT tc_correct_safety_rating CHECK(rating > 0 and rating <= 10);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
