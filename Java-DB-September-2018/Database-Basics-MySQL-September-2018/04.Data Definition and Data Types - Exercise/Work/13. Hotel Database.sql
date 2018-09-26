CREATE DATABASE hotel;

USE hotel;

CREATE TABLE employees   
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL, 
    last_name VARCHAR(50) NOT NULL, 
    title VARCHAR(50), 
    notes TEXT
);

CREATE TABLE customers    
(
	account_number INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL, 
    last_name VARCHAR(50) NOT NULL, 
    phone_number VARCHAR(20), 
    emergency_name VARCHAR(50), 
    emergency_number VARCHAR(20), 
    notes TEXT
);

CREATE TABLE room_status   
(
	`room_status` VARCHAR(15) PRIMARY KEY,
    notes TEXT
);

CREATE TABLE room_types    
(
	`room_type` VARCHAR(15) PRIMARY KEY,
    notes TEXT
);

CREATE TABLE bed_types     
(
	bed_type VARCHAR(15) PRIMARY KEY,
    notes TEXT
);

CREATE TABLE rooms     
(
	room_number INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    room_type VARCHAR(15), 
    bed_type VARCHAR(15), 
    rate DOUBLE(10, 2), 
    room_status VARCHAR(15), 
    notes TEXT
);

CREATE TABLE payments     
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    employee_id INT(11), 
    payment_date DATE,
    account_number INT(11),
    first_date_occupied DATE, 
    last_date_occupied DATE, 
    total_days INT, 
    amount_charged DOUBLE(10, 2), 
    tax_rate DOUBLE(10, 2), 
    tax_amount DOUBLE(10, 2), 
    payment_total DOUBLE(10, 2), 
    notes TEXT
);

CREATE TABLE occupancies      
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    employee_id INT(11), 
    date_occupied DATE, 
    account_number INT(11),
    room_number INT(11),
    rate_applied DOUBLE(10, 2), 
    phone_charge VARCHAR(20), 
    notes TEXT
);

INSERT INTO employees(first_name, last_name)
VALUES ('Lorem', 'Ipsum'), ('Lorem', 'Ipsum'), ('Lorem', 'Ipsum');

INSERT INTO customers (first_name, last_name)
VALUES ('Lorem', 'Ipsum'), ('Lorem', 'Ipsum'), ('Lorem', 'Ipsum');

INSERT INTO room_status(room_status)
VALUES ('a'), ('b'), ('c');

INSERT INTO room_types(room_type)
VALUES ('a'), ('b'), ('c');

INSERT INTO bed_types(bed_type)
VALUES ('a'), ('b'), ('c');

INSERT INTO rooms() VALUES (),(),();

INSERT INTO payments() VALUES (),(),();

INSERT INTO occupancies() VALUES (),(),();