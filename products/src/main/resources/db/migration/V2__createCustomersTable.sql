create table customers
(
    id      bigint not null
        constraint customers_pkey
            primary key,
    deposit integer,
    name    varchar(255)
);