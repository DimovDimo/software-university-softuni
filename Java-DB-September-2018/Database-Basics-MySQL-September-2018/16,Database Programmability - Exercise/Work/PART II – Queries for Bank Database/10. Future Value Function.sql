USE bank;

DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(initial_sum DOUBLE, yearly_interest_rate DOUBLE, years INT(11))
RETURNS DOUBLE
BEGIN
	DECLARE result DOUBLE;
	SET result := initial_sum * POW(1 + yearly_interest_rate, years);
	RETURN result;
END $$

CALL ufn_calculate_future_value(1000, 0.1, 5);