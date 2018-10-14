USE soft_uni;

DELIMITER $$
CREATE FUNCTION ufn_get_salary_level(salary DOUBLE(10,4))
RETURNS VARCHAR(7)
BEGIN
	DECLARE salary_level VARCHAR(7);
	SET salary_level := (CASE
		WHEN salary < 30000 THEN 'Low'
        WHEN salary > 50000 THEN'High'
        ELSE 'Average'
    END);
    RETURN salary_level;
END $$