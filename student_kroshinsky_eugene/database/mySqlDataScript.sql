USE `java-real-practice-insurance`;

INSERT INTO `classifiers` (title, description)
VALUES ("RISK_TYPE", "travel policy risk type classifier");

    INSERT INTO `classifier_values` (classifier_id, ic, description)
    VALUES ((SELECT id FROM `classifiers` WHERE title = 'RISK_TYPE'), "TRAVEL_CANCELLATION", "travel policy trip cancellation risk"),
	   ((SELECT id FROM `classifiers` WHERE title = 'RISK_TYPE'), "TRAVEL_LOSS_BAGGAGE", "travel policy baggage lose risk"),
	   ((SELECT id FROM `classifiers` WHERE title = 'RISK_TYPE'), "TRAVEL_THIRD_PARTY_LIABILITY", "travel policy third party liability risk"),
	   ((SELECT id FROM `classifiers` WHERE title = 'RISK_TYPE'), "TRAVEL_EVACUATION", "travel policy evacuation risk"),
	   ((SELECT id FROM `classifiers` WHERE title = 'RISK_TYPE'), "TRAVEL_SPORT_ACTIVITIES", "travel policy sport risk");