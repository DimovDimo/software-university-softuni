USE soft_uni;

DELIMITER $$
CREATE FUNCTION ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))
RETURNS BIT
BEGIN
	DECLARE result BIT;
    DECLARE word_length INT(11);
    DECLARE current_index INT(11);
    
    SET result := 1;
    SET word_length := char_length(word);
    SET current_index := 1;
    
    WHILE(current_index <= word_length) DO
		IF(set_of_letters NOT LIKE (concat('%', substr(word, current_index, 1), '%')))
			THEN SET result := 0;
		END IF;
        
        SET current_index = current_index + 1;
    END WHILE;
    RETURN result;
END $$