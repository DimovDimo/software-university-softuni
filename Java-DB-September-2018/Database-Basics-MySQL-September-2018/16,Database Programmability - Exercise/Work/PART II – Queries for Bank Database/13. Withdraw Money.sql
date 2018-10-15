USE bank;

DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(20,4))
BEGIN
	START TRANSACTION;
		CASE WHEN money_amount < 0 
				OR money_amount > 
					(SELECT a.balance 
                     FROM accounts as a
					 WHERE a.id = account_id)
		THEN ROLLBACK;
	ELSE 
		UPDATE accounts a
		SET a.balance = a.balance - money_amount
        WHERE a.id = account_id;
	END CASE;
	COMMIT;
END $$

CALL usp_withdraw_money(1, 10);

SELECT a.id, h.id, a.balance
FROM account_holders AS h
JOIN accounts AS a ON a.account_holder_id = h.id
WHERE a.id = 1;