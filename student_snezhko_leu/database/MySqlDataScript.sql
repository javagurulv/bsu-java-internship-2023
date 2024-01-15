INSERT INTO classifiers (id, title)
    VALUES
    ("RISK_TYPE", "Risk type classifier"),
    ("COUNTRY", "Travel country classifier")
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