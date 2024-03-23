INSERT INTO classifiers(title, description)
VALUES('RISK_TYPE', 'Risk type classifier');

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'TRAVEL_MEDICAL',
    'Medical risk during travel'
FROM classifiers as cl
WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'TRAVEL_CANCELLATION',
    'Trip cancellation risk during travel'
FROM classifiers as cl
WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'TRAVEL_LOSS_BAGGAGE',
    'Baggage lose risk type'
FROM classifiers as cl
WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'TRAVEL_THIRD_PARTY_LIABILITY',
    'Third party liability risk during travel'
FROM classifiers as cl
WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'TRAVEL_EVACUATION',
    'Evacuation risk during travel'
FROM classifiers as cl
WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT
    cl.id,
    'TRAVEL_SPORT_ACTIVITIES',
    'Sport activities risk during travel'
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

INSERT INTO country_default_day_rate(country_ic, default_day_rate)
VALUES('LATVIA', 1.00);

INSERT INTO country_default_day_rate(country_ic, default_day_rate)
VALUES('SPAIN', 2.50);

INSERT INTO country_default_day_rate(country_ic, default_day_rate)
VALUES('JAPAN', 3.50);