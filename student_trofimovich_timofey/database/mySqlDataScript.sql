insert into classifiers (title, description)
values ('RISK_TYPE', 'travel policy risk type classifier');
insert into classifier_values (classifier_id, ic, description)
values ((select id from classifiers where title = 'RISK_TYPE'), 'TRAVEL_MEDICAL', 'travel policy medical risk'),
       ((select id from classifiers where title = 'RISK_TYPE'), 'TRAVEL_CANCELLATION',
        'travel policy trip cancellation risk'),
       ((select id from classifiers where title = 'RISK_TYPE'), 'TRAVEL_LOSS_BAGGAGE',
        'travel policy baggage lose risk');
insert into classifier_values (classifier_id, ic, description)
values ((select id from classifiers where title = 'RISK_TYPE'), 'TRAVEL_THIRD_PARTY_LIABILITY',
        'Travel policy third party liability risk type'),
       ((select id from classifiers where title = 'RISK_TYPE'), 'TRAVEL_EVACUATION',
        'Travel policy evacuation risk type'),
       ((select id from classifiers where title = 'RISK_TYPE'), 'TRAVEL_SPORT_ACTIVITIES',
        'Travel policy sport activities risk type');


insert into classifiers(title, description)
values ('COUNTRY', 'country classifier');

insert into classifier_values(classifier_id, ic, description)
VALUES ((SELECT id from classifiers where title = 'COUNTRY'), 'LATVIA', 'country: Latvia'),
       ((SELECT id from classifiers where title = 'COUNTRY'), 'SPAIN', 'country: Spain'),
       ((SELECT id from classifiers where title = 'COUNTRY'), 'JAPAN', 'country: Japan');

INSERT INTO country_default_day_rate(country_ic, country_default_day_rate)
VALUES ('LATVIA', 1.00),
       ('SPAIN', 2.50),
       ('JAPAN', 3.50);