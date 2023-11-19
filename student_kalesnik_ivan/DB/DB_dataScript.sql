INSERT INTO classifiers(title, description)
VALUES('RISK_TYPE', 'Risk type classifier');

INSERT INTO classifier_values(
    classifier_id,
    ic,
    description)
SELECT cl.id, tmp.ic, tmp.description
FROM classifiers as cl
         CROSS JOIN (
    SELECT 'TRAVEL_MEDICAL' AS ic, 'Travel policy medical risk type' AS description
    UNION ALL
    SELECT 'TRAVEL_CANCELLATION', 'Travel policy trip cancellation risk type'
    UNION ALL
    SELECT 'TRAVEL_LOSS_BAGGAGE', 'Travel policy baggage lose risk type'
    UNION ALL
    SELECT 'TRAVEL_THIRD_PARTY_LIABILITY', 'Travel policy third party liability risk type'
    UNION ALL
    SELECT 'TRAVEL_EVACUATION', 'Travel policy evacuation risk type'
    UNION ALL
    SELECT 'TRAVEL_SPORT_ACTIVITIES', 'Travel policy sport activities risk type'
) AS tmp
WHERE cl.title = 'RISK_TYPE';