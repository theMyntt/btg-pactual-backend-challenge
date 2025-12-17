CREATE TABLE tbl_users (
    id_user INTEGER NOT NULL,
    tx_name VARCHAR(255),
    tx_email VARCHAR(255),
    tx_phone VARCHAR(255),
    PRIMARY KEY (id_user)
);

CREATE TABLE tbl_orders (
    id_order INTEGER NOT NULL,
    fk_id_user INTEGER NOT NULL,
    PRIMARY KEY (id_order),
    FOREIGN KEY (fk_id_user) REFERENCES tbl_users(id_user)
);

CREATE TABLE tbl_products (
    id_product INTEGER AUTO_INCREMENT,
    tx_name VARCHAR(255) NOT NULL,
    tx_price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id_product)
);

CREATE TABLE tbl_order_items (
    id_item INTEGER AUTO_INCREMENT,
    fk_id_order INTEGER NOT NULL,
    fk_id_product INTEGER NOT NULL,
    PRIMARY KEY (id_item),
    FOREIGN KEY (fk_id_order) REFERENCES tbl_orders(id_order),
    FOREIGN KEY (fk_id_product) REFERENCES tbl_products(id_product)
);
