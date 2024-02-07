USE `java-real-practice-insurance`;

INSERT INTO `classifiers` (title, description)
VALUES ("MEDICAL_RISK_LIMIT_LEVEL", "maximum payout to client in insured event");

INSERT INTO `classifier_values` (classifier_id, ic, description)
VALUES ((SELECT id FROM `classifiers` WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'), "LEVEL_10000", "Limit is 10000"),
 ((SELECT id FROM `classifiers` WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'), "LEVEL_15000", "Limit is 15000"),
 ((SELECT id FROM `classifiers` WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'), "LEVEL_20000", "Limit is 20000"),
 ((SELECT id FROM `classifiers` WHERE title = 'MEDICAL_RISK_LIMIT_LEVEL'), "LEVEL_50000", "Limit is 50000");