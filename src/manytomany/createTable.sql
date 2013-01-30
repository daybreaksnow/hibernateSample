create table CATEGORY_MANYTOMANY(
 category_id bigint not null,
 parent_category_id bigint,
 name varchar(256) not null,
 version bigint
);

--Category PK
ALTER TABLE CATEGORY_MANYTOMANY ADD PRIMARY KEY(category_id);

--parentCategory FK
ALTER TABLE CATEGORY_MANYTOMANY ADD CONSTRAINT CATEGORY_MANYTOMANY_PARENT_FK
 FOREIGN KEY (parent_category_id)
 REFERENCES CATEGORY_MANYTOMANY(category_id);


--
create table ITEM_MANYTOMANY(
 item_id bigint not null,
 name varchar(256),
 version bigint
);

--Category PK
ALTER TABLE ITEM_MANYTOMANY ADD PRIMARY KEY(item_id);

--���ԃ}�b�s���O
create table ITEM_CATEGORY_MAPPING_MM(
 category_id bigint not null,
 item_id bigint not null,
 created_date date,
 version bigint
);

--PK
ALTER TABLE ITEM_CATEGORY_MAPPING_MM ADD PRIMARY KEY(category_id,item_id);

--item FK
ALTER TABLE ITEM_CATEGORY_MAPPING_MM ADD CONSTRAINT MAP_ITEM_FK
 FOREIGN KEY (item_id)
 REFERENCES ITEM_MANYTOMANY(item_id);

 --category FK
ALTER TABLE ITEM_CATEGORY_MAPPING_MM ADD CONSTRAINT MAP_CATEGORY_FK
 FOREIGN KEY (category_id)
 REFERENCES CATEGORY_MANYTOMANY(category_id);



create table ITEM_CATEGORY_MAPPING_EXTEND(
 mapping_id bigint not null,
 ref_category_id bigint not null,
 ref_item_id bigint not null,
 created_date date,
 user_name varchar(100),
 version bigint
);



--PK
ALTER TABLE ITEM_CATEGORY_MAPPING_EXTEND ADD PRIMARY KEY(mapping_id);

--item FK
ALTER TABLE ITEM_CATEGORY_MAPPING_EXTEND ADD CONSTRAINT MAP_EXTEND_ITEM_FK
 FOREIGN KEY (ref_item_id)
 REFERENCES ITEM_MANYTOMANY(item_id);

 --category FK
ALTER TABLE ITEM_CATEGORY_MAPPING_EXTEND ADD CONSTRAINT MAP_EXTEND_CATEGORY_FK
 FOREIGN KEY (ref_category_id)
 REFERENCES CATEGORY_MANYTOMANY(category_id);

