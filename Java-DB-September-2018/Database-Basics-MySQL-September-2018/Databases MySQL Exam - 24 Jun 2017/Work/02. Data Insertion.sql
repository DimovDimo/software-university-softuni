INSERT INTO submissions(passed_tests, problem_id, user_id)
	SELECT 
		ceil(sqrt(pow(char_length(p.name), 3)) - char_length(p.name)),
        p.id,
        ceil((p.id * 3) / 2)
	FROM problems AS p
    WHERE p.id BETWEEN 1 AND 10;