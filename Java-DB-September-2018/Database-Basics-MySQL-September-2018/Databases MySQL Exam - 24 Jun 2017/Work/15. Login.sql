DELIMITER $$
CREATE PROCEDURE udp_login(username VARCHAR(30), password VARCHAR(30))
BEGIN
	IF((SELECT u.id FROM users AS u WHERE u.username = username) IS NULL) THEN
		SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Username does not exist!';
	ELSEIF((SELECT u2.id FROM users AS u2 WHERE u2.username = username AND u2.password = password) IS NULL) THEN
		SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Password is incorrect!';
	ELSEIF((SELECT lu.id FROM logged_in_users AS lu WHERE lu.username = username) IS NOT NULL) THEN
		SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'User is already logged in!';
	ELSE
		INSERT INTO logged_in_users
        SELECT * FROM users AS u3 WHERE u3.username = username;
	END IF;
END $$
DELIMITER ;

CALL udp_login('doge', 'doge');