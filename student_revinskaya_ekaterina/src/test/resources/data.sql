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

 insert into classifiers (title, description)
 values('COUNTRY', 'country type classifier');

insert into classifier_values (classifier_id, ic, description)
select classifiers.id, 'LATVIA', 'country of visit is Latvia'
from classifiers where classifiers.title = 'COUNTRY';

insert into classifier_values (classifier_id, ic, description)
select classifiers.id, 'SPAIN', 'country of visit is Spain'
from classifiers where classifiers.title = 'COUNTRY';

insert into classifier_values (classifier_id, ic, description)
select classifiers.id, 'JAPAN', 'country of visit is Japan'
from classifiers where classifiers.title = 'COUNTRY';

 insert into country_default_day_rate (  country_ic, default_day_rate)
 values('LATVIA', 1.00), ('SPAIN', 2.50), ('JAPAN', 3.50);

 insert into age_coefficient (age_from, age_to, coefficient) values
    (0, 5, 1.1),
    (6, 10, 0.7),
    (11, 17, 1.0),
    (18, 40, 1.1),
    (41, 65, 1.2),
    (66, 150, 1.5);

