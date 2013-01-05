create table ITEM_CATEGORY_MAPPING(
 mapping_id bigint not null,
 category_id bigint not null,
 item_id bigint not null,
 created_date date
);

--PK
ALTER TABLE ITEM_CATEGORY_MAPPING ADD PRIMARY KEY(mapping_id);

--item FK
ALTER TABLE ITEM_CATEGORY_MAPPING ADD CONSTRAINT ITEM_FK
 FOREIGN KEY (item_id)
 REFERENCES ITEM(item_id);
 
 --category FK
ALTER TABLE ITEM_CATEGORY_MAPPING ADD CONSTRAINT CATEGORY_FK
 FOREIGN KEY (category_id)
 REFERENCES CATEGORY(category_id);