CREATE DATABASE report_service;

USE report_service;

# Section 1. DDL (30 pts)

CREATE TABLE users
(
	id INT(11) UNSIGNED UNIQUE NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) UNIQUE,
	password VARCHAR(50) NOT NULL,
    name VARCHAR(50),
    gender VARCHAR(1),
    birthdate DATETIME,
    age INT(11) UNSIGNED,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE departments
(
	id INT(11) UNSIGNED UNIQUE NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE employees
(
	id INT(11) UNSIGNED UNIQUE NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    gender VARCHAR(1),
    birthdate DATETIME,
    age INT(11) UNSIGNED,
    department_id INT(11) UNSIGNED,
    CONSTRAINT fk_employees_departments
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE categories
(
	id INT(11) UNSIGNED UNIQUE NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    department_id INT(11) UNSIGNED,
    CONSTRAINT fk_categories_departments
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE status
(
	id INT(11) UNSIGNED UNIQUE NOT NULL PRIMARY KEY AUTO_INCREMENT,
	label VARCHAR(30) NOT NULL
);

CREATE TABLE reports
(
	id INT(11) UNSIGNED UNIQUE NOT NULL PRIMARY KEY AUTO_INCREMENT,
    category_id INT(11) UNSIGNED,
    status_id INT(11) UNSIGNED,
    open_date DATETIME,
    close_date DATETIME,
    description VARCHAR(200),
    user_id INT(11) UNSIGNED,
    employee_id INT(11) UNSIGNED,
    
    CONSTRAINT fk_reports_categories
    FOREIGN KEY (category_id) REFERENCES categories(id),
    
    CONSTRAINT fk_reports_status
    FOREIGN KEY (status_id) REFERENCES status(id),
    
    CONSTRAINT fk_reports_users
    FOREIGN KEY (user_id) REFERENCES users(id),
    
    CONSTRAINT fk_reports_employees
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

# Section 2. DML (10 pts)

# 2. Insert 

INSERT INTO employees(first_name, last_name, gender, birthdate, department_id)
VALUES
	('Marlo', 'O''Malley', 'M', '1958-21-09', 1),
    ('Niki', 'Stanaghan', 'F', '1969-26-11', 4),
    ('Ayrton', 'Senna', 'M', '1960-21-03', 9),
    ('Ronnie', 'Peterson', 'M', '1944-14-02', 9),
    ('Giovanna', 'Amati', 'F', '1959-20-07', 5);
    
INSERT INTO reports(category_id, status_id, open_date, close_date, description, user_id, employee_id)
VALUES
	(1, 1,'2017-13-04', NULL, 'Stuck Road on Str.133', 6, 2),
    (6, 3,'2015-05-09', '2015-06-12', 'SCharity trail running', 3, 5),
    (14, 2,'2015-07-09', NULL, 'Falling bricks on Str.58', 5, 2),
    (4, 3,'2017-03-07', '2017-06-07', 'Cut off streetlight on Str.11', 1, 1);  

# 3. Update

UPDATE reports
SET status_id = 2
WHERE status_id = 1 AND category_id = 4;

# 4. Delete 

DELETE FROM reports
WHERE status_id = 4;

# Section 3. Querying (40 pts)

# 5. Users by Age 

SELECT username, age
FROM users
ORDER BY age, username DESC;

# 6. Unassigned Reports 

SELECT description, open_date
FROM reports
WHERE employee_id IS NULL
ORDER BY open_date, description;

# 7. Employees and Reports

SELECT e.first_name, e.last_name, r.description, 
	date_format(r.open_date, '%Y-%m-%d') AS open_date
FROM employees AS e
JOIN reports AS r ON e.id = r.employee_id
ORDER BY r.employee_id, open_date, r.id;

# 8. Most Reported Category 

SELECT c.name, COUNT(r.id) AS reports_number
FROM categories AS c
JOIN reports AS r ON r.category_id = c.id
GROUP BY c.id
ORDER BY reports_number, c.name;

# 9. One Category Employees 

SELECT c.name, COUNT(c.id) AS employees_number
FROM categories AS c
JOIN departments AS d ON d.id = c.department_id
JOIN employees AS e ON e.department_id = d.id
GROUP BY c.id
ORDER BY c.name;

# 10. Birthday Report 

SELECT c.name
FROM categories AS c
JOIN reports AS r ON r.category_id = c.id
JOIN users AS u ON u.id = r.user_id
WHERE date_format(r.open_date, '%m-%d') = date_format(u.birthdate, '%m-%d')
GROUP BY c.id
ORDER BY c.name;

# 11. Users per Employee 

SELECT 
	concat_ws(' ', e.first_name, e.last_name) AS name,
    COUNT(e.id) AS users_count
FROM employees AS e
JOIN reports AS r ON r.employee_id = e.id
JOIN users AS u ON u.id = r.user_id
GROUP BY e.id
ORDER BY users_count DESC, name;
 #--------
SELECT 
	concat_ws(' ', e.first_name, e.last_name) AS name,
    COUNT(DISTINCT(r.user_id)) AS users_count
FROM employees AS e
LEFT OUTER JOIN reports AS r ON r.employee_id = e.id
GROUP BY e.id
ORDER BY users_count DESC, name;

# 12. Emergency Patrol 

SELECT r.open_date, r.description, 
	u.email AS 'reporter_email'
FROM reports AS r
JOIN users AS u ON u.id = r.user_id
JOIN categories AS c ON c.id = r.category_id
JOIN departments AS d ON d.id = c.department_id
WHERE r.close_date IS NULL
	AND length(r.description) > 20
    AND r.description LIKE '%str%'
    AND d.name IN ('Infrastructure', 'Emergency', 'Roads Maintenance')
ORDER BY r.open_date, u.email, r.id;

# 13. Numbers Coincidence 

SELECT DISTINCT(u.username)
FROM users AS u
JOIN reports AS r ON r.user_id = u.id
JOIN categories AS c ON c.id = r.category_id
WHERE (left(u.username, 1) REGEXP '^[0-9]'
		AND SUBSTRING(u.username, 1, 1) = c.id)
	OR (right(u.username, 1) REGEXP '^[0-9]'
		AND SUBSTRING(u.username, char_length(u.username), 1) = c.id)
ORDER BY u.username;

# 14. Open/Closed Statistics 

SELECT 
	g.name,
    concat_ws('/', g.close, g.open) AS closed_open_reports
FROM 
	(
		SELECT 
			concat_ws(' ', e.first_name, e.last_name) AS name,
            COUNT(IF(year(r.close_date) = 2016, 1, NULL)) AS close,
            COUNT(IF(year(r.open_date) = 2016, 1, NULL)) AS open
        FROM reports AS r
		JOIN employees AS e ON r.employee_id = e.id
        GROUP BY name
        HAVING close > 0 OR open > 0
    ) AS g
ORDER BY name;

# 15. Average Closing Time 

SELECT d.name, 
	IF(f.avg_date_diff IS NULL, 'no info', f.avg_date_diff) AS average_duration
FROM (
	SELECT d.id AS id, d.name, 
		floor(AVG(DATEDIFF(r.close_date, r.open_date))) AS avg_date_diff
	FROM reports AS r
	JOIN categories AS c ON c.id = r.category_id
	JOIN departments AS d ON d.id = c.department_id
	WHERE r.close_date IS NOT NULL
	GROUP BY d.name
	) AS f
RIGHT JOIN (
	SELECT d.id, d.name, COUNT(d.id)
	FROM reports AS r
	JOIN categories AS c ON c.id = r.category_id
	JOIN departments AS d ON d.id = c.department_id
	WHERE r.open_date IS NOT NULL
	GROUP BY d.name
) AS d ON d.id = f.id
ORDER BY d.name;

# 16. Most Reported Category 

SELECT 
	DISTINCT(d.name) AS department_name,
    c.name AS category_name,
    round((COUNT(c.name) / m.max_sum) * 100) AS percentage
FROM reports AS r
JOIN categories AS c ON c.id = r.category_id
JOIN departments AS d ON d.id = c.department_id
JOIN (
	SELECT 
	d.id AS id,
    COUNT(c.name) AS max_sum
	FROM reports AS r
	JOIN categories AS c ON c.id = r.category_id
	JOIN departments AS d ON d.id = c.department_id
	GROUP BY d.name
) AS m ON m.id = d.id
GROUP BY c.name
ORDER BY d.name, c.name, percentage;

# 17. Get Reports 

CREATE FUNCTION udf_get_reports_count(employee_id INT, status_id INT) 
RETURNS INT
RETURN (
		SELECT COUNT(r.id)
		FROM reports AS r
		WHERE r.employee_id = employee_id 
			AND r.status_id = status_id);

SELECT id, first_name, last_name, 
	udf_get_reports_count(id, 2) AS reports_count
FROM employees AS e
ORDER BY e.id;

# 18. Assign Employee

DELIMITER $$
CREATE PROCEDURE usp_assign_employee_to_report(employee_id INT, report_id INT)
BEGIN
	DECLARE employee_department_id INT DEFAULT (SELECT department_id FROM employees WHERE id = employee_id);
    DECLARE report_category_id INT DEFAULT (SELECT r.category_id FROM reports AS r WHERE r.id = report_id);
    DECLARE category_department_id INT DEFAULT (SELECT department_id FROM categories WHERE id = report_category_id);

	START TRANSACTION;
	IF(employee_department_id != category_department_id) THEN
		SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'Employee doesn''t belong to the appropriate department!';
		ROLLBACK;
    ELSE
		UPDATE reports AS r
        SET r.employee_id = employee_id
        WHERE r.id = report_id;
        COMMIT;
    END IF;
END $$
DELIMITER ;

# 19. Close Reports 

DELIMITER $$
CREATE TRIGGER tr_close_reports 
BEFORE UPDATE ON reports
FOR EACH ROW
BEGIN
	IF(NEW.close_date IS NOT NULL) THEN
        SET NEW.status_id := 3;
    END IF;
END $$
DELIMITER ;

# 20. Categories Revision 

SELECT category_name, reports_number,
	IF(waiting_count > in_progress_count, 'waiting',
		IF(waiting_count < in_progress_count, 'in progress', 'equal')) AS main_status
FROM
	(SELECT c.name AS category_name, 
		COUNT(r.status_id) AS reports_number,
		COUNT(IF(r.status_id = 1, 1, NULL)) AS waiting_count,
		COUNT(IF(r.status_id = 2, 1, NULL)) AS in_progress_count
	FROM categories AS c
	JOIN reports AS r ON r.category_id = c.id
	WHERE r.status_id IN (1, 2)
	GROUP BY c.name) AS m
ORDER BY category_name;