CREATE DATABASE movies;

USE movies;

CREATE TABLE directors 
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    director_name VARCHAR(50) NOT NULL,
    notes TEXT
);

CREATE TABLE genres  
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    genre_name VARCHAR(50) NOT NULL,
    notes TEXT
);

CREATE TABLE categories   
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL,
    notes TEXT
);

CREATE TABLE movies    
(
	id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    director_id INT(11) NOT NULL,
    copyright_year DATE,
    length DOUBLE(10, 2), 
    genre_id INT(11) NOT NULL, 
    category_id INT(11) NOT NULL, 
    rating DOUBLE(10, 2),
    notes TEXT
);

INSERT INTO directors(director_name)
VALUES ('Pesho'), ('Pesho'), ('Pesho'), ('Pesho'), ('Pesho');

INSERT INTO genres(genre_name)
VALUES ('Pesho'), ('Pesho'), ('Pesho'), ('Pesho'), ('Pesho');

INSERT INTO categories(category_name)
VALUES ('Pesho'), ('Pesho'), ('Pesho'), ('Pesho'), ('Pesho');

INSERT INTO movies(title, director_id, genre_id, category_id)
VALUES
	('Pesho', 1, 1, 1),
    ('Pesho', 1, 1, 1),
    ('Pesho', 1, 1, 1),
    ('Pesho', 1, 1, 1),
    ('Pesho', 1, 1, 1);