SELECT c.id, c.name 
FROM categories AS c
LEFT JOIN categories AS c2 ON c.id = c2.parent_id
WHERE ISNULL(c2.id)
ORDER BY c.name, c.id;