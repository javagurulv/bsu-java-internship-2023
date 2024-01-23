INSERT INTO classifiers(title, description)
VALUES('RISK_TYPE', 'Risk type classifier');

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


INSERT INTO classifiers(title, description)
VALUES('COUNTRY', 'Country classifier');

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'LATVIA',
    'Country Latvia'
FROM classifiers as cl
WHERE cl.title = 'COUNTRY';

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'SPAIN',
    'Country Spain'
FROM classifiers as cl
WHERE cl.title = 'COUNTRY';

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'JAPAN',
    'Country Japan'
FROM classifiers as cl
WHERE cl.title = 'COUNTRY';


INSERT INTO travel_medical_country_default_day_rate(country_ic, country_default_day_rate)
VALUES('LATVIA', 1.00);

INSERT INTO travel_medical_country_default_day_rate(country_ic, country_default_day_rate)
VALUES('SPAIN', 2.50);

INSERT INTO travel_medical_country_default_day_rate(country_ic, country_default_day_rate)
VALUES('JAPAN', 3.50);

INSERT INTO travel_medical_age_coefficient(age_from, age_to, coefficient)
VALUES (0, 5, 1.1),
       (6, 10, 0.7),
       (11, 17, 1.0),
       (18, 40, 1.1),
       (41, 65, 1.2),
       (65, 150, 1.5);

INSERT INTO classifiers(title, description) VALUES ('MEDICAL_RISK_LIMIT_LEVEL', 'medical risk limit level');
INSERT INTO classifier_values(classifier_id, ic, description)
VALUES ((SELECT id FROM classifiers WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'), 'LEVEL_10000', 'Medical Risk 10000 euro limit level'),
       ((SELECT id FROM classifiers WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'), 'LEVEL_15000', 'Medical Risk 15000 euro limit level'),
       ((SELECT id FROM classifiers WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'), 'LEVEL_20000', 'Medical Risk 20000 euro limit level'),
       ((SELECT id FROM classifiers WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'), 'LEVEL_50000', 'Medical Risk 50000 euro limit level');

INSERT INTO travel_medical_risk_limit_level(medical_risk_limit_level_ic, coefficient) VALUES
                                                                                   ('LEVEL_10000', 1.0),
                                                                                   ('LEVEL_15000', 1.2),
                                                                                   ('LEVEL_20000', 1.5),
                                                                                   ('LEVEL_50000', 2.0);

INSERT INTO travel_cancellation_trip_cost_coefficient(travel_cost_from, travel_cost_to, coefficient)
VALUES (0, 4999.99, 10.0),
       (5000, 9999.99, 30.0),
       (10000, 19999.99, 100.0),
       (20000, 10000000, 500.0);
