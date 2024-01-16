CREATE TABLE IF NOT EXISTS classifiers (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL UNIQUE,
  description VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS ix_classifiers_title ON classifiers(title);

CREATE TABLE IF NOT EXISTS classifier_values (
  id BIGINT NOT NULL AUTO_INCREMENT,
  classifier_id BIGINT NOT NULL,
  ic VARCHAR(200) NOT NULL UNIQUE,
  description VARCHAR(500) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE classifier_values
ADD FOREIGN KEY (classifier_id) REFERENCES classifiers(id);

CREATE UNIQUE INDEX IF NOT EXISTS ix_classifier_values_ic
ON classifier_values(ic);

CREATE TABLE IF NOT EXISTS COUNTRY_DEFAULT_DAY_RATE (
    id BIGINT NOT NULL AUTO_INCREMENT,
    country_ic  VARCHAR(200) NOT NULL UNIQUE,
    country_default_day_rate FLOAT NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE COUNTRY_DEFAULT_DAY_RATE
ADD FOREIGN KEY (country_ic) REFERENCES classifier_values(ic);