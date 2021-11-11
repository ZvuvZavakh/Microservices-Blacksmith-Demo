create table products
(
    id          bigint not null
        constraint products_pkey
            primary key,
    description varchar(255),
    price       integer,
    type        varchar(255)
);

create sequence hibernate_sequence;