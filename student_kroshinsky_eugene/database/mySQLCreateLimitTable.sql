CREATE TABLE IF NOT EXISTS `medical_risk_limit_level` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `medical_risk_limit_level_ic` VARCHAR(200) NOT NULL,
  `coefficient` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

CREATE UNIQUE INDEX `ix_medical_risk_limit_level_ic`
ON `medical_risk_limit_level` (`medical_risk_limit_level_ic`);


INSERT INTO medical_risk_limit_level(medical_risk_limit_level_ic,coefficient)
VALUES ("LEVEL_10000", 1.0),
("LEVEL_15000", 1.2),
("LEVEL_20000", 1.5),
("LEVEL_50000", 2.0);