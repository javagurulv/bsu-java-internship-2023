USE `java-real-practice-insurance`;
INSERT INTO classifiers (title, description)
VALUES ('RISK_TYPE', 'travel policy risk type classifier');

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT
    cl.id,
    'TRAVEL_MEDICAL',
    'Travel policy medical risk type'
FROM classifiers as cl
WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT
    cl.id,
    'TRAVEL_CANCELLATION',
    'Travel police cancellation risk type'
FROM classifiers as cl
WHERE cl.title = 'RISK_TYPE';

UPDATE classifier_values
SET description = 'Travel policy cancellation risk type'
WHERE id = 1003;

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT
    cl.id,
    'TRAVEL_LOSS_BAGGAGE',
    'Travel policy loss baggage risk type'
FROM classifiers as cl
WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT
    cl.id,
    'TRAVEL_THIRD_PARTY_LIABILITY',
    'Travel policy third party liability risk type'
FROM classifiers as cl
WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT
    cl.id,
    'TRAVEL_EVACUATION',
    'Travel policy evacuation risk type'
FROM classifiers as cl
WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT
    cl.id,
    'TRAVEL_SPORT_ACTIVITIES',
    'Travel policy sport activities risk type'
FROM classifiers as cl
WHERE cl.title = 'RISK_TYPE';

