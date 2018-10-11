USE soft_uni;

SELECT 
	e.employee_id,
	e.first_name,
    e.last_name,
    d.name
FROM employees e
JOIN departments d
ON e.department_id = d.department_id
WHERE d.name = 'Sales'
ORDER BY e.employee_id DESC;