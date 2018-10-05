USE restaurant;

SELECT category_id, 
	round(AVG(price),2) AS 'Average Price',
    round(MIN(price),2) AS 'Cheapest Product',
	round(MAX(price),2) AS 'Most Expensive Product'
FROM products
GROUP BY category_id;