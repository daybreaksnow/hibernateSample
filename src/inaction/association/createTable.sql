create table BID(
 bid_id bigint not null unique,
 amount bigint,
 item_id bigint,
 user_id bigint,
 created_date date
);

create table ITEM(
 item_id bigint not null unique,
 name varchar(256),
 description varchar(256),
 initial_price bigint,
 reserve_price bigint,
 start_date date,
 end_date date,
 created_date date 
);

--Bid PK
ALTER TABLE BID ADD constraint BID_PK unique (bid_id);

--Item PK
ALTER TABLE ITEM ADD constraint ITEM_PK unique (item_id);

--Bid to Item　FK
ALTER TABLE BID ADD CONSTRAINT BID_ITEM_FK
 FOREIGN KEY (item_id)
 REFERENCES ITEM(item_id);