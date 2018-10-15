SELECT *
FROM
	(SELECT c.id, c.name,
		COUNT(uc.user_id) AS contestants
	FROM contests AS c
	LEFT JOIN users_contests AS uc ON uc.contest_id = c.id
	GROUP BY uc.contest_id
	ORDER BY contestants DESC, c.id DESC
	LIMIT 5) AS result
ORDER BY contestants , id;