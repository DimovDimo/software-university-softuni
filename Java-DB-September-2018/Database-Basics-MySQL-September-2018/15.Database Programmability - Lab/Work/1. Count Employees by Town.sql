USE soft_uni;

CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(50))
RETURNS DOUBLE
BEGIN
	DECLARE count_result DOUBLE;
	SET count_result := (SELECT COUNT(e.employee_id)
    FROM employees e
    JOIN addresses AS a 
		ON a.address_id = e.address_id
	JOIN towns AS t 
		ON t.town_id = a.town_id
	WHERE t.name = town_name);
    RETURN count_result;
END