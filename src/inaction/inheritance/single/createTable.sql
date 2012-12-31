create table BILLING_DETAILS_SINGLE(
 billing_details_id bigint not null,
 billing_details_type varchar(30),
 owner varchar(30),
 created date,
 credit_card_type integer,
 credit_card_exp_month char(2),
 credit_card_exp_year char(4),
 bank_account_bank_name varchar(250),
 bank_account_bank_swift varchar(30)
)