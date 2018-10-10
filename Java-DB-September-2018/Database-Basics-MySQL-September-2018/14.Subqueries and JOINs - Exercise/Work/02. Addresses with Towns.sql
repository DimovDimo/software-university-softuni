USE soft_uni;

SELECT 
	e.first_name,
    e.last_name,
    t.name,
    a.address_text
FROM employees e
JOIN addresses a
ON e.address_id = a.address_id
JOIN towns t
ON a.town_id = t.town_id
ORDER BY e.first_name, e.last_name
LIMIT 5;