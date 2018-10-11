USE geography;

SELECT 
	c.country_name,
    MAX(p.elevation) AS highest_peak_elevation,
    MAX(r.length) AS longest_river_length
FROM countries c
JOIN mountains_countries mc
	ON c.country_code = mc.country_code
JOIN mountains m
	ON mc.mountain_id = m.id
JOIN peaks p
	ON p.mountain_id = m.id
LEFT JOIN countries_rivers cr
	ON c.country_code = cr.country_code
LEFT JOIN rivers r
	ON cr.river_id = r.id
GROUP BY c.country_name
ORDER BY 
	highest_peak_elevation DESC,
    longest_river_length DESC,
    c.country_name
LIMIT 5;