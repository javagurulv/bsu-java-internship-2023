MERGE INTO classifiers(title, description)
KEY(title)
VALUES('RISK_TYPE', 'Risk type classifier');

MERGE INTO classifiers(title, description)
KEY(title)
VALUES('COUNTRY', 'Country of travel classifier');

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