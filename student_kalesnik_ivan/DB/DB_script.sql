SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

USE `java-real-practice-insurance` ;

CREATE TABLE `java-real-practice-insurance`.`classifiers` (
                                                              `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                              `title` VARCHAR(200) NOT NULL,
                                                              `description` VARCHAR(200) NOT NULL,
                                                              PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1002
    DEFAULT CHARACTER SET = utf8;


CREATE UNIQUE INDEX `ix_classifiers_title` ON `classifiers` (`title`);

CREATE TABLE `java-real-practice-insurance`.`classifier_values` (
                                                                    `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                                    `classifier_id` BIGINT NOT NULL,
                                                                    `ic` VARCHAR(200) NOT NULL,
                                                                    `description` VARCHAR(200) NOT NULL,
                                                                    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1002
    DEFAULT CHARACTER SET = utf8
    COMMENT = 'CLASSIFIER_VALUES';

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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;