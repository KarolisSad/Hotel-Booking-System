-- DELETE ALL --
DROP SCHEMA hotel CASCADE;
--------------------------


CREATE SCHEMA hotel;
SET SCHEMA 'hotel';

CREATE TABLE IF NOT EXISTS room
(
    roomID   varchar(20) PRIMARY KEY NOT NULL,
    roomType varchar(30)             NOT NULL CHECK (roomType IN ('Family', 'Single', 'Double', 'Suite', 'Conference')),
    nrBeds   integer                 NOT NULL CHECK ( nrBeds BETWEEN 1 AND 20),
    dailyPrice  integer NOT NULL CHECK ( dailyPrice > 0 )
);

CREATE TABLE IF NOT EXISTS guest
(
    username varchar(100) NOT NULL PRIMARY KEY,
    fName    VARCHAR(60)  NOT NULL,
    lName    VARCHAR(60)  NOT NULL,
    email    VARCHAR(60) CHECK ( email LIKE ('%@%')),
    phoneNr  integer      NOT NULL
);
CREATE TABLE IF NOT EXISTS roomBooking
(
    bookingID SERIAL PRIMARY KEY,
    startDate date,
    endDate   date,
    guest     varchar(100),
    roomID    varchar(20),
    state     varchar(12),
    FOREIGN KEY (roomID) REFERENCES room (roomID),
    FOREIGN KEY (guest) REFERENCES guest (username)
);

CREATE TABLE IF NOT EXISTS login
(
    username     varchar(100) PRIMARY KEY,
    userPassword varchar(100) NOT NULL,
    FOREIGN KEY (username) REFERENCES guest (username)
);

ALTER TABLE roomBooking
    ADD CONSTRAINT
        start_date_is_before_current_date CHECK ( startDate >= CURRENT_DATE);

ALTER TABLE roomBooking
    ADD CONSTRAINT
        end_date_is_before_start_date CHECK ( startDate < endDate);


-------------------------------<

-----------Trigger------------->
-- Checking if this room is not booked during selected period
CREATE OR REPLACE FUNCTION double_booking()
    RETURNS trigger AS
$BODY$
DECLARE
    vBookingCount NUMERIC;

BEGIN


    SELECT COUNT(*)
    INTO vBookingCount
    FROM roomBooking
    WHERE roomID = new.roomID
        AND old.state NOT LIKE 'Cancelled'
        AND (new.startDate BETWEEN startDate AND endDate
            OR new.endDate BETWEEN startDate AND endDate)
       OR (new.startDate < startDate AND new.endDate > endDate)
       OR (new.startDate > startDate AND new.endDate < endDate);


    IF (vBookingCount > 0) THEN
        RAISE EXCEPTION 'Room % is already booked during these dates',
            new.roomID;
    END IF;
    RETURN new;

END
$BODY$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS BookingDate
    ON roomBooking;

-- attaching trigger to roomBooking
CREATE TRIGGER BookingDate
    BEFORE INSERT
    ON roomBooking
    FOR EACH ROW
EXECUTE PROCEDURE double_booking();



-------------------------------<

-----------Trigger------------->

-------------------------------<

--------------------------------
------ NEW FUNCTION ------------

CREATE OR REPLACE FUNCTION update_booking()
    RETURNS trigger AS
$BODY$
DECLARE
    vBookingCount NUMERIC;

BEGIN


    SELECT COUNT(*)
    INTO vBookingCount
    FROM roomBooking
    WHERE roomID = new.roomID
        AND bookingID != new.bookingID
        AND (new.startDate BETWEEN startDate AND endDate
            OR new.endDate BETWEEN startDate AND endDate)
       OR (new.startDate < startDate AND new.endDate > endDate)
       OR (new.startDate > startDate AND new.endDate < endDate);


    IF (vBookingCount > 0) THEN
        RAISE EXCEPTION 'Room % is already booked during these dates',
            new.roomID;
    END IF;
    RETURN new;

END
$BODY$ LANGUAGE plpgsql;


CREATE TRIGGER BookingDateUpdate
    BEFORE UPDATE
    ON roomBooking
    FOR EACH ROW
    WHEN ( old.state = new.state )
EXECUTE PROCEDURE update_booking();

-----------Views--------------->
create view conferenceRooms as
select *
from room
where roomType = 'Conference';


create view regularRooms as
select *
from room
where roomType not in ('Conference');
-------------------------------<


drop view conferenceRooms;
drop view conferenceRooms;






--------Guest-------

DELETE
FROM guest;

INSERT INTO guest (fName, lName, email, phoneNr)
VALUES ('Karolis', 'Anon', 'Karolis@gmail.com', 11112222);

---------Room--------

DELETE
FROM room;

INSERT INTO room
VALUES ('1', 'Single', 1);

INSERT INTO room
VALUES ('2', 'Double', 3);

INSERT INTO room
VALUES ('3', 'Family', 5);

----------Room-Booking---------
DELETE
FROM roomBooking;

INSERT INTO roomBooking (startDate, endDate, guest, roomID, state)
VALUES ('2022-06-10', '2022-06-20', 11112222, 1, 'Booked');

INSERT INTO roomBooking (startDate, endDate, guest, roomID, state)
VALUES ('2022-06-10', '2022-06-20', 11112222, 3, 'Booked');

INSERT INTO roomBooking (startDate, endDate, guest, roomID, state)
VALUES ('2023-06-10', '2023-06-20', 11112222, 3, 'Booked');

------All-Available-Rooms------>

SELECT *
FROM room
WHERE roomID IN (SELECT roomID
                 FROM room
                 EXCEPT
                 SELECT roomID
                 FROM roomBooking
                 WHERE startDate BETWEEN '2022-01-03' AND '2022-01-11'
                    OR endDate BETWEEN '2022-01-03' AND '2022-01-11'
);

SELECT *
FROM guest
WHERE username = 'christhougaard';

-- Ignore cancelled bookings testing:

SELECT *
FROM room
WHERE roomID IN (SELECT roomID
    FROM room
    EXCEPT
        SELECT roomID
                 FROM roomBooking
                 WHERE state in ('Booked', 'In Progress', 'Archived') AND
                         (startDate BETWEEN '2022-05-27' AND '2022-05-29'
                         OR endDate BETWEEN '2022-05-27' AND '2022-05-29'));

-------------------------------<
insert into login(username, userPassword) VALUES ('nina', 'password');

insert into guest (username, fName, lName, email, phoneNr)
values ('nina', 'Nina', 'Wrona', 'n@gmail.com', 12344567);

update guest
                 set fname = 'Maria',
                 lname = 'Cook',
                 email ='m@g.com',
                 phonenr = 12345678
where username = 'nina';

select * from guest where username = 'nina';
