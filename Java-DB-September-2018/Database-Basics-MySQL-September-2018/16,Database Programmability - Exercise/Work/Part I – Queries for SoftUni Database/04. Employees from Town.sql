USE soft_uni;

DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(target_town VARCHAR(20))
BEGIN
	SELECT first_name, last_name
	FROM employees AS e
    JOIN addresses AS a ON a.address_id = e.address_id
    JOIN towns AS t ON t.town_id = a.town_id
	WHERE t.name = target_town
	ORDER BY first_name, last_name, employee_id;
END $$

CALL usp_get_employees_from_town('Sofia');