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