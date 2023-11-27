INSERT INTO classifiers(title, description)
VALUES ("RISK_TYPE", "contains the types of risks available in the application contains");
INSERT INTO classifier_values  (classifier_id, ic, description)
SELECT id, "TRAVEL_MEDICAL", "travel policy medical risk"
FROM classifiers where classifiers.title = "RISK_TYPE";
INSERT INTO classifier_values  (classifier_id, ic, description)
SELECT id, "TRAVEL_CANCELLATION", "travel policy trip cancellation risk"
FROM classifiers where classifiers.title = "RISK_TYPE";
INSERT INTO classifier_values  (classifier_id, ic, description)
SELECT id, "TRAVEL_LOSS_BAGGAGE", "travel policy baggage lose risk"
FROM classifiers where classifiers.title = "RISK_TYPE";
INSERT INTO classifier_values  (classifier_id, ic, description)
SELECT id, "TRAVEL_THIRD_PARTY_LIABILITY", "travel policy liability to third party risk"
FROM classifiers where classifiers.title = "RISK_TYPE";
INSERT INTO classifier_values  (classifier_id, ic, description)
SELECT id, "TRAVEL_EVACUATION","travel policy evacuation risk"
FROM classifiers where classifiers.title = "RISK_TYPE";
INSERT INTO classifier_values  (classifier_id, ic, description)
SELECT id, "TRAVEL_SPORT_ACTIVITIES","travel policy sport activities risk"
FROM classifiers where classifiers.title = "RISK_TYPE";


