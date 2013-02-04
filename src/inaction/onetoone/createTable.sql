create table USER_ONETOONE(
 user_id bigint not null,
 username varchar(30),
 home_address_id bigint,
 billing_address_id bigint,
 version bigint
)
;

--User PK
ALTER TABLE USER_ONETOONE ADD PRIMARY KEY(user_id);

create table ADDRESS_ONETOONE(
 address_id bigint not null,
 street varchar(250),
 city varchar(250),
 zip_code varchar(10),
 version bigint
)
;
--Address PK
ALTER TABLE ADDRESS_ONETOONE ADD PRIMARY KEY(address_id);

--FK
ALTER TABLE USER_ONETOONE ADD CONSTRAINT USER_ADDRESS_ONETOONE_FK
 FOREIGN KEY (home_address_id)
 REFERENCES ADDRESS_ONETOONE(address_id);
 
 --FK
ALTER TABLE USER_ONETOONE ADD CONSTRAINT USER_ADDRESS_ONETOONE_HOME_FK
 FOREIGN KEY (home_address_id)
 REFERENCES ADDRESS_ONETOONE(address_id);
 
  --FK
ALTER TABLE USER_ONETOONE ADD CONSTRAINT USER_ADDRESS_ONETOONE_BILLING_FK
 FOREIGN KEY (billing_address_id)
 REFERENCES ADDRESS_ONETOONE(address_id);