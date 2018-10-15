USE bank;

CREATE TABLE logs(
	log_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    account_id INT NOT NULL, 
    old_sum DECIMAL(20,4) NOT NULL, 
    new_sum DECIMAL(20,4) NOT NULL
);

CREATE TRIGGER log_balance_changes 
AFTER UPDATE 
ON accounts 
FOR EACH ROW
BEGIN
	CASE WHEN OLD.balance != NEW.balance 
    THEN 
		INSERT INTO `logs`(account_id, old_sum, new_sum)
		VALUES (OLD.id, OLD.balance, NEW.balance);
	ELSE
		BEGIN END;
	END CASE;
END;