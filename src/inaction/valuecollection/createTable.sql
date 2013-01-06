create table ITEM_IMAGE(
 image_id bigint not null,
 item_id bigint not null,
 file_name varchar(256) not null,
 created_date date,
 version integer
);

--PK
ALTER TABLE ITEM_IMAGE ADD PRIMARY KEY(image_id);

--item FK
ALTER TABLE ITEM_IMAGE ADD CONSTRAINT ITEM_IMAGE_FK
 FOREIGN KEY (item_id)
 REFERENCES ITEM(item_id);
 