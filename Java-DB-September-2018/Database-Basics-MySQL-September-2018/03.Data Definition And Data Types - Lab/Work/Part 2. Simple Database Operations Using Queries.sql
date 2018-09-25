CREATE SCHEMA `gamebar` DEFAULT CHARACTER SET utf8 ;

USE `gamebar`;

CREATE TABLE `employees`(
`id` INT AUTO_INCREMENT PRIMARY KEY,
`first_name` VARCHAR(15) NOT NULL,
`last_name` VARCHAR(15) NOT NULL
);

CREATE TABLE `categories`(
`id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(15) NOT NULL
);

CREATE TABLE `products`(
`id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(15) NOT NULL,
`category_id` INT NOT NULL
);

INSERT INTO employees(first_name,last_name) VALUES ('A','B'),('A','B'),('A','B');

ALTER TABLE employees
ADD `middle_name` VARCHAR(15) NOT NULL;

ALTER TABLE products
ADD CONSTRAINT `my_fk`
FOREIGN KEY(`category_id`)
REFERENCES categories(id);

ALTER TABLE employees
MODIFY COLUMN `middle_name` VARCHAR(100);

DROP DATABASE gamebar;