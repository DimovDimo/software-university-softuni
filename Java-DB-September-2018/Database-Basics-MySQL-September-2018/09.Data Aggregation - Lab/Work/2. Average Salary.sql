USE soft_uni;

SELECT department_id, round(AVG(salary),2)
FROM employees
GROUP BY department_id
ORDER BY department_id;