MERGE INTO classifiers(title, description)
KEY(title)
VALUES('RISK_TYPE', 'Risk type classifier');

MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
KEY (ic)
SELECT
	cl.id,
    'TRAVEL_MEDICAL',
    'Travel policy medical risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
    KEY (ic)
SELECT
	cl.id,
    'TRAVEL_CANCELLATION',
    'Travel policy trip cancellation risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
    KEY(ic)
SELECT
	cl.id,
    'TRAVEL_LOSS_BAGGAGE',
    'Travel policy baggage lose risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
    KEY(ic)
SELECT
	cl.id,
    'TRAVEL_THIRD_PARTY_LIABILITY',
    'Travel policy third party liability risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
    KEY(ic)
SELECT
	cl.id,
    'TRAVEL_EVACUATION',
    'Travel policy evacuation risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
    KEY(ic)
SELECT
	cl.id,
    'TRAVEL_SPORT_ACTIVITIES',
    'Travel policy sport activities risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


MERGE INTO classifiers(title, description)
KEY(title)
VALUES('COUNTRY', 'Country classifier');

MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
    KEY(ic)
SELECT
	cl.id,
    'LATVIA',
    'Country Latvia'
 FROM classifiers as cl
 WHERE cl.title = 'COUNTRY';

MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
    KEY(ic)
SELECT
	cl.id,
    'SPAIN',
    'Country Spain'
 FROM classifiers as cl
 WHERE cl.title = 'COUNTRY';

MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
    KEY(ic)
SELECT
	cl.id,
    'JAPAN',
    'Country Japan'
 FROM classifiers as cl
 WHERE cl.title = 'COUNTRY';


MERGE INTO country_default_day_rate(country_ic, default_day_rate)
KEY(country_ic)
VALUES('LATVIA', 1.00);

MERGE INTO country_default_day_rate(country_ic, default_day_rate)
KEY(country_ic)
VALUES('SPAIN', 2.50);

MERGE INTO country_default_day_rate(country_ic, default_day_rate)
KEY(country_ic)
VALUES('JAPAN', 3.50);



MERGE INTO age_coefficient(age_from, age_to, coefficient)
KEY(age_from, age_to)
VALUES(0, 5, 1.1);

MERGE INTO age_coefficient(age_from, age_to, coefficient)
KEY(age_from, age_to)
VALUES(6, 10, 0.7);

MERGE INTO age_coefficient(age_from, age_to, coefficient)
KEY(age_from, age_to)
VALUES(11, 17, 1.0);

MERGE INTO age_coefficient(age_from, age_to, coefficient)
KEY(age_from, age_to)
VALUES(18, 40, 1.1);

MERGE INTO age_coefficient(age_from, age_to, coefficient)
KEY(age_from, age_to)
VALUES(41, 65, 1.2);

MERGE INTO age_coefficient(age_from, age_to, coefficient)
KEY(age_from, age_to)
VALUES(65, 150, 1.5);


MERGE INTO classifiers(title, description)
KEY(title)
VALUES('MEDICAL_RISK_LIMIT_LEVEL', 'Medical Risk limit level classifier');

MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
    KEY(ic)
SELECT
	cl.id,
    'LEVEL_10000',
    'Medical Risk 10000 euro limit level'
 FROM classifiers as cl
 WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
    KEY(ic)
SELECT
	cl.id,
    'LEVEL_15000',
    'Medical Risk 15000 euro limit level'
 FROM classifiers as cl
 WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
    KEY(ic)
SELECT
	cl.id,
    'LEVEL_20000',
    'Medical Risk 20000 euro limit level'
 FROM classifiers as cl
 WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';


MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
    KEY(ic)
SELECT
	cl.id,
    'LEVEL_50000',
    'Medical Risk 50000 euro limit level'
 FROM classifiers as cl
 WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';


MERGE INTO medical_risk_limit_level(medical_risk_limit_level_ic, coefficient)
KEY(medical_risk_limit_level_ic)
VALUES('LEVEL_10000', 1.0);

MERGE INTO medical_risk_limit_level(medical_risk_limit_level_ic, coefficient)
KEY(medical_risk_limit_level_ic)
VALUES('LEVEL_15000', 1.2);

MERGE INTO medical_risk_limit_level(medical_risk_limit_level_ic, coefficient)
KEY(medical_risk_limit_level_ic)
VALUES('LEVEL_20000', 1.5);

MERGE INTO medical_risk_limit_level(medical_risk_limit_level_ic, coefficient)
KEY(medical_risk_limit_level_ic)
VALUES('LEVEL_50000', 2.0);

MERGE INTO persons(first_name, last_name, ic, birth_date)
KEY(ic)
VALUES('CorrectFirstName', 'CorrectLastName', 'CORRECT_IC_AGR_#1', '2001-01-01');

insert INTO agreements(date_from, date_to, country, premium)
VALUES('2050-02-02', '2050-02-03', 'SPAIN', 1);

MERGE into selected_risks(risk_ic, agreement)
KEY (risk_ic)
SELECT 'CORRECT_RISK_IC',
        agr.id
FROM agreements AS agr;

MERGE into agreement_persons(first_name,
                            last_name,
                            person_ic,
                            agreement,
                            birth_date,
                            medical_risk_limit_level,
                            premium)
KEY(person_ic)
SELECT 'CorrectFirstName',
        'CorrectLastName',
        'CORRECT_PERSON_IC',
        agr.id,
        '2005-02-02',
        'CORRECT_MEDICAL_RISK_LIMIT_LEVEL',
        10
FROM agreements AS agr;

MERGE INTO person_risks(risk_ic, premium, person)
KEY(risk_ic, person)
SELECT
    'CORRECT_RISK_IC',
    2,
    p.id
from persons as p;