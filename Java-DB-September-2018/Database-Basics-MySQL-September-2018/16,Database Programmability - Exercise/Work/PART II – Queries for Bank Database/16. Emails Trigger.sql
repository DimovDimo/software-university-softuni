USE bank;

CREATE TABLE logs(
	log_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    account_id INT NOT NULL, 
    old_sum DECIMAL(20,4) NOT NULL, 
    new_sum DECIMAL(20,4) NOT NULL
);

CREATE TRIGGER tr_log_balance_changes 
AFTER UPDATE 
ON accounts 
FOR EACH ROW
BEGIN
	CASE WHEN OLD.balance != NEW.balance 
    THEN 
		INSERT INTO logs(account_id, old_sum, new_sum)
		VALUES (OLD.id, OLD.balance, NEW.balance);
	ELSE
		BEGIN END;
	END CASE;
END;

CREATE TABLE notification_emails(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    recipient INT NOT NULL, 
    subject TEXT, 
    body TEXT
);

CREATE TRIGGER tr_notification_emails 
AFTER INSERT  
ON logs 
FOR EACH ROW
BEGIN
	INSERT INTO notification_emails(recipient, subject, body)
	VALUES (
		NEW.account_id, 
		concat('Balance change for account: ', NEW.account_id),
        concat('On ',DATE_FORMAT(NOW(), '%b %d %Y'), ' at ', DATE_FORMAT(NOW(), '%r'),' your balance was changed from ',NEW.old_sum,' to ',NEW.new_sum,'.')
        );
END;