USE soft_uni;

SELECT COUNT(*)
FROM employees
WHERE manager_id IS NULL;