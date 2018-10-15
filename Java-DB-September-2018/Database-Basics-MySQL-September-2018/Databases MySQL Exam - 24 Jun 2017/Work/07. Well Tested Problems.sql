SELECT id, name, tests
FROM problems
WHERE tests > points
	AND (LENGTH(name) - LENGTH(REPLACE(name, ' ', ''))) = 1
ORDER BY id DESC;