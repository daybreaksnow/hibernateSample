create table USER_COMPONENT(
 user_id bigint not null,
 username varchar(30),
 home_street varchar(250),
 home_city varchar(250),
 home_zip_code varchar(10),
 billing_street varchar(250),
 billing_city varchar(250),
 billing_zip_code varchar(10)
)