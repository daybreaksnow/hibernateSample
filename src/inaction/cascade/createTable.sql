create table CATEGORY(
 category_id bigint not null,
 parent_category_id bigint,
 name varchar(256) not null,
 created_date date
);

--Category PK
ALTER TABLE CATEGORY ADD PRIMARY KEY(category_id);

--parentCategory FK
ALTER TABLE CATEGORY ADD CONSTRAINT CATEGORY_PARENT_FK
 FOREIGN KEY (parent_category_id)
 REFERENCES CATEGORY(category_id);