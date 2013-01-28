create table ITEM_IMAGE(
 image_id bigint not null,
 ref_item_id bigint not null,
 file_name varchar(256) not null,
 created_date date,
 version integer
);

--PK
ALTER TABLE ITEM_IMAGE ADD PRIMARY KEY(image_id);

--item FK
ALTER TABLE ITEM_IMAGE ADD CONSTRAINT ITEM_IMAGE_FK
 FOREIGN KEY (ref_item_id)
 REFERENCES ITEM(item_id);

 --map用のカラム
 alter table item_image add column image_name varchar(256);

 update item_image
 set image_name = file_name

  alter table item_image alter column image_name set not null;