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