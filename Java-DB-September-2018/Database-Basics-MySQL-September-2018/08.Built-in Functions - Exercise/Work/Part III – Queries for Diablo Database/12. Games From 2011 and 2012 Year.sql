USE diablo;

SELECT name, DATE_FORMAT(start, '%Y-%m-%d') AS 'start'
FROM games
WHERE year(start) BETWEEN 2011 AND 2012
ORDER BY DATE(start), name
LIMIT 50;