USE restaurant;

SELECT COUNT(*)
FROM products
#GROUP BY category_id
WHERE category_id = 2 AND price > 8;