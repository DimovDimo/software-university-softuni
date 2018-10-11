USE soft_uni;

SELECT 
	e.employee_id,
	e.first_name,
	IF(YEAR(p.start_date)>=2005,NULL,p.name)
FROM employees e
LEFT JOIN employees_projects ep
ON e.employee_id = ep.employee_id
LEFT JOIN projects p
ON ep.project_id = p.project_id
WHERE e.employee_id = 24
ORDER BY p.name;