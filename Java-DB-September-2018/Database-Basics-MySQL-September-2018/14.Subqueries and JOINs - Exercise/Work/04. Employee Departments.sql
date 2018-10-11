USE soft_uni;

SELECT 
	e.employee_id,
	e.first_name,
    e.salary,
    d.name
FROM employees e
JOIN departments d
ON e.department_id = d.department_id
WHERE e.salary > 15000
ORDER BY d.department_id DESC
LIMIT 5;