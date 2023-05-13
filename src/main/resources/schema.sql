CREATE TABLE IF NOT EXISTS CRUISE (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(5000) NOT NULL,
    CAPACITY INTEGER NOT NULL CHECK (CAPACITY > 0),
    AVG_SHIP_RATING REAL DEFAULT 0,
    AVG_ROOM_RATING REAL DEFAULT 0,
    AVG_CREW_RATING REAL DEFAULT 0,
    AVG_FOOD_RATING REAL DEFAULT 0,
    N_REVIEWS INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS DESTINATION (
    ID SERIAL PRIMARY KEY,
    DESCRIPTION VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS TRIP (
    ID SERIAL PRIMARY KEY,
    ROUTE VARCHAR(5000) NOT NULL,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    N_DIVES INTEGER NOT NULL CHECK (N_DIVES >= 0),
    PRICE REAL NOT NULL CHECK (PRICE > 0),
    CRUISE_ID INTEGER NOT NULL,
    DESTINATION_ID INTEGER NOT NULL,
    AVAILABLE_PLACES INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT FK_CRUISE FOREIGN KEY (CRUISE_ID) REFERENCES CRUISE(ID),
    CONSTRAINT FK_DESTINATION FOREIGN KEY (DESTINATION_ID) REFERENCES DESTINATION(ID)
);

CREATE TYPE BOOKING_STATUS AS ENUM ('PENDING', 'CONFIRMED', 'REJECTED');

CREATE TABLE IF NOT EXISTS BOOKING (
    ID SERIAL PRIMARY KEY,
    STATUS BOOKING_STATUS
);

CREATE TABLE IF NOT EXISTS RATING (
    ID SERIAL PRIMARY KEY,
    SHIP INTEGER CHECK (SHIP >= 0 AND SHIP <= 5),
    ROOM INTEGER CHECK (ROOM >= 0 AND ROOM <= 5),
    CREW INTEGER CHECK (CREW >= 0 AND CREW <= 5),
    FOOD INTEGER CHECK (FOOD >= 0 AND FOOD <= 5),
    COMMENT VARCHAR(255)
);