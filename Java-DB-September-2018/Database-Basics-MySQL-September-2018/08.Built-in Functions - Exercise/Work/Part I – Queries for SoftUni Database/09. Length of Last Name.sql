USE soft_uni;

SELECT first_name, last_name
FROM employees
WHERE char_length(last_name) = 5;