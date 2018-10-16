DELIMITER $$
CREATE PROCEDURE udp_evaluate(id INT)
BEGIN
	IF((SELECT s.id FROM submissions AS s WHERE s.id = id) IS NULL) THEN
		SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Submission does not exist!';
	ELSE
		INSERT INTO evaluated_submissions 
        SELECT s1.id, p.name, u.username,
			(p.points / p.tests * s1.passed_tests) AS result 
		FROM submissions AS s1 
        JOIN problems AS p ON p.id = s1.problem_id
        JOIN users AS u ON u.id = s1.user_id
        WHERE s1.id = id;
	END IF;
END $$
DELIMITER ;

CALL udp_evaluate(1);