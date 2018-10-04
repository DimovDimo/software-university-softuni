USE book_library;

SELECT cast(sum(cost) as decimal(10, 2))
FROM books;