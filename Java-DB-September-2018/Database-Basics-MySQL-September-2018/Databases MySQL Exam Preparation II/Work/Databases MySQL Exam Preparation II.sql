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

# 03. Update 

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