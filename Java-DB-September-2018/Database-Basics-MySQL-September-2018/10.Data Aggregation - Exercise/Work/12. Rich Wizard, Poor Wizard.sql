USE gringotts;

SELECT SUM(
	w.deposit_amount -
		(SELECT wW.deposit_amount
		FROM wizzard_deposits AS wW
		WHERE wW.id = 1 + w.id
	)) AS 'sum_difference'
FROM wizzard_deposits AS w;