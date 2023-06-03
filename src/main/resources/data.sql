INSERT INTO CRUISE (NAME, DESCRIPTION, CAPACITY, AVG_SHIP_RATING, AVG_ROOM_RATING, AVG_CREW_RATING, AVG_FOOD_RATING, COMPANY_ID, THUMBNAIL_ID)
VALUES ('Caribbean Liveaboard Adventure', 'Experience the beauty of the Caribbean on this exciting liveaboard cruise', 16, 4.2, 4.3, 4.5, 4.4, 2, NULL),
       ('Red Sea Diving Expedition', 'Explore the underwater wonders of the Red Sea on this thrilling liveaboard cruise', 20, 4.6, 4.5, 4.7, 4.8, 2, NULL),
       ('Komodo National Park Safari', 'Embark on a wildlife adventure in the Komodo National Park on this amazing liveaboard cruise', 12, 4.8, 4.4, 4.6, 4.2, 2, NULL);

INSERT INTO DESTINATION (DESCRIPTION)
VALUES ('Caribbean'),
       ('Red Sea'),
       ('Komodo National Park');

INSERT INTO TRIP (ROUTE, START_DATE, END_DATE, DIVES, PRICE, CRUISE_ID, DESTINATION_ID, AVAILABLE_PLACES)
VALUES ('St. Lucia - Dominica - St. Kitts - St. Lucia', '2023-07-15', '2023-07-22', 18, 3500.00, 1, 1, 10),
       ('Hurghada - Brothers Islands - Daedalus - Elphinstone - Hurghada', '2023-08-10', '2023-08-20', 20, 4500.00, 2, 2, 12),
       ('Labuan Bajo - Rinca Island - Padar Island - Komodo Island - Labuan Bajo', '2023-09-05', '2023-09-15', 15, 5000.00, 3, 3, 8);

INSERT INTO BOOKING (STATUS, TRIP_ID, TRAVELER_ID)
VALUES ('CONFIRMED', 1, 1),
       ('PENDING', 2, 2),
       ('PENDING', 3, 3);

INSERT INTO RATING (SHIP, ROOM, CREW, FOOD, CRUISE_ID, COMMENT)
VALUES (4, 5, 4, 4, 1, 'The liveaboard was well-maintained, and the dive sites were fantastic.'),
       (5, 4, 5, 4, 2, 'The crew provided excellent dive guidance, and the marine life in the Red Sea was incredible.'),
       (4, 4, 3, 5, 3, 'The Komodo National Park safari exceeded my expectations, and the crew was attentive and friendly.');

