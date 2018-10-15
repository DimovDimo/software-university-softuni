SELECT p.id,
	concat(cat.name,' - ',con.name,' - ',p.name) AS full_path
FROM problems AS p
	JOIN contests AS con ON con.id = p.contest_id
    JOIN categories AS cat ON cat.id = con.category_id
ORDER BY p.id;