USE bank;

DELIMITER $$
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(20,4))
BEGIN
	START TRANSACTION;
    
		IF((SELECT id
            FROM accounts
            WHERE id = account_id
            ) IS NULL
        ) THEN ROLLBACK;
        END IF;
        
        IF(money_amount < 0) THEN ROLLBACK;
        END IF;
    
		UPDATE accounts
        SET balance = balance + money_amount
        WHERE id = account_id;
	COMMIT;
END $$

CALL usp_deposit_money(1, 10);

SELECT a.id, h.id, a.balance
FROM account_holders AS h
JOIN accounts AS a ON a.account_holder_id = h.id
WHERE a.id = account_id;