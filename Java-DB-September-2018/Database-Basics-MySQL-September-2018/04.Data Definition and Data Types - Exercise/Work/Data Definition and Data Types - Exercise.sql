/*Create Database*/
CREATE DATABASE minions;

USE minions;

/*01. Create Tables*/
CREATE TABLE minions(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT
);

CREATE TABLE towns (
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

/*02. Alter Minions Table */
ALTER TABLE minions
ADD town_id INT;

ALTER TABLE minions
ADD CONSTRAINT fk_towns_id
FOREIGN KEY (town_id)
REFERENCES towns(id);

#03. Insert Records in Both Tables 
INSERT INTO towns(id, name)
VALUES (1, 'Sofia'),(2, 'Plovdiv'),(3, 'Varna');

INSERT INTO minions(id,name, age, town_id)
VALUES (1, 'Kevin', 22, 1), (2, 'Bob', 15, 3), 
(3, 'Steward', NULL, 2);

#04. Truncate Table Minions 
TRUNCATE TABLE minions;

#05. Drop All Tables 
DROP TABLE minions;
DROP TABLE towns;

#06. Create Table People 
CREATE TABLE people(
id INT(11) PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
name VARCHAR(200) NOT NULL,
picture MEDIUMBLOB,
height DOUBLE(10,2),
weight DOUBLE(10,2),
gender CHAR(1) NOT NULL,
birthdate DATE NOT NULL,
biography TEXT
);

INSERT INTO people(id,name,gender,birthdate)
VALUES (1, 'Person1', 'm', '2000-01-01'),
(2, 'Person2', 'f', '2000-01-01'),
(3, 'Person3', 'f', '2001-01-01'),
(4, 'Person4', 'm', '2002-01-01'),
(5, 'Person5', 'm', '2005-01-01');

#07. Create Table Users 
CREATE TABLE users(
id INT(22) PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
username  VARCHAR(30) UNIQUE NOT NULL,
password  VARCHAR(26) NOT NULL,
profile_picture BLOB,
last_login_time DATETIME,
is_deleted BOOLEAN
);

INSERT INTO users(id,username,password)
VALUES (1, 'Person1', 'password'),
(2, 'Person2', 'password'),
(3, 'Person3', 'password'),
(4, 'Person4', 'password'),
(5, 'Person5', 'password');