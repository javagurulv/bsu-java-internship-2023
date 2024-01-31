INSERT INTO classifiers (title, description)
    VALUES
    ("RISK_TYPE", "Risk type classifier"),
    ("COUNTRY", "Travel country classifier"),
    ('MEDICAL_RISK_LIMIT_LEVEL', 'Values of insurance limit coefficient')
    ;


INSERT INTO classifier_values (classifier_id, ic, description)

    SELECT cl.id, 'TRAVEL_MEDICAL', 'Travel policy medical risk type' FROM classifiers as cl
    WHERE cl.title = 'RISK_TYPE'

    UNION ALL
    SELECT cl.id, 'TRAVEL_CANCELLATION', 'travel policy cancelling travel risk' FROM classifiers AS cl
    WHERE cl.title = 'RISK_TYPE'
    
    UNION ALL
    SELECT cl.id, 'TRAVEL_LOSS_BAGGAGE', 'travel policy loss baggage risk' FROM classifiers AS cl 
    WHERE cl.title = 'RISK_TYPE'
	
    UNION ALL
    SELECT cl.id, 'TRAVEL_THIRD_PARTY_LIABILITY', 'travel policy third party liability risk' FROM classifiers AS cl 
    WHERE cl.title = 'RISK_TYPE'
    
    UNION ALL
    SELECT cl.id, 'TRAVEL_EVACUATION', 'travel policy evacuation or deportation risk' FROM classifiers AS cl 
    WHERE cl.title = 'RISK_TYPE'
	
    UNION ALL
    SELECT cl.id, 'TRAVEL_SPORT_ACTIVITIES', 'travel policy sport activities risk' FROM classifiers AS cl 
    WHERE cl.title = 'RISK_TYPE'


    UNION ALL
    SELECT cl.id, 'LATVIA', "Latvia travel country" FROM classifiers AS cl
    WHERE cl.title = "COUNTRY"

    UNION ALL
    SELECT cl.id, 'SPAIN', "Spain travel country" FROM classifiers AS cl
    WHERE cl.title = "COUNTRY"

    UNION ALL
    SELECT cl.id, 'JAPAN', "Japan travel country" FROM classifiers AS cl
    WHERE cl.title = "COUNTRY"

    UNION ALL
    SELECT cl.id, 'LEVEL_10000', 'Coefficient for insurance limit 10000' FROM classifiers AS cl
    WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL'

    UNION ALL
    SELECT cl.id, 'LEVEL_15000', 'Coefficient for insurance limit 15000' FROM classifiers AS cl
    WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL'

    UNION ALL
    SELECT cl.id, 'LEVEL_20000', 'Coefficient for insurance limit 20000' FROM classifiers AS cl
    WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL'

    UNION ALL
    SELECT cl.id, 'LEVEL_50000', 'Coefficient for insurance limit 50000' FROM classifiers AS cl
    WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL'
    ;

INSERT INTO COUNTRY_DEFAULT_DAY_RATE (country_ic, country_default_day_rate)
    SELECT cl.ic, 1.00 FROM classifier_values AS cl
    WHERE cl.ic = "LATVIA"

    UNION ALL
    SELECT cl.ic, 2.50 FROM classifier_values AS cl
    WHERE cl.ic = "SPAIN"

    UNION ALL
    SELECT cl.ic, 3.50 FROM classifier_values AS cl
    WHERE cl.ic = "JAPAN"
;

INSERT INTO AGE_COEFFICIENT (age_from, age_to, coefficient)
VALUES (0, 5, 1.1),
        (6, 10, 0.7),
        (11, 17, 1.0),
        (18, 40, 1.1),
        (41, 65, 1.2),
        (66, 150, 1.5);

INSERT INTO MEDICAL_RISK_LIMIT_LEVEL (MEDICAL_RISK_LIMIT_LEVEL_IC, COEFFICIENT)
VALUES ('LEVEL_10000', 1.0),
       ('LEVEL_15000', 1.2),
       ('LEVEL_20000', 1.5),
       ('LEVEL_50000', 2.0);