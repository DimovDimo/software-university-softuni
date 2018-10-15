USE bank;

DELIMITER $$
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(20, 4))
BEGIN
	START TRANSACTION;
		CASE WHEN 
			(SELECT a.id FROM accounts as a WHERE a.id = from_account_id) IS NULL
            OR (SELECT a.id FROM accounts as a WHERE a.id = to_account_id) IS NULL
            OR from_account_id = to_account_id
            OR amount < 0
            OR (SELECT a.balance FROM accounts as a WHERE a.id = from_account_id) < amount
		THEN ROLLBACK;
	ELSE 
		UPDATE accounts a
		SET a.balance = a.balance - amount
        WHERE a.id = from_account_id;
        
        UPDATE accounts a
		SET a.balance = a.balance + amount
        WHERE a.id = to_account_id;
	END CASE;
	COMMIT;
END $$

CALL usp_transfer_money(1, 2, 10);

SELECT a.id, h.id, a.balance
FROM account_holders AS h
JOIN accounts AS a ON a.account_holder_id = h.id
WHERE a.id IN (1, 2);