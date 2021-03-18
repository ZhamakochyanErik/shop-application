create table shop_user(
    id bigserial not null ,
    name varchar(255) not null ,
    username varchar(255) not null ,
    password varchar(255) not null ,
    user_role varchar(5) not null ,
    blocked boolean not null ,
    registered_date timestamp not null,
    CONSTRAINT shop_user_pkey PRIMARY KEY (id)
);

create table product(
    id bigserial not null ,
    name varchar(255) not null ,
    price double precision not null ,
    description text ,
    created_date timestamp not null,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

create table product_category(
    id bigserial not null ,
    name varchar(255) not null ,
    CONSTRAINT product_category_pkey PRIMARY KEY (id)
);

create table products_categories(
    product_id bigserial not null ,
    category_id bigserial not null ,
    FOREIGN KEY (product_id) REFERENCES product(id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES product_category(id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

create table product_comment(
    id bigserial not null ,
    comment text not null ,
    created_date timestamp not null,
    product_id bigserial not null ,
    user_id bigserial not null ,
    FOREIGN KEY (product_id) REFERENCES product(id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES shop_user(id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT product_comment_pkey PRIMARY KEY (id)
);

create table product_rate(
    id bigserial not null ,
    rate int not null ,
    created_date timestamp not null,
    product_id bigserial not null ,
    user_id bigserial not null ,
    FOREIGN KEY (product_id) REFERENCES product(id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES shop_user(id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT product_rate_pkey PRIMARY KEY (id)
);
