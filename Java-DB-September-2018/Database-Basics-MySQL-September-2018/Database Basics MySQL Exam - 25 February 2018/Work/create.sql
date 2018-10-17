CREATE DATABASE buhtig;

USE buhtig;

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