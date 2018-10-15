SELECT c.id, c.name, COUNT(s.id) AS 'submissions'
FROM contests AS c
JOIN problems AS p ON p.contest_id = c.id
JOIN submissions AS s ON s.problem_id = p.id
JOIN users_contests AS uc ON uc.contest_id = c.id
WHERE s.user_id = uc.user_id
GROUP BY c.id
ORDER BY submissions DESC, c.id;