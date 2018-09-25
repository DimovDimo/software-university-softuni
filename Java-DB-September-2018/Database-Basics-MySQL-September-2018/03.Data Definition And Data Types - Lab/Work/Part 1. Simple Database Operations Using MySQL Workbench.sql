CREATE SCHEMA `gamebar` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `gamebar`.`employees` (
  `id` INT(11) NOT NULL,
  `first_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(50) NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `gamebar`.`categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `gamebar`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `category_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `my_fk_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `my_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `gamebar`.`categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO `gamebar`.`employees` (`id`, `first_name`, `last_name`) VALUES ('1', 'Test1', 'Test1');
INSERT INTO `gamebar`.`employees` (`id`, `first_name`, `last_name`) VALUES ('2', 'Test2', 'Test2');
INSERT INTO `gamebar`.`employees` (`id`, `first_name`, `last_name`) VALUES ('3', 'Test3', 'Test3');

DELETE FROM `gamebar`.`employees` WHERE (`id` = '2');
UPDATE `gamebar`.`employees` SET `last_name` = 'Test2' WHERE (`id` = '3');

