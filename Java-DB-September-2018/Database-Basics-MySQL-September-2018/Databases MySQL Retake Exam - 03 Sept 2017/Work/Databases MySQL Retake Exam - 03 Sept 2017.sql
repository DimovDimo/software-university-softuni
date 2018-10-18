CREATE DATABASE instagraph_db;

USE instagraph_db;

# Section 1: Data Definition Language (DDL) – 40 pts

# 01. Table Design 

CREATE TABLE pictures
(
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    path VARCHAR(255) NOT NULL,
    size DECIMAL(10,2) NOT NULL
);

CREATE TABLE users
(
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(30) NOT NULL,
    profile_picture_id INT(11),
    CONSTRAINT fk_users_pictures
    FOREIGN KEY (profile_picture_id)
    REFERENCES pictures(id)
);

CREATE TABLE posts
(
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    caption VARCHAR(255) NOT NULL,
    user_id INT(11) NOT NULL,
    picture_id INT(11) NOT NULL,
    
    CONSTRAINT fk_posts_users
    FOREIGN KEY (user_id)
    REFERENCES users(id),
    
    CONSTRAINT fk_posts_pictures
    FOREIGN KEY (picture_id)
    REFERENCES pictures(id)
);

CREATE TABLE comments
(
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(255) NOT NULL,
    user_id INT(11) NOT NULL,
    post_id INT(11) NOT NULL,
    
    CONSTRAINT fk_comments_users
    FOREIGN KEY (user_id)
    REFERENCES users(id),
    
    CONSTRAINT fk_comments_posts
    FOREIGN KEY (post_id)
    REFERENCES posts(id)
);

CREATE TABLE users_followers
(
    user_id INT(11),
    follower_id INT(11),
    
    CONSTRAINT fk_users_followers_users
    FOREIGN KEY (user_id)
    REFERENCES users(id),
    
    CONSTRAINT fk_users_followers_follower
    FOREIGN KEY (follower_id)
    REFERENCES users(id)
);

# Section 2: Data Manipulation Language (DML) – 30 pts

# 02. Insert 

INSERT INTO comments(content, user_id, post_id)
	SELECT 
		concat('Omg!',u.username,'!This is so cool!') AS content,
        ceil(p.id * 3 / 2) AS user_id,
        p.id AS post_id 
	FROM posts AS p
	JOIN users AS u ON u.id = p.user_id
	WHERE p.id BETWEEN 1 AND 10;

# 03. Update 

UPDATE users AS u
JOIN (
		SELECT *, 
			COUNT(DISTINCT(uf.follower_id)) AS count_followers
		FROM users AS u
		LEFT JOIN users_followers AS uf ON uf.user_id = u.id
		GROUP BY u.id
		HAVING u.profile_picture_id IS NULL
      ) AS j
SET u.profile_picture_id = 
	IF(j.count_followers > 0, j.count_followers, u.id)
WHERE u.id = j.id;

# 04. Delete 

DELETE u FROM users AS u
LEFT JOIN users_followers AS uf ON uf.user_id = u.id
WHERE uf.user_id IS NULL
	AND uf.follower_id IS NULL;

# Section 3: Querying – 100 pts

# 05. Users 

SELECT id, username
FROM users
ORDER BY id;

# 06. Cheaters 

SELECT uf.user_id, u.username
FROM users_followers AS uf
JOIN users AS u ON u.id = uf.user_id
WHERE user_id = follower_id
ORDER BY uf.user_id;

# 07. High Quality Pictures 

SELECT * 
FROM pictures
WHERE size > 50000
	AND (path LIKE '%jpeg' OR path LIKE '%png')
ORDER BY size DESC;

# 08. Comments and Users 

SELECT c.id,
	concat_ws(' : ', u.username, c.content) AS full_comment
FROM comments AS c
JOIN users AS u ON u.id = c.user_id
ORDER BY c.id DESC;

# 09. Profile Pictures 

SELECT u.id, u.username,
	concat(p.size, 'KB') AS size
FROM users AS u
JOIN pictures AS p ON p.id = u.profile_picture_id
WHERE 1 < (SELECT COUNT(*) 
			FROM users AS u2
            WHERE u.profile_picture_id = u2.profile_picture_id)
ORDER BY u.id;

# 10. Spam Posts 

SELECT p.id, p.caption,
	COUNT(p.id) AS comments
FROM posts AS p
JOIN comments AS c ON c.post_id = p.id
GROUP BY p.id
ORDER BY comments DESC, p.id
LIMIT 5;

# 11, Most Popular User 

SELECT u.id, u.username, 
	COUNT(u.id) AS posts,
    (
		SELECT COUNT(uf.follower_id) 
		FROM users_followers AS uf
		WHERE u.id = uf.user_id
    ) AS followers
FROM users AS u
JOIN posts AS p ON p.user_id = u.id
GROUP BY u.id
ORDER BY followers DESC
LIMIT 1;

# 12. Commenting Myself 

SELECT u.id, u.username,
	SUM(IF(u.id = p.user_id, 1, 0)) AS my_comments
FROM users AS u
LEFT JOIN comments AS c ON c.user_id = u.id
LEFT JOIN posts AS p ON p.id = c.post_id
GROUP BY u.id
ORDER BY my_comments DESC, u.id;

# 13. User Top Posts 

SELECT user_id, user_username, post_caption
FROM (
		SELECT
			u.id AS user_id,
			u.username AS user_username, 
			p.id AS post_id,
			p.caption AS post_caption,
			COUNT(c.id) AS count_comments
		FROM users AS u
		JOIN posts AS p ON p.user_id = u.id
		LEFT JOIN comments AS c ON c.post_id = p.id
		GROUP BY p.id
		ORDER BY count_comments DESC, post_id
    ) AS b
GROUP BY user_id
ORDER BY user_id;

# 14. Posts and Commentators 

SELECT p.id, p.caption, 
	COUNT(DISTINCT(c.user_id)) AS count_users
FROM posts AS p
LEFT JOIN comments AS c ON c.post_id = p.id
GROUP BY p.id
ORDER BY count_users DESC, p.id;

# Section 4: Programmability – 30 pts

# 15. Post 

DROP PROCEDURE IF EXISTS udp_commit;

DELIMITER $$
CREATE PROCEDURE udp_commit(
	username VARCHAR(30), 
    password VARCHAR(30),
    caption VARCHAR(255),
    path VARCHAR(255))
BEGIN
	
    DECLARE users_password_id INT DEFAULT (SELECT u.id FROM users AS u WHERE u.password = password AND u.username = username);
    DECLARE pictures_path_id INT DEFAULT (SELECT p.id FROM pictures AS p WHERE p.path = path);
	
	START TRANSACTION;
	IF(users_password_id IS NULL) THEN
		SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'Password is incorrect!';
		ROLLBACK;
    ELSEIF(pictures_path_id IS NULL) THEN
		SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'The picture does not exist!';
		ROLLBACK;
    ELSE   
        INSERT INTO posts(caption, user_id, picture_id)
        VALUES (caption, 
				users_password_id,
                pictures_path_id);
        
        COMMIT;
    END IF;
END $$
DELIMITER ;

CALL udp_commit(
	'UnderSinduxrein', 
    '4l8nYGTKMW', 
    '#new #procedure', 
    'src/folders/resources/images/story/reformatted/img/hRI3TW31rC.img');
    
    # 16. Filter 
    
    DROP PROCEDURE IF EXISTS udp_filter;

DELIMITER $$
CREATE PROCEDURE udp_filter(hashtag VARCHAR(30))
BEGIN
	SELECT p.id, p.caption, u.username
	FROM posts AS p
    JOIN users AS u ON u.id = p.user_id
	WHERE p.caption LIKE (concat('%', hashtag, '%'))
	ORDER BY p.id;
END $$
DELIMITER ;

CALL udp_filter('cool');