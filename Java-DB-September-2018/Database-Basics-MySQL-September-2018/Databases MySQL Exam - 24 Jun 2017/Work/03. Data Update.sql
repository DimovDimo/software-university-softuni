UPDATE problems AS p
	JOIN contests AS con ON con.id = p.contest_id
    JOIN categories AS cat ON cat.id = con.category_id
SET p.tests = CASE(p.id % 3)
		WHEN 0 THEN LENGTH(cat.name)
        WHEN 1 THEN (SELECT SUM(s.id) FROM submissions AS s WHERE p.id = s.problem_id)
        WHEN 2 THEN LENGTH(con.name)
	END
WHERE p.tests = 0;