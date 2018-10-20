CREATE DATABASE the_nerd_herd;

USE the_nerd_herd;

# Section I: Data Definition Language (DDL) – 30 pts

# 01. Table Design 

CREATE TABLE locations
(
	id INT(11) NOT NULL PRIMARY KEY,
    latitude FLOAT,
    longitude FLOAT
);

CREATE TABLE credentials
(
	id INT(11) NOT NULL PRIMARY KEY,
    email VARCHAR(30),
    password VARCHAR(20)
);

CREATE TABLE users
(
	id INT(11) NOT NULL PRIMARY KEY,
    nickname VARCHAR(25),
    gender CHAR(1),
    age INT(11),
    location_id INT(11),
    credential_id INT(11) UNIQUE,
    
    CONSTRAINT fk_users_locations
    FOREIGN KEY (location_id)
    REFERENCES locations(id),
    
    CONSTRAINT fk_users_credentials
    FOREIGN KEY (credential_id)
    REFERENCES credentials(id)
);

CREATE TABLE chats
(
	id INT(11) NOT NULL PRIMARY KEY,
    title VARCHAR(32),
    start_date DATE,
    is_active BIT
);

CREATE TABLE messages
(
	id INT(11) NOT NULL PRIMARY KEY,
    content VARCHAR(200),
    sent_on DATE,
    chat_id INT(11),
    user_id INT(11),
    
    CONSTRAINT fk_messages_chats
    FOREIGN KEY (chat_id)
    REFERENCES chats(id),
    
    CONSTRAINT fk_messages_users
    FOREIGN KEY (user_id)
    REFERENCES users(id)
);

CREATE TABLE users_chats
(
	user_id INT(11) UNIQUE,
    chat_id INT(11) UNIQUE,    
    
    CONSTRAINT pk_chat_id_user_id
    PRIMARY KEY (chat_id, user_id),
    
    CONSTRAINT fk_users_chats_chats
    FOREIGN KEY (chat_id)
    REFERENCES chats(id),
    
    CONSTRAINT fk_users_chats_users
    FOREIGN KEY (user_id)
    REFERENCES users(id)
);

# Section 2: Data Manipulation Language (DML) – 30 pts

# 02. Insert 

INSERT INTO messages(
					content,
					sent_on,
					chat_id,
					user_id)
	SELECT 
		concat_ws('-', u.age, u.gender, l.latitude, l.longitude) AS content,
        '2016-12-15' AS sent_on,
        (CASE
			WHEN u.gender = 'F' THEN ceil(sqrt(u.age * 2))
            WHEN u.gender = 'M' THEN (pow((u.age /18), 3))
        END) AS chat_id,
        u.id AS user_id 
	FROM users AS u
    JOIN locations AS l ON l.id = u.location_id
	WHERE u.id BETWEEN 10 AND 20;

# 03. Update 

UPDATE chats AS c
JOIN
	(
		SELECT 
			c.id AS chat_id,
			c.start_date AS start_date,
			m.id AS message_id, 
			m.sent_on AS sent_on
		FROM chats AS c
		JOIN messages AS m ON m.chat_id = c.id
		WHERE c.start_date > m.sent_on
		GROUP BY c.id
		ORDER BY m.sent_on, c.id
    ) AS d ON c.id = d.chat_id
SET c.start_date = d.sent_on
WHERE c.id = d.chat_id;

# 04. Delete

DELETE l FROM locations AS l
LEFT JOIN users AS u ON u.location_id = l.id
WHERE u.id IS NULL;

# Section 3: Querying – 100 pts

# 05. Age Range 

SELECT nickname, gender, age
FROM users
WHERE age BETWEEN 22 AND 37
ORDER BY id;

# 06. Messages 

SELECT content, sent_on
FROM messages
WHERE sent_on > '2014-05-12'
	AND content LIKE '%just%'
ORDER BY id DESC;

# 07. Chats 

SELECT title, is_active
FROM chats
WHERE (is_active = 0 AND length(title) < 5)
	OR title LIKE '__tl%'
ORDER BY title DESC;

# 08. Chat Messages 

SELECT c.id, c.title, m.id
FROM chats AS c
JOIN messages AS m ON m.chat_id = c.id
WHERE m.sent_on < '2012-03-26'
	AND c.title LIKE '%x'
ORDER BY c.id, m.id;

# 09. Message Count 

SELECT c.id, COUNT(c.id) AS total_messages
FROM chats AS c
JOIN messages AS m ON m.chat_id = c.id
WHERE m.id < 90
GROUP BY c.id
ORDER BY total_messages DESC, c.id
LIMIT 5;

# 10. Credentials 

SELECT u.nickname, c.email, c.password
FROM users AS u
JOIN credentials AS c ON c.id = u.credential_id
WHERE c.email LIKE '%co.uk'
ORDER BY c.email;

# 11. Locations 

SELECT id, nickname, age
FROM users
WHERE location_id IS NULL
ORDER BY id;

# 12. Left Users 

SELECT m.id, m.chat_id, m.user_id
FROM messages AS m
LEFT JOIN users_chats AS uc ON uc.chat_id = m.chat_id
	AND uc.user_id = m.user_id
WHERE m.chat_id = 17
	AND uc.user_id IS NULL
ORDER BY m.id DESC;

# 13. Users in Bulgaria 

SELECT 
	u.nickname,
	c.title,
    l.latitude,
    l.longitude
FROM users AS u
JOIN locations AS l ON l.id = u.location_id
JOIN users_chats AS uc ON uc.user_id = u.id
JOIN chats AS c ON c.id = uc.chat_id
WHERE (l.latitude BETWEEN 41.139999 AND 44.129999)
	AND (l.longitude BETWEEN 22.209999 AND 28.359999)
ORDER BY c.title;

# 14. Last Chat 

SELECT c.title, m.content
FROM chats AS c
LEFT JOIN messages AS m ON m.chat_id = c.id
WHERE c.start_date = (
						SELECT start_date
                        FROM chats
                        ORDER BY start_date DESC
                        LIMIT 1
                        )
ORDER BY m.sent_on, m.id;

# Section 4: Programmability – 40 pts

# 15. Radians 

DROP FUNCTION IF EXISTS udf_get_radians;

DELIMITER $$
CREATE FUNCTION udf_get_radians(degrees FLOAT) 
RETURNS FLOAT
READS SQL DATA
DETERMINISTIC
BEGIN
	DECLARE radians FLOAT;
    SET radians := (SELECT ((degrees * PI()) / 180));
	RETURN radians;
END $$
DELIMITER ;

SELECT udf_get_radians(22.12) AS radians;

# 16. Change Password 

DROP PROCEDURE IF EXISTS udp_change_password;

DELIMITER $$
CREATE PROCEDURE udp_change_password(
	email VARCHAR(30),
    password VARCHAR(20))
BEGIN
	
    DECLARE exists_email_id INT DEFAULT (SELECT c.id FROM credentials AS c WHERE c.email = email);
    
	START TRANSACTION;
	IF(exists_email_id IS NULL) THEN
		SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'The email does''t exist!';
		ROLLBACK;
    ELSE   
		UPDATE credentials AS c
        SET c.password = password
        WHERE c.id = exists_email_id;
        
        COMMIT;
    END IF;
END $$
DELIMITER ;

CALL udp_change_password('abarnes0@sogou.com', 'new_pass');

# 17. Send Message 

DROP PROCEDURE IF EXISTS udp_send_message;

DELIMITER $$
CREATE PROCEDURE udp_send_message(
	user_id INT(11),
    chat_id INT(11),
    content VARCHAR(200))
BEGIN
	
    DECLARE exists_user_id_and_chat_id INT DEFAULT (SELECT uc.user_id FROM users_chats AS uc WHERE uc.user_id = user_id AND uc.chat_id = chat_id);
    
	START TRANSACTION;
	IF(exists_user_id_and_chat_id IS NULL) THEN
		SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'There is no chat with that user!';
		ROLLBACK;
    ELSE   
		INSERT INTO messages(content,
								sent_on,
								chat_id,
								user_id)
		VALUES (content, '2016-12-15', chat_id, user_id);
        
        COMMIT;
    END IF;
END $$
DELIMITER ;

CALL udp_send_message(19, 17, 'Awesome');

# 18. Log Messages 

DROP TRIGGER IF EXISTS tr_log_messages;
DROP TABLE IF EXISTS messages_log;

CREATE TABLE messages_log
(
	id INT(11) NOT NULL PRIMARY KEY,
    content VARCHAR(200),
    sent_on DATE,
    chat_id INT(11),
    user_id INT(11),
    
    CONSTRAINT fk_messages_log_chats
    FOREIGN KEY (chat_id)
    REFERENCES chats(id),
    
    CONSTRAINT fk_messages_log_users
    FOREIGN KEY (user_id)
    REFERENCES users(id)
);

DELIMITER $$
CREATE TRIGGER tr_log_messages 
AFTER DELETE ON messages
FOR EACH ROW
BEGIN
	INSERT INTO 
			messages_log(
					id,
					content,
					sent_on,
					chat_id,
					user_id)
		VALUES (
				OLD.id,
				OLD.content,
				OLD.sent_on,
				OLD.chat_id,
				OLD.user_id);
END $$
DELIMITER ;

DELETE FROM messages
WHERE id = 1;

# 19. Delete Users 

DROP TRIGGER IF EXISTS tr_users_before_deleted;

DELIMITER $$
CREATE TRIGGER tr_users_before_deleted
BEFORE DELETE ON users
FOR EACH ROW
BEGIN
	DELETE uc FROM users_chats AS uc WHERE uc.user_id = OLD.id;
    DELETE m FROM messages AS m WHERE m.user_id = OLD.id;
    DELETE ml FROM messages_log AS ml WHERE ml.user_id = OLD.id;
END $$
DELIMITER ;