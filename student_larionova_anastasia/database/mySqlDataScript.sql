/*
USE `java-real-practice-insurance`;

INSERT INTO classifiers (title, description)
VALUES ('RISK_TYPE', 'Travel risk type');

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT id, 'TRAVEL_MEDICAL', 'Travel medical type risk'
FROM classifiers
WHERE title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT id, 'TRAVEL_CANCELLATION', 'Travel cancellation type risk'
FROM classifiers
WHERE title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT id, 'TRAVEL_LOSS_BAGGAGE', 'Travel loss baggage type risk'
FROM classifiers
WHERE title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT id, 'TRAVEL_THIRD_PARTY_LIABILITY', 'Travel third party lyabiriyy type risk'
FROM classifiers
WHERE title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT id, 'TRAVEL_EVACUATION', 'Travel evacuation type risk'
FROM classifiers
WHERE title = 'RISK_TYPE';

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT id, 'TRAVEL_SPORT_ACTIVITIES', 'Travel sport activities type risk'
FROM classifiers
WHERE title = 'RISK_TYPE'
*/