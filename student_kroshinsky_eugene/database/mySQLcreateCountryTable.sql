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


INSERT INTO country_default_day_rate(country_ic,default_day_rate)
VALUES ("LATVIA", 10.0),
("SPAIN", 2.50),
("JAPAN", 3.50);