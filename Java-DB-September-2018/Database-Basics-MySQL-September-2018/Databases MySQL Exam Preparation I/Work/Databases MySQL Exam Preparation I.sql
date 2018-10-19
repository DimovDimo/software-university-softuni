CREATE DATABASE airport_management_system;

USE airport_management_system;

# Section 1: Data Definition Language (DDL) – 30 pts

# 01. Table Design 

CREATE TABLE towns
(
	town_id INT(11) NOT NULL PRIMARY KEY,
    town_name VARCHAR(30) NOT NULL
);

CREATE TABLE airports
(
	airport_id INT(11) NOT NULL PRIMARY KEY,
    airport_name VARCHAR(50) NOT NULL,
    town_id INT(11),
    CONSTRAINT fk_airports_towns
    FOREIGN KEY (town_id)
    REFERENCES towns(town_id)
);

CREATE TABLE airlines
(
	airline_id INT(11) NOT NULL PRIMARY KEY,
    airline_name VARCHAR(30) NOT NULL,
    nationality VARCHAR(30) NOT NULL,
    rating INT(11) DEFAULT 0
);

CREATE TABLE customers
(
	customer_id INT(11) NOT NULL PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(1),
    home_town_id INT(11),
    CONSTRAINT fk_customers_towns
    FOREIGN KEY (home_town_id)
    REFERENCES towns(town_id)
);

CREATE TABLE flights
(
	flight_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    departure_time DATETIME NOT NULL,
    arrival_time DATETIME NOT NULL,
    status VARCHAR(9),
    origin_airport_id INT(11),
    destination_airport_id INT(11),
    airline_id INT(11),
    
    CONSTRAINT fk_flights_airports_origin
    FOREIGN KEY (origin_airport_id)
    REFERENCES airports(airport_id),
    
    CONSTRAINT fk_flights_airports_destination
    FOREIGN KEY (destination_airport_id)
    REFERENCES airports(airport_id),
    
    CONSTRAINT fk_flights_airlines
    FOREIGN KEY (airline_id)
    REFERENCES airlines(airline_id)
);

CREATE TABLE tickets
(
	ticket_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    price DECIMAL(8,2) NOT NULL,
    class VARCHAR(6),
    seat VARCHAR(5) NOT NULL,
    customer_id INT(11),
    flight_id INT(11),
    
    CONSTRAINT fk_tickets_customers
    FOREIGN KEY (customer_id)
    REFERENCES customers(customer_id),
    
    CONSTRAINT fk_tickets_flights
    FOREIGN KEY (flight_id)
    REFERENCES flights(flight_id)
);

# Section 2: Data Manipulation Language (DML) – 30 pts

# 02. Insert 

INSERT INTO flights(
				departure_time,
				arrival_time,
				status,
				origin_airport_id,
				destination_airport_id,
				airline_id)
	SELECT 
		'2017-06-19 14:00:00' AS departure_time,
        '2017-06-21 11:00:00' AS arrival_time,
        (CASE
			WHEN a.airline_id % 4 = 0 THEN 'Departing'
            WHEN a.airline_id % 4 = 1 THEN 'Delayed'
            WHEN a.airline_id % 4 = 2 THEN 'Arrived'
            WHEN a.airline_id % 4 = 3 THEN 'Canceled'
        END) AS status,
        (ceil(sqrt(length(a.airline_name)))) AS origin_airport_id,
        (ceil(sqrt(length(a.nationality)))) AS destination_airport_id,
        (a.airline_id) AS airline_id
	FROM airlines AS a
	WHERE a.airline_id BETWEEN 1 AND 10;

# 03. Update Flights 

UPDATE flights
SET airline_id = 1
WHERE status = 'Arrived';

# 04. Update Tickets 

UPDATE tickets AS t
JOIN flights AS f ON f.flight_id = t.flight_id
JOIN airlines AS a ON a.airline_id = f.airline_id
SET price = price * 1.5
WHERE a.airline_id = 
	(SELECT airline_id 
    FROM airlines 
    ORDER BY rating DESC 
    LIMIT 1);

# Section 3: Querying – 100 pts

# 05. Tickets 

SELECT ticket_id, price, class, seat
FROM tickets
ORDER BY ticket_id;

# 06. Customers 

SELECT customer_id,
	concat_ws(' ', first_name, last_name) AS full_name,
    gender
FROM customers
ORDER BY full_name, customer_id;

# 07. Flights 

SELECT flight_id, departure_time, arrival_time
FROM flights
WHERE status = 'Delayed'
ORDER BY flight_id;

# 08. Top 5 Airlines 

SELECT a.airline_id, a.airline_name, a.nationality, a.rating
FROM airlines AS a
JOIN flights AS f ON f.airline_id = a.airline_id
GROUP BY a.airline_id
ORDER BY a.rating DESC, a.airline_id
LIMIT 5;

# 09. First Class Tickets 

SELECT 
	t.ticket_id,
    a.airport_name AS destination,
	concat_ws(' ', c.first_name, c.last_name) AS customer_name
FROM tickets AS t
JOIN flights AS f ON f.flight_id = t.flight_id
JOIN airports AS a ON a.airport_id = f.destination_airport_id
JOIN customers AS c ON c.customer_id = t.customer_id
WHERE t.price < 5000
	AND t.class = 'First'
ORDER BY t.ticket_id;

# 10. Home Town Customers 

SELECT c.customer_id,
	concat_ws(' ', c.first_name, c.last_name) AS full_name,
    t2.town_name
FROM customers AS c
JOIN tickets AS tic ON tic.customer_id = c.customer_id
JOIN flights AS f ON f.flight_id = tic.flight_id
JOIN airports AS a ON a.airport_id = f.origin_airport_id
JOIN towns AS t2 ON t2.town_id = a.town_id
WHERE c.home_town_id = a.town_id
	AND f.status = 'Departing'
GROUP BY c.customer_id
ORDER BY c.customer_id;

# 11. Flying Customers 

SELECT c.customer_id,
	concat_ws(' ', c.first_name, c.last_name) AS full_name,
    TIMESTAMPDIFF(year, c.date_of_birth, '2016-12-31') AS age
FROM customers AS c
JOIN tickets AS tic ON tic.customer_id = c.customer_id
JOIN flights AS f ON f.flight_id = tic.flight_id
WHERE f.status = 'Departing'
GROUP BY c.customer_id
ORDER BY age, c.customer_id;

# 12. Delayed Customers 

SELECT c.customer_id,
	concat_ws(' ', c.first_name, c.last_name) AS full_name,
    tic.price,
    a.airport_name
FROM customers AS c
JOIN tickets AS tic ON tic.customer_id = c.customer_id
JOIN flights AS f ON f.flight_id = tic.flight_id
JOIN airports AS a ON a.airport_id = f.destination_airport_id
WHERE f.status = 'Delayed'
ORDER BY tic.price DESC, c.customer_id
LIMIT 3;

# 13. Last Departing Flights 

SELECT 
	f2.flight_id,
    f2.departure_time,
    f2.arrival_time,
    ao.airport_name,
    ad.airport_name
FROM (
		SELECT * 
		FROM flights AS f
		WHERE f.status = 'Departing'
		ORDER BY f.departure_time DESC
		LIMIT 5
     ) AS f2
JOIN airports AS ao On ao.airport_id = f2.origin_airport_id
JOIN airports AS ad On ad.airport_id = f2.destination_airport_id
ORDER BY f2.departure_time, f2.flight_id;

# 14. Flying Children

SELECT c.customer_id,
	concat_ws(' ', c.first_name, c.last_name) AS full_name,
    TIMESTAMPDIFF(year, c.date_of_birth, '2016-12-31') AS age
FROM customers AS c
JOIN tickets AS tic ON tic.customer_id = c.customer_id
JOIN flights AS f ON f.flight_id = tic.flight_id
WHERE f.status = 'Arrived'
	AND TIMESTAMPDIFF(year, c.date_of_birth, '2016-12-31') < 21
GROUP BY c.customer_id
ORDER BY age DESC, c.customer_id;

# 15. Airports and Passengers 

SELECT 
	a.airport_id,
    a.airport_name,
    COUNT(a.airport_id) AS passengers
FROM airports AS a
JOIN flights AS f ON f.origin_airport_id = a.airport_id 
JOIN tickets AS t ON t.flight_id = f.flight_id
WHERE f.status = 'Departing' 
GROUP BY a.airport_id    
ORDER BY a.airport_id;

# Section 4: Programmability

# 16. Submit Review 

DROP PROCEDURE IF EXISTS udp_submit_review;

DELIMITER $$
CREATE PROCEDURE udp_submit_review(
	customer_id INT(11), 
    review_content VARCHAR(255),
    review_grade INT,
    airline_name VARCHAR(30))
BEGIN
	
    DECLARE users_airline_id INT DEFAULT (SELECT a.airline_id FROM airlines AS a WHERE a.airline_name = airline_name);
    
	START TRANSACTION;
	IF(users_airline_id IS NULL) THEN
		SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'Airline does not exist.';
		ROLLBACK;
    ELSE   
        INSERT INTO customer_reviews(review_content, review_grade, airline_id, customer_id)
        VALUES (review_content, 
				review_grade,
                (SELECT a.airline_id FROM airlines AS a WHERE a.airline_name = airline_name),
                customer_id);
        
        COMMIT;
    END IF;
END $$
DELIMITER ;

# 17. Ticket Purchase 

DROP PROCEDURE IF EXISTS udp_purchase_ticket;

DELIMITER $$
CREATE PROCEDURE udp_purchase_ticket(
	customer_id INT(11), 
    flight_id INT(11),
    ticket_price DECIMAL(8,2),
    class VARCHAR(6),
    seat VARCHAR(5))
BEGIN
	
    DECLARE customers_ticket_price_diff INT DEFAULT (SELECT (cba.balance - ticket_price) AS diff FROM customer_bank_accounts AS cba WHERE cba.customer_id = customer_id);
    
	START TRANSACTION;
	IF(customers_ticket_price_diff < 0) THEN
		SIGNAL SQLSTATE '45000' 
			SET MESSAGE_TEXT = 'Insufficient bank account balance for ticket purchase.';
		ROLLBACK;
    ELSE   
        INSERT INTO tickets(price,
							class,
							seat,
							customer_id,
							flight_id)
        VALUES (ticket_price, 
				class,
                seat,
                customer_id,
                flight_id);
                
		UPDATE customer_bank_accounts AS cba
        SET cba.balance = cba.balance - ticket_price
        WHERE cba.customer_id = customer_id;
        
        COMMIT;
    END IF;
END $$
DELIMITER ;

# 18. Update Trigger 

DROP TRIGGER IF EXISTS udp_purchase_ticket;

DELIMITER $$
CREATE TRIGGER tr_arrived_flights 
BEFORE UPDATE ON flights
FOR EACH ROW
BEGIN
	IF((OLD.status NOT IN ('Cancelled', 'Arrived')) AND NEW.status = 'Arrived') THEN
        INSERT INTO 
			arrived_flights(
					flight_id,
					arrival_time,
					origin,
					destination,
					passengers)
		VALUES (
				OLD.flight_id,
				OLD.arrival_time,
				(SELECT a.airport_name FROM airports AS a WHERE a.airport_id = OLD.origin_airport_id),
                (SELECT a.airport_name FROM airports AS a WHERE a.airport_id = OLD.destination_airport_id),
				(SELECT COUNT(f.flight_id) AS passengers
					FROM flights AS f
					JOIN tickets AS t ON t.flight_id = f.flight_id
					WHERE OLD.flight_id = f.flight_id));
    END IF;
END $$
DELIMITER ;