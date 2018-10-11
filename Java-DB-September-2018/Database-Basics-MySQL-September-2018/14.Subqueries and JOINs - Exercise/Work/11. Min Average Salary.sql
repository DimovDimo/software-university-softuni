USE soft_uni;

SELECT AVG(e.salary) AS min_average_salary
FROM employees e
GROUP BY e.department_id
ORDER BY min_average_salary
LIMIT 1;