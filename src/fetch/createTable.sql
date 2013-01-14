create table FETCH_ROOT(
 id bigint not null,
 value text,
 version integer
);

--PK
ALTER TABLE FETCH_ROOT ADD PRIMARY KEY(id);

create table FETCH_SUB1(
 id bigint not null,
 root_id bigint not null,
 value text,
 version integer
);

--PK
ALTER TABLE FETCH_SUB1 ADD PRIMARY KEY(id);

--root FK
ALTER TABLE FETCH_SUB1 ADD CONSTRAINT ROOT_SUB_FK
 FOREIGN KEY (root_id)
 REFERENCES FETCH_ROOT(id);
 

create table FETCH_SUB2(
 id bigint not null,
 sub1_id bigint not null,
 value text,
 version integer
);

--PK
ALTER TABLE FETCH_SUB2 ADD PRIMARY KEY(id);

 --sub FK
ALTER TABLE FETCH_SUB2 ADD CONSTRAINT SUB_SUB2_FK
 FOREIGN KEY (sub1_id)
 REFERENCES FETCH_SUB1(id);
 