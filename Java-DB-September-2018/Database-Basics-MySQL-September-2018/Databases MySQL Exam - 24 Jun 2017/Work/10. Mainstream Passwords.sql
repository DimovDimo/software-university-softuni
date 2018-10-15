SELECT u.id, u.username, u.password
FROM users AS u
WHERE 1 < (
	SELECT COUNT(u2.password) 
	FROM users AS u2 
    WHERE u.password = u2.password)
ORDER BY u.username, u.id;