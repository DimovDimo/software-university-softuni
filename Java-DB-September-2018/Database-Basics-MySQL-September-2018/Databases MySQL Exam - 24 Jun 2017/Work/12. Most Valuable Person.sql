SELECT s.id, u.username, p.name,
	concat_ws(' / ', s.passed_tests, p.tests) AS result
FROM (SELECT user_id, COUNT(contest_id) AS count_contest_id
		FROM users_contests
		GROUP BY user_id
		ORDER BY count_contest_id DESC
        LIMIT 1) AS m
JOIN users AS u ON u.id = m.user_id
JOIN submissions AS s ON u.id = s.user_id
JOIN problems AS p ON p.id = s.problem_id
ORDER BY s.id DESC;