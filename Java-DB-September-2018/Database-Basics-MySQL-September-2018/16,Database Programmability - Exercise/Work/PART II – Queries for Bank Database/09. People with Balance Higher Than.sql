USE bank;

DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(target_balance DOUBLE)
BEGIN
	SELECT h.first_name, h.last_name
	FROM account_holders h
    JOIN accounts AS a ON a.account_holder_id = h.id 
    GROUP BY a.account_holder_id
    HAVING SUM(a.balance) > target_balance
	ORDER BY a.id, h.first_name, h.last_name;
END $$

CALL usp_get_holders_with_balance_higher_than(7000);