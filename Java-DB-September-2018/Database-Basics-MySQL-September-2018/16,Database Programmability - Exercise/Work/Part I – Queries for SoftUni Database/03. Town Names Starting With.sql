USE soft_uni;

DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(target_starting_string VARCHAR(20))
BEGIN
	SELECT t.name
	FROM towns t
	WHERE t.name LIKE CONCAT(target_starting_string,'%')
	ORDER BY t.name;
END $$

CALL usp_get_towns_starting_with('b');