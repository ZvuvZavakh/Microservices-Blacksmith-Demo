create table inventory_orders
(
    id       bigint not null
        constraint inventory_orders_pkey
            primary key,
    order_id bigint
);

create table inventory_order_products
(
    id                 bigint not null
        constraint inventory_order_products_pkey
            primary key,
    product_id         bigint,
    quantity_received  integer,
    quantity_requested integer,
    order_id           bigint
        constraint fkjhraykc3qx5upei3f3p5rnf9
            references inventory_orders
);

create table inventory_products
(
    id         bigint not null
        constraint inventory_products_pkey
            primary key,
    amount     integer,
    product_id bigint
        constraint uk_eobepgy2jdbfg679jebbdnnrl
            unique
);