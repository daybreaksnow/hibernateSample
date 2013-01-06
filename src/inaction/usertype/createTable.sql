create table BID_USERTYPE(
 bid_id bigint not null,
 amount bigint,
 created_date date,
 version integer
);

--Bid PK
ALTER TABLE BID_USERTYPE ADD PRIMARY KEY(bid_id);
