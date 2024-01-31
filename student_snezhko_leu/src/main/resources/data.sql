MERGE INTO classifiers(title, description)
KEY(title)
VALUES('RISK_TYPE', 'Risk type classifier');

MERGE INTO classifiers(title, description)
KEY(title)
VALUES('COUNTRY', 'Country of travel classifier');

MERGE INTO classifiers(title, description)
KEY(title)
VALUES('MEDICAL_RISK_LIMIT_LEVEL', 'Values of insurance limit coefficient');



MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
KEY(ic)
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
KEY(ic)
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


MERGE INTO classifier_values(
	classifier_id,
    ic,
    description)
KEY(ic)
SELECT
	cl.id,
    'LATVIA',
    'Latvia travel country'
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
     'Spain travel country'
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
      'Japan travel country'
 FROM classifiers as cl
 WHERE cl.title = 'COUNTRY';

 MERGE INTO classifier_values(
        classifier_id,
        ic,
        description
   )
 KEY (ic)
 SELECT
        cl.id,
        'LEVEL_10000',
        'Coefficient for insurance limit 10000'
 FROM classifiers AS cl
 WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

 MERGE INTO classifier_values (
        classifier_id,
        ic,
        description
 )
 KEY(ic)
 SELECT
    cl.id,
    'LEVEL_15000',
    'Coefficient for insurance limit 15000'
 FROM classifiers AS cl
 WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

 MERGE INTO classifier_values (
         classifier_id,
         ic,
         description
  )
 KEY(ic)
 SELECT
     cl.id,
     'LEVEL_20000',
     'Coefficient for insurance limit 20000'
 FROM classifiers AS cl
 WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

 MERGE INTO classifier_values (
         classifier_id,
         ic,
         description
  )
 KEY(ic)
 SELECT
     cl.id,
     'LEVEL_50000',
     'Coefficient for insurance limit 50000'
 FROM classifiers AS cl
 WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';




 MERGE INTO COUNTRY_DEFAULT_DAY_RATE (
        country_ic,
        country_default_day_rate)
 KEY(country_ic)
 SELECT
        cl.ic,
        1.00
 FROM classifier_values AS cl
 WHERE cl.ic = 'LATVIA';

MERGE INTO COUNTRY_DEFAULT_DAY_RATE (
    country_ic,
    country_default_day_rate)
KEY (country_ic)
SELECT
    cl.ic,
    2.50
FROM classifier_values AS cl
WHERE cl.ic = 'SPAIN';

MERGE INTO COUNTRY_DEFAULT_DAY_RATE (
    country_ic,
    country_default_day_rate
)
KEY(country_ic)
SELECT
    cl.ic,
    3.50
FROM classifier_values AS cl
WHERE cl.ic = 'JAPAN';



MERGE INTO AGE_COEFFICIENT(
    age_from,
    age_to,
    coefficient
)
KEY(age_from, age_to)
VALUES (0, 5, 1.1);

MERGE INTO AGE_COEFFICIENT (
    age_from,
    age_to,
    coefficient
)
KEY(age_from, age_to)
VALUES (6, 10, 0.7);

MERGE INTO AGE_COEFFICIENT (
    age_from,
    age_to,
    coefficient
)
KEY(age_from, age_to)
VALUES (11, 17, 1.0);

MERGE INTO AGE_COEFFICIENT (
    age_from,
    age_to,
    coefficient
)
KEY(age_from, age_to)
VALUES (18, 40, 1.1);

MERGE INTO AGE_COEFFICIENT (
    age_from,
    age_to,
    coefficient
)
KEY(age_from, age_to)
VALUES (41, 65, 1.2);


MERGE INTO AGE_COEFFICIENT (
    age_from,
    age_to,
    coefficient
)
KEY(age_from, age_to)
VALUES (66, 150, 1.5);



MERGE INTO MEDICAL_RISK_LIMIT_LEVEL (
    MEDICAL_RISK_LIMIT_LEVEL_IC,
    COEFFICIENT
)
KEY (MEDICAL_RISK_LIMIT_LEVEL_IC)
VALUES ('LEVEL_10000', 1.0);

MERGE INTO MEDICAL_RISK_LIMIT_LEVEL (
    MEDICAL_RISK_LIMIT_LEVEL_IC,
    COEFFICIENT
)
KEY (MEDICAL_RISK_LIMIT_LEVEL_IC)
VALUES ('LEVEL_15000', 1.2);

MERGE INTO MEDICAL_RISK_LIMIT_LEVEL (
    MEDICAL_RISK_LIMIT_LEVEL_IC,
    COEFFICIENT
)
KEY (MEDICAL_RISK_LIMIT_LEVEL_IC)
VALUES ('LEVEL_20000', 1.5);

MERGE INTO MEDICAL_RISK_LIMIT_LEVEL (
    MEDICAL_RISK_LIMIT_LEVEL_IC,
    COEFFICIENT
)
KEY (MEDICAL_RISK_LIMIT_LEVEL_IC)
VALUES ('LEVEL_50000', 2.0);