create sequence classifiers_id_seq
    increment by 1002;

create sequence classifier_values_id_seq
    increment by 1002;

create table classifiers
(
    id          bigint default nextval('insurance_schema.classifiers_id_seq'::regclass) not null
        constraint classifiers_pk
            primary key,
    title       varchar(200)                                                            not null,
    description varchar(100)                                                            not null
);

alter sequence classifiers_id_seq owned by classifiers.id;

create table classifier_values
(
    id            bigint default nextval('insurance_schema.classifier_values_id_seq'::regclass) not null
        constraint classifier_values_pk
            primary key,
    classifier_id bigint                                                                        not null
        constraint classifier_values_classifiers_id_fk
            references classifiers,
    ic            varchar(200)                                                                  not null,
    description   varchar(500)                                                                  not null
);

alter sequence classifier_values_id_seq owned by classifier_values.id;