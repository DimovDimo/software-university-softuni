USE geography;

SELECT 
	c.continent_code, 
    c.currency_code, 
    COUNT(c.currency_code) AS curr_usage
FROM countries AS c
GROUP BY c.continent_code, c.currency_code
HAVING curr_usage > 1 
	AND curr_usage = (
			SELECT COUNT(c1.currency_code) AS c_count
			FROM countries AS c1
			WHERE c1.continent_code = c.continent_code
			GROUP BY c1.currency_code
			ORDER BY c_count DESC
			LIMIT 1)
ORDER BY c.continent_code, c.currency_code;