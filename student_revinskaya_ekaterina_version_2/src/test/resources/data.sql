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

 insert into travel_medical_age_coefficient (age_from, age_to, coefficient) values
    (0, 5, 1.1),
    (6, 10, 0.7),
    (11, 17, 1.0),
    (18, 40, 1.1),
    (41, 65, 1.2),
    (66, 150, 1.5);

    insert into classifiers (title, description)
    values('MEDICAL_RISK_LIMIT_LEVEL',
    'limit levels for medical risk payments');

    insert into classifier_values (classifier_id, ic, description)
    select classifiers.id, 'LEVEL_10000', 'the maximum payout is 10,000 euros'
    from classifiers where classifiers.title = 'MEDICAL_RISK_LIMIT_LEVEL';

    insert into classifier_values (classifier_id, ic, description)
    select classifiers.id, 'LEVEL_15000', 'the maximum payout is 15,000 euros'
    from classifiers where classifiers.title = 'MEDICAL_RISK_LIMIT_LEVEL';

    insert into classifier_values (classifier_id, ic, description)
    select classifiers.id, 'LEVEL_20000', 'the maximum payout is 20,000 euros'
    from classifiers where classifiers.title = 'MEDICAL_RISK_LIMIT_LEVEL';

    insert into classifier_values (classifier_id, ic, description)
    select classifiers.id, 'LEVEL_50000', 'the maximum payout is 50,000 euros'
    from classifiers where classifiers.title = 'MEDICAL_RISK_LIMIT_LEVEL';

    insert into medical_risk_limit_level ( medical_risk_limit_level_ic, coefficient)
    values('LEVEL_10000', 1.0),
    ('LEVEL_15000', 1.2),
    ('LEVEL_20000', 1.5),
    ('LEVEL_50000', 2.0);
INSERT INTO travel_cancellation_age_coefficient(age_from, age_to, coefficient)
VALUES
(0, 9, 5),
(10, 17, 10),
(18, 39, 20),
(40, 64, 30),
(65, 150, 50)
;
INSERT INTO travel_cost_coefficient(cost_from, cost_to, coefficient)
VALUES
(0, 5000, 10),
(5000, 10000, 30),
(10000, 20000, 100),
(20000, 1000000, 500)
;
INSERT INTO country_safety_rating_coefficient(country_ic, rating)
VALUES
('LATVIA', 5),
('SPAIN', 8),
('JAPAN', 9)
;