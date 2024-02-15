INSERT INTO classifiers(title, description)
VALUES('RISK_TYPE', 'Risk type classifier');

INSERT INTO classifiers(title, description) VALUES('COUNTRY', 'Country classifier');
INSERT INTO classifiers(title, description) VALUES ('MEDICAL_RISK_LIMIT_LEVEL', 'no');

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
    'JAPAN',
    'country'
FROM classifiers as cl
WHERE cl.title = 'COUNTRY';

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'LATVIA',
    'country'
FROM classifiers as cl
WHERE cl.title = 'COUNTRY';

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'SPAIN',
    'country'
FROM classifiers as cl
WHERE cl.title = 'COUNTRY';

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'LEVEL_10000',
    'limit'
FROM classifiers as cl
WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'LEVEL_15000',
    'limit'
FROM classifiers as cl
WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'LEVEL_20000',
    'limit'
FROM classifiers as cl
WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'LEVEL_50000',
    'limit'
FROM classifiers as cl
WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';


INSERT INTO country_default_day_rate (country_ic, default_day_rate) VALUES ('LATVIA', '1.00');
INSERT INTO country_default_day_rate (country_ic, default_day_rate) VALUES ('SPAIN', '2.50');
INSERT INTO country_default_day_rate (country_ic, default_day_rate) VALUES ('JAPAN', '3.50');

INSERT INTO age_coefficient (age_from, age_to, coefficient) VALUES (0, 5, 1.1);
INSERT INTO age_coefficient (age_from, age_to, coefficient) VALUES (6, 10, 0.7);
INSERT INTO age_coefficient (age_from, age_to, coefficient) VALUES (11, 17, 1.0);
INSERT INTO age_coefficient (age_from, age_to, coefficient) VALUES (18, 40, 1.1);
INSERT INTO age_coefficient (age_from, age_to, coefficient) VALUES (41, 65, 1.2);
INSERT INTO age_coefficient (age_from, age_to, coefficient) VALUES (66, 150, 1.5);

INSERT INTO medical_risk_limit_level (MEDICAL_RISK_LIMIT_LEVEL_IC, COEFFICIENT) VALUES ('LEVEL_10000', '1.0');
INSERT INTO medical_risk_limit_level (MEDICAL_RISK_LIMIT_LEVEL_IC, COEFFICIENT) VALUES ('LEVEL_15000', '1.2');
INSERT INTO medical_risk_limit_level (MEDICAL_RISK_LIMIT_LEVEL_IC, COEFFICIENT) VALUES ('LEVEL_20000', '1.5');
INSERT INTO medical_risk_limit_level (MEDICAL_RISK_LIMIT_LEVEL_IC, COEFFICIENT) VALUES ('LEVEL_50000', '2.0');

