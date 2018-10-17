CREATE DATABASE buhtig;

USE buhtig;

# Section 1: Data Definition Language (DDL) – 40 pts

# 01. Table Design 

CREATE TABLE users
(
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE repositories
(
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name  VARCHAR(50) NOT NULL
);

CREATE TABLE repositories_contributors
(
	repository_id INT(11),
    contributor_id INT(11),
    
    CONSTRAINT fk_repositories
    FOREIGN KEY (repository_id)
    REFERENCES repositories(id),
    
    CONSTRAINT fk_users
    FOREIGN KEY (contributor_id)
    REFERENCES users(id)
);

CREATE TABLE issues
(
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title  VARCHAR(255) NOT NULL,
    issue_status VARCHAR(6) NOT NULL,
    repository_id INT(11) NOT NULL,
    assignee_id INT(11) NOT NULL,
    
    CONSTRAINT fk_issues_repositories
    FOREIGN KEY (repository_id)
    REFERENCES repositories(id),
    
    CONSTRAINT fk_issues_users
    FOREIGN KEY (assignee_id)
    REFERENCES users(id)
);

CREATE TABLE commits
(
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    message  VARCHAR(255) NOT NULL,
	issue_id INT(11),
	repository_id INT(11) NOT NULL,
    contributor_id INT(11) NOT NULL,
    
    CONSTRAINT fk_commit_issues
    FOREIGN KEY (issue_id)
    REFERENCES issues(id),
    
    CONSTRAINT fk_commit_repositories
    FOREIGN KEY (repository_id)
    REFERENCES repositories(id),
    
    CONSTRAINT fk_commit_users
    FOREIGN KEY (contributor_id)
    REFERENCES users(id)
);

CREATE TABLE files
(
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name  VARCHAR(100) NOT NULL,
	size DECIMAL(10, 2),
	parent_id INT(11),
    commit_id INT(11) NOT NULL,
    
    CONSTRAINT fk_file_files
    FOREIGN KEY (parent_id)
    REFERENCES files(id),
    
    CONSTRAINT fk_file_commits
    FOREIGN KEY (commit_id)
    REFERENCES commits(id)
);

# Section 2: Data Manipulation Language (DML) – 30 pts

# 02. Insert 

INSERT INTO issues(title, issue_status, repository_id, assignee_id)
	SELECT 
		concat('Critical Problem With ', f.name, '!') AS title,
		concat('open') AS issue_status,
		ceil(f.id * 2 / 3) AS repository_id,
		c.contributor_id AS assignee_id
	FROM files AS f
	JOIN commits AS c ON c.id = f.commit_id
	WHERE f.id BETWEEN 46 AND 50;
    
# 03. Update

UPDATE repositories_contributors AS r
JOIN (
	  SELECT r.id AS repo_id
      FROM repositories AS r
      WHERE r.id NOT IN (
		  SELECT repository_id
          FROM repositories_contributors)
      ORDER BY r.id
      LIMIT 1
      ) AS j
SET repository_id = j.repo_id
WHERE repository_id = contributor_id;

# 04. Delete 

DELETE r FROM repositories r
LEFT JOIN issues AS i ON i.repository_id = r.id
WHERE i.repository_id IS NULL;

# Section 3: Querying – 100 pts

# 05. Users 

SELECT id, username
FROM users
ORDER BY id;

# 06. Lucky Numbers 

SELECT *
FROM repositories_contributors
WHERE repository_id = contributor_id
ORDER BY repository_id;

# 07. Heavy HTML 

SELECT id, name, size
FROM files
WHERE size > 1000
	AND name LIKE '%html%'
ORDER BY size DESC;

# 08. IssuesAndUsers 

SELECT i.id,
	concat_ws(' : ', u.username, i.title) AS issue_assignee
FROM issues AS i
JOIN users AS u ON u.id = i.assignee_id
ORDER BY i.id DESC;

# 09. NonDirectoryFiles 

SELECT f.id, f.name,
	concat(f.size, 'KB') AS size
FROM files AS f
LEFT JOIN
	(SELECT DISTINCT(parent_id)
	FROM files
	WHERE parent_id IS NOT NULL) AS m
    ON m.parent_id = f.id
WHERE m.parent_id IS NULL
ORDER BY f.id;

# 10. ActiveRepositories 

SELECT r.id, r.name,
	COUNT(r.id) AS issues
FROM repositories AS r
JOIN issues AS i ON i.repository_id = r.id
GROUP BY r.id
ORDER BY issues DESC, r.id
LIMIT 5;

# 11. MostContributedRepository 

SELECT r.id, r.name, 
	(
		 SELECT COUNT(*)
		 FROM commits AS c
		 WHERE c.repository_id = r.id) AS commits,
	COUNT(u.id) AS contributors
FROM repositories r
JOIN repositories_contributors AS rc ON rc.repository_id = r.id
JOIN users AS u ON u.id = rc.contributor_id
GROUP BY r.id, commits
ORDER BY contributors DESC, r.id
LIMIT 1;

# 12, FixingMyOwnProblems 

SELECT u.id, u.username,
	SUM(IF(c.contributor_id = u.id, 1, 0)) AS commits
FROM users AS u
LEFT JOIN issues AS i ON i.assignee_id = u.id
LEFT JOIN commits AS c ON c.issue_id = i.id
GROUP BY u.id
ORDER BY commits DESC, u.id;

# 13. RecursiveCommits 

SELECT 
	SUBSTRING_INDEX(f.name, '.', 1) AS file,
    COUNT(nullif(LOCATE(f.name, c.message), 0)) AS recursive_count
FROM files AS f
JOIN files AS p ON p.parent_id = f.id
JOIN commits AS c
WHERE f.id <> p.id
	AND p.parent_id = f.id
    AND p.id = f.parent_id
GROUP BY f.name
ORDER BY f.name;
    
# 14. RepositoriesAndCommits 

SELECT r.id, r.name,
	COUNT(DISTINCT(c.contributor_id)) AS users
FROM repositories AS r
LEFT JOIN commits AS c ON c.repository_id = r.id
GROUP BY r.id
ORDER BY users DESC, r.id;

# Section 4: Programmability – 30 pts

# 15. Commit 

DROP PROCEDURE IF EXISTS udp_commit;

DELIMITER $$
CREATE PROCEDURE udp_commit(
	username VARCHAR(30), 
    password VARCHAR(30),
    message VARCHAR(255),
    issue_id INT)
BEGIN
	DECLARE users_username VARCHAR(30) DEFAULT (SELECT u.username FROM users AS u WHERE u.username = username);
    DECLARE users_password VARCHAR(30) DEFAULT (SELECT u.password FROM users AS u WHERE u.password = password);
    DECLARE issues_issue_id INT DEFAULT (SELECT i.id FROM issues AS i WHERE i.id = issue_id);
	
	START TRANSACTION;
	IF(users_username IS NULL) THEN
		SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'No such user!';
		ROLLBACK;
	ELSEIF(users_password <> password) THEN
		SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'Password is incorrect!';
		ROLLBACK;
    ELSEIF(issues_issue_id IS NULL) THEN
		SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'The issue does not exist!';
		ROLLBACK;
    ELSE   
        INSERT INTO commits(message, issue_id, repository_id, contributor_id)
        VALUES (message, 
				issue_id, 
				(SELECT i.repository_id AS repo_id FROM issues AS i WHERE i.id = issue_id),
                (SELECT u.id FROM users AS u WHERE u.username = username AND u.password = password));
        
        UPDATE issues AS i
        SET i.issue_status = 'closed'
        WHERE i.id = issue_id;
        
        COMMIT;
    END IF;
END $$
DELIMITER ;

CALL udp_commit(
	'WhoDenoteBel', 
    'ajmISQi*', 
    'Fixed issue: blah', 
    2);

# 16. Filter Extensions 

DROP PROCEDURE IF EXISTS udp_findbyextension;

DELIMITER $$
CREATE PROCEDURE udp_findbyextension(extension VARCHAR(30))
BEGIN
	SELECT 
		f.id AS id,
		f.name AS caption,
		concat(f.size, 'KB') AS user
	FROM files AS f
	WHERE f.name LIKE (concat('%', extension))
	ORDER BY f.id;
END $$
DELIMITER ;

CALL udp_findbyextension('html');