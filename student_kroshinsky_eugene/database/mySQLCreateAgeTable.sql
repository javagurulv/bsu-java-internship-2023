CREATE TABLE IF NOT EXISTS `age_coefficient` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `age_from` INT NOT NULL,
  `age_to` INT NOT NULL,
  `coefficient` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

CREATE UNIQUE INDEX `ix_age_coefficient_age_from`
ON `age_coefficient` (`age_from`);

CREATE UNIQUE INDEX `ix_age_coefficient_age_to`
ON `age_coefficient` (`age_to`);

INSERT INTO age_coefficient(age_from,age_to,coefficient)
VALUES  (0, 5, 1.1),
        (6, 10, 0.7),
        (11, 17, 1.0),
        (18, 40, 1.1),
        (41, 65, 1.2),
        (66, 150, 1.5);