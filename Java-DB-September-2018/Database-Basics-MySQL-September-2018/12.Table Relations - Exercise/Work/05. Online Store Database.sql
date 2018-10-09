CREATE DATABASE online_store_database;

USE online_store_database;

CREATE TABLE cities(
	city_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE customers(
	customer_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    birthday DATE,
    city_id INT(11),
    CONSTRAINT fk_customer_city
    FOREIGN KEY (city_id)
    REFERENCES cities(city_id)
);

CREATE TABLE orders(
	order_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    customer_id INT(11),
    CONSTRAINT fk_order_custumer
    FOREIGN KEY (customer_id)
    REFERENCES customers(customer_id)
);

CREATE TABLE item_types(
	item_type_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE items(
	item_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    item_type_id INT(11),
    CONSTRAINT fk_item_type
    FOREIGN KEY (item_type_id)
    REFERENCES item_types(item_type_id)
);

CREATE TABLE order_items(
	order_id INT(11),
    item_id INT(11),
    
    CONSTRAINT pk_order_items
    PRIMARY KEY (order_id, item_id),
    
    CONSTRAINT fk_order_id
    FOREIGN KEY (order_id)
    REFERENCES orders(order_id),
    
    CONSTRAINT fk_item_id
    FOREIGN KEY (item_id)
    REFERENCES items(item_id)
);