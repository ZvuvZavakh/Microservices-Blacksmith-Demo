create table blacksmith_orders
(
    id       bigint not null
        constraint blacksmith_orders_pkey
            primary key,
    order_id bigint
);

create table blacksmith_order_products
(
    id                  bigint not null
        constraint blacksmith_order_products_pkey
            primary key,
    product_id          bigint,
    quantity            integer,
    blacksmith_order_id bigint
        constraint fkbndqbc66ewnv0ctirqvua1rvd
            references blacksmith_orders
);