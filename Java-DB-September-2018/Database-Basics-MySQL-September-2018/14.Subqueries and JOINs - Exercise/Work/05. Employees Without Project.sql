USE soft_uni;

SELECT 
	e.employee_id,
	e.first_name
FROM employees e
LEFT JOIN employees_projects p
ON e.employee_id = p.employee_id
WHERE p.employee_id IS NULL
ORDER BY e.employee_id DESC
LIMIT 3;