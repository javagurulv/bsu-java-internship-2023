INSERT INTO classifiers(title, description)
VALUES('RISK_TYPE', 'Risk type classifier'),
    ('COUNTRY', 'travel policy host country');

INSERT INTO classifier_values(
  classifier_id,
    ic,
    description)
SELECT
  cl.id,
    'TRAVEL_MEDICAL',
    'Travel policy medical risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
  classifier_id,
    ic,
    description)
SELECT
  cl.id,
    'TRAVEL_CANCELLATION',
    'Travel policy trip cancellation risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
  classifier_id,
    ic,
    description)
SELECT
  cl.id,
    'TRAVEL_LOSS_BAGGAGE',
    'Travel policy baggage lose risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
  classifier_id,
    ic,
    description)
SELECT
  cl.id,
    'TRAVEL_THIRD_PARTY_LIABILITY',
    'Travel policy third party liability risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
  classifier_id,
    ic,
    description)
SELECT
  cl.id,
    'TRAVEL_EVACUATION',
    'Travel policy evacuation risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
  classifier_id,
    ic,
    description)
SELECT
  cl.id,
    'TRAVEL_SPORT_ACTIVITIES',
    'Travel policy sport activities risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values(
  classifier_id,
    ic,
    description)
SELECT
  cl.id,
    'LATVIA',
    'country for trip'
 FROM classifiers as cl
 WHERE cl.title = 'COUNTRY';

 INSERT INTO classifier_values(
  classifier_id,
    ic,
    description)
SELECT
  cl.id,
    'SPAIN',
    'country for trip'
 FROM classifiers as cl
 WHERE cl.title = 'COUNTRY';


 INSERT INTO classifier_values(
  classifier_id,
    ic,
    description)
SELECT
  cl.id,
    'JAPAN',
    'country for trip'
 FROM classifiers as cl
 WHERE cl.title = 'COUNTRY';

INSERT INTO country_default_day_rate(country_ic, default_day_rate)
VALUES('LATVIA', 1.00);

INSERT INTO country_default_day_rate(country_ic, default_day_rate)
VALUES('SPAIN', 2.50);

INSERT INTO country_default_day_rate(country_ic, default_day_rate)
VALUES('JAPAN', 3.50);

INSERT INTO age_coefficient(age_from,age_to,coefficient)
VALUES  (0, 5, 1.1),
        (6, 10, 0.7),
        (11, 17, 1.0),
        (18, 40, 1.1),
        (41, 65, 1.2),
        (66, 150, 1.5);

INSERT INTO classifiers (title, description)
VALUES ('MEDICAL_RISK_LIMIT_LEVEL', 'maximum payout to client in insured event');

INSERT INTO classifier_values (classifier_id, ic, description)
VALUES ((SELECT id FROM `classifiers` WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'), 'LEVEL_10000', 'Limit is 10000'),
 ((SELECT id FROM `classifiers` WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'), 'LEVEL_15000', 'Limit is 15000'),
 ((SELECT id FROM `classifiers` WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'), 'LEVEL_20000', 'Limit is 20000'),
 ((SELECT id FROM `classifiers` WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'), 'LEVEL_50000', 'Limit is 50000');

INSERT INTO medical_risk_limit_level(medical_risk_limit_level_ic, coefficient)
VALUES ('LEVEL_10000', 1.0),
('LEVEL_15000', 1.2),
('LEVEL_20000', 1.5),
('LEVEL_50000', 2.0);