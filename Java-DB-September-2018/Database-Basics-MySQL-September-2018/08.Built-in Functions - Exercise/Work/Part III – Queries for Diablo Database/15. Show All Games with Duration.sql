USE diablo;

SELECT name,
	   IF(hour(start) < 12, 'Morning',
			IF(hour(start) < 18, 'Afternoon', 
				'Evening')) AS 'Part of the Day',                
	   IF(duration <= 3, 'Extra Short',
			IF(duration <= 6, 'Short',
				IF(duration <= 10, 'Long',
					'Extra Long'))) AS 'Duration'
FROM games;