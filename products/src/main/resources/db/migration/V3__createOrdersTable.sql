create table orders
(
    id           bigint not null
        constraint orders_pkey
            primary key,
    customer_id  bigint,
    order_status varchar(255)
);

create table order_products
(
    id         bigint not null
        constraint order_products_pkey
            primary key,
    product_id bigint,
    quantity   integer,
    order_id   bigint
        constraint fkawxpt1ns1sr7al76nvjkv21of
            references orders
);