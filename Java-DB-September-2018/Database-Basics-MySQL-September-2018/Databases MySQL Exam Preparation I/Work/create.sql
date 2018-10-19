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