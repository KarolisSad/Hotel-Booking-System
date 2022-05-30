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
            OR new.endDate BETWEEN startDate AND endDate
       OR (new.startDate < startDate AND new.endDate > endDate)
       OR (new.startDate > startDate AND new.endDate < endDate))
    EXCEPT SELECT count(*) FROM roomBooking WHERE roomID = new.roomID AND state = 'Cancelled';


    IF (vBookingCount > 0) THEN
        RAISE EXCEPTION 'Room % is already booked during these dates.',
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

-----------Dummy-Data---------->
insert into guest(username, fName, lName, email, phoneNr)
values ('bob','Bob','Builder','BobBuilder@gmail.com', 88851515);
insert into login(username, userPassword)
values ('bob', 'builder');
insert into guest(username, fName, lName, email, phoneNr)
values ('julia','Julia','Mcclain', 'JuliaMcclain@gmail.com', 15637557);
insert into login(username, userPassword)
values ('julia', 'password');
insert into guest(username, fName, lName, email, phoneNr)
values ('norman','Norman','William', 'NormanWilliam@gmail.com', 12637227);
insert into login(username, userPassword)
values ('norman', 'password');
insert into guest(username, fName, lName, email, phoneNr)
values ('Filip','Filip','Joyner', 'Flip@gmail.com', 12437212);
insert into login(username, userPassword)
values ('Filip', 'password');


insert into room(roomID, roomType, nrBeds, dailyPrice)
values ('202','Family',4,950);
insert into room(roomID, roomType, nrBeds, dailyPrice)
values ('101','Single',1,350);
insert into room(roomID, roomType, nrBeds, dailyPrice)
values ('210','Double',4,650);
insert into room(roomID, roomType, nrBeds, dailyPrice)
values ('303','Family',5,1200);
insert into room(roomID, roomType, nrBeds, dailyPrice)
values ('104','Single',1,350);

insert into roomBooking(startDate, endDate, guest, roomID, state)
values ('2022-06-09', '2022-06-11','Filip','303','Booked');
insert into roomBooking(startDate, endDate, guest, roomID, state)
values ('2022-07-11', '2022-07-14','Filip','202','Booked');
insert into roomBooking(startDate, endDate, guest, roomID, state)
values ('2022-07-11', '2022-07-17','bob','210','Booked');
insert into roomBooking(startDate, endDate, guest, roomID, state)
values ('2022-06-09', '2022-06-14','julia','104','Booked');
insert into roomBooking(startDate, endDate, guest, roomID, state)
values ('2022-06-22', '2022-06-24','norman','101','Booked');
-------------------------------<

