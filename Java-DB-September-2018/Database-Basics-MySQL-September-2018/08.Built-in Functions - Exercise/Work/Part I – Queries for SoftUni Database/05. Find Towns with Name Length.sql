USE soft_uni;

SELECT name
FROM towns
WHERE char_length(name) IN (5, 6)
ORDER BY name;