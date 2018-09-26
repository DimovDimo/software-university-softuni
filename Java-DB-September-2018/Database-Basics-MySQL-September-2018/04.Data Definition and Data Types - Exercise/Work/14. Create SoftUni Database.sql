CREATE DATABASE soft_uni;

USE soft_uni;

CREATE TABLE towns    
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE addresses     
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    address_text VARCHAR(50) NOT NULL,
    town_id INT(11) NOT NULL,
    CONSTRAINT fk_town_id FOREIGN KEY(town_id) REFERENCES towns(id)
);

CREATE TABLE departments     
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE employees      
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL,
    job_title VARCHAR(50) NOT NULL,
    department_id INT(11) NOT NULL,
    hire_date DATE NOT NULL,
    salary DOUBLE(10, 2),
    address_id INT(11) NOT NULL,
    CONSTRAINT fk_department_id FOREIGN KEY(department_id) REFERENCES departments(id),
    CONSTRAINT fk_address_id FOREIGN KEY(address_id) REFERENCES addresses(id)
);