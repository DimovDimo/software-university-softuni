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