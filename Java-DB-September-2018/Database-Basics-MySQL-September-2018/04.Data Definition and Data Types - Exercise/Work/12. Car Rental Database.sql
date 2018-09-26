CREATE DATABASE car_rental;

USE car_rental;

CREATE TABLE categories 
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    category VARCHAR(50) NOT NULL,
    daily_rate DOUBLE(10, 2), 
    weekly_rate DOUBLE(10, 2),
    monthly_rate DOUBLE(10, 2),
    weekend_rate DOUBLE(10, 2)
);

CREATE TABLE cars  
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    plate_number INT NOT NULL, 
    make VARCHAR(50), 
    model VARCHAR(50) NOT NULL,
    car_year DATE, 
    category_id INT(11) NOT NULL, 
    doors INT, 
    picture BLOB, 
    car_condition TEXT, 
    available BOOLEAN 
);

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
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    driver_licence_number LONG NOT NULL, 
    full_name VARCHAR(150) NOT NULL, 
    address VARCHAR(100), 
    city VARCHAR(50), 
    zip_code VARCHAR(100), 
    notes TEXT
);

CREATE TABLE rental_orders     
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    employee_id INT(11) NOT NULL, 
    customer_id INT(11) NOT NULL, 
    car_id INT(11) NOT NULL, 
    car_condition TEXT, 
    tank_level INT, 
    kilometrage_start INT, 
    kilometrage_end INT, 
    total_kilometrage INT, 
    start_date DATE, 
    end_date DATE, 
    total_days INT, 
    rate_applied DOUBLE(10, 2), 
    tax_rate DOUBLE(10, 2), 
    order_status TEXT, 
    notes TEXT
);

INSERT INTO categories(category)
VALUES ('Lorem ipsum'), ('Lorem ipsum'), ('Lorem ipsum');

INSERT INTO cars(plate_number, model, category_id)
VALUES (1, 'Lorem ipsum', 1), (1, 'Lorem ipsum', 1), (1, 'Lorem ipsum', 1);

INSERT INTO employees(first_name, last_name)
VALUES ('Lorem', 'Ipsum'), ('Lorem', 'Ipsum'), ('Lorem', 'Ipsum');

INSERT INTO customers(driver_licence_number, full_name)
VALUES (1, 'Lorem ipsum'), (1, 'Lorem ipsum'), (1, 'Lorem ipsum');

INSERT INTO rental_orders(employee_id, customer_id, car_id)
VALUES (1, 1, 1), (1, 1, 1), (1, 1, 1);