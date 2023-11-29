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


insert into classifiers(title, description)
values ('COUNTRY', 'country');

insert into classifier_values(classifier_id, ic, description)
VALUES ((SELECT id from classifiers where title = 'COUNTRY'), 'LATVIA', 'country: Latvia'),
       ((SELECT id from classifiers where title = 'COUNTRY'), 'SPAIN', 'country: Spain'),
       ((SELECT id from classifiers where title = 'COUNTRY'), 'JAPAN', 'country: Japan');
