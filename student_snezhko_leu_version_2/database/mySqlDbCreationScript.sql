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


CREATE TABLE IF NOT EXISTS `age_coefficient` (
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
    ic VARCHAR(200) NOT NULL UNIQUE,
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
    medical_risk_limit_level VARCHAR(200) NOT NULL,
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

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
