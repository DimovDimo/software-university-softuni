USE camp;

CREATE TABLE teachers(
	teacher_id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20),
	manager_id INT,
	CONSTRAINT fk_manager_teacher
    FOREIGN KEY (manager_id)
    REFERENCES teachers(teacher_id)
);

INSERT INTO teachers(teacher_id,name)
VALUES
	(101,'John'),
	(102,'Maya'),
	(103,'Silvia'),
	(104,'Ted'),
	(105,'Mark'),
	(106,'Greta');
    
UPDATE teachers
SET manager_id = CASE
	WHEN teacher_id = 102 THEN 106
	WHEN teacher_id = 103 THEN 106
	WHEN teacher_id = 104 THEN 105
	WHEN teacher_id = 105 THEN 101
	WHEN teacher_id = 106 THEN 101
END
WHERE teacher_id IS NOT NULL;