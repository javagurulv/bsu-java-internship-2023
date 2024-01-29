INSERT INTO classifiers(title, description)
VALUES('RISK_TYPE', 'Risk type classifier');

INSERT INTO classifiers(title, description)
VALUES('COUNTRY', 'Country classifier');

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
