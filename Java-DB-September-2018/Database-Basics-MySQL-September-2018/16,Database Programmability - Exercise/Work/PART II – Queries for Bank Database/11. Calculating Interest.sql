USE bank;

DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value
	(initial_sum DECIMAL(20,4), yearly_interest_rate DECIMAL(20,4), years INT)
RETURNS DECIMAL(20,4)
BEGIN
	DECLARE result DECIMAL(20,4);
	SET result := initial_sum * POW(1 + yearly_interest_rate, years);
	RETURN result;
END $$

DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account
	(account_id INT, interest_rate DECIMAL(20,4))
BEGIN
	SELECT a.id, h.first_name, h.last_name, a.balance,
		ufn_calculate_future_value(a.balance, interest_rate, 5) AS balance_in_5_years
	FROM account_holders h
    JOIN accounts AS a ON a.account_holder_id = h.id 
    GROUP BY a.account_holder_id
    HAVING a.id = account_id;
END $$

CALL usp_calculate_future_value_for_account(1, 0.1);