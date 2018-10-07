USE camp;

CREATE TABLE mountains(
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE peaks(
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    mountain_id INT(11) NOT NULL,
    CONSTRAINT fk_peaks_mountains
    FOREIGN KEY (mountain_id)
    REFERENCES mountains(id)
    ON DELETE CASCADE
);