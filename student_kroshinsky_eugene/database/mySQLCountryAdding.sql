USE `java-real-practice-insurance`;

INSERT INTO `classifiers` (title, description)
VALUES ("COUNTRY", "travel policy host country");

INSERT INTO `classifier_values` (classifier_id, ic, description)
VALUES ((SELECT id FROM `classifiers` WHERE title = 'COUNTRY'), "LATVIA", "country for trip"),
 ((SELECT id FROM `classifiers` WHERE title = 'COUNTRY'), "SPAIN", "country for trip"),
 ((SELECT id FROM `classifiers` WHERE title = 'COUNTRY'), "JAPAN", "country for trip");