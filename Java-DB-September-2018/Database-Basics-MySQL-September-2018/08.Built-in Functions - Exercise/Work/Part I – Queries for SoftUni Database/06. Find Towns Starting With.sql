USE soft_uni;

SELECT town_id, name
FROM towns
WHERE name REGEXP '^(M|K|B|E).*'
ORDER BY name;