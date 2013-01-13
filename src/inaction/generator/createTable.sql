create table IDENTITY_GENERATOR(
 id SERIAL not null,
 value text,
 version bigint
);

--PK
ALTER TABLE IDENTITY_GENERATOR ADD PRIMARY KEY(id);

create table SEQUENCE_GENERATOR(
 id bigint not null,
 value text,
 version bigint
);

create sequence generator_sequence_id;

ALTER TABLE SEQUENCE_GENERATOR ADD PRIMARY KEY(id);


create table UUID_GENERATOR(
 id text not null,
 value text,
 version bigint
);

ALTER TABLE UUID_GENERATOR ADD PRIMARY KEY(id);


create table HILO_GENERATOR(
 id bigint not null,
 value text,
 version bigint
);

ALTER TABLE HILO_GENERATOR ADD PRIMARY KEY(id);
