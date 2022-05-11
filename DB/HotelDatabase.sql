create schema hotel;
set schema 'hotel';

drop schema hotel cascade ;
create table if not exists room(
    roomID          varchar(20) PRIMARY KEY NOT NULL                                                  ,
    roomType        varchar(30) NOT NULL CHECK(roomType IN ('Family', 'Single', 'Double', 'Suite'))   ,
    nrBeds          integer     NOT NULL CHECK ( nrBeds between 1 AND 20)
);

drop table guest;
create table if not exists guest(
    fName           VARCHAR(60) NOT NULL                        ,
    lName           VARCHAR(60) NOT NULL                        ,
    email           VARCHAR(60) CHECK  ( email like ('%@%'))    ,
    phoneNr         integer PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS roomBooking(
    startDate       date                                         ,
    endDate         date                                         ,
    guest           integer                                      ,
    roomID          varchar(20)                                  ,
    PRIMARY KEY (guest, roomID, startDate)                       ,
    FOREIGN KEY (roomID) REFERENCES room(roomID)                 ,
    FOREIGN KEY (guest) REFERENCES guest(phoneNr)
);

ALTER TABLE roombooking ADD CONSTRAINT
    start_date_is_before_current_date CHECK ( startDate >= CURRENT_DATE);

ALTER TABLE roombooking ADD CONSTRAINT
    end_date_is_before_start_date CHECK ( startDate < endDate);

--------Guest-------

delete from guest;

insert into guest (fName, lName, email, phoneNr)
values ('Karolis', 'Anon','Karolis@gmail.com',11112222);

---------Room--------

delete  from room;

insert into room
values ('1','Single',1);

insert into room
values ('2','Double',3);

insert into room
values ('3','FAMILY',5);

----------Room-Booking---------
delete from roomBooking;

insert into roomBooking (startDate, endDate, guest, roomID)
values ('2022-06-10','2022-06-20',11112222,1);

insert into roomBooking
values ('2022-02-10','2022-02-20',11112222,1);

insert into roomBooking
values ('2022-03-10', '2000-03-20',11112222,1);

------All-Available-Rooms------>

select * from room where roomID in (select roomID from room
except
select roomID
from roomBooking
where startDate  between '2022-01-03' and '2022-01-11'
OR endDate between '2022-01-03' and '2022-01-11'
);
-------------------------------<

-----------Trigger------------->
-- Checking if this room is not booked during selected period
CREATE OR REPLACE FUNCTION double_booking()
	RETURNS trigger AS
$BODY$
DECLARE vBookingCount NUMERIC;

BEGIN

	SELECT COUNT(*) INTO vBookingCount
	FROM roomBooking
	WHERE roomID = new.roomID
	and new.startDate between startDate and endDate
	and new.endDate between startDate and endDate
	or(new.startDate < startDate and new.endDate > endDate)
	or(new.startDate > startDate and new.endDate < endDate);


	IF (vBookingCount > 0) THEN
		RAISE EXCEPTION 'Room % is already booked during these dates',
				new.roomID;
	END IF;
	return new;

END
$BODY$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS BookingDate
  ON roomBooking;

-- attaching trigger to roomBooking
CREATE TRIGGER BookingDate
BEFORE INSERT ON roomBooking
FOR EACH ROW
EXECUTE PROCEDURE double_booking();
-------------------------------<

-----------Trigger------------->

-------------------------------<

update room
set nrBeds = 11,
    roomType ='Suite'
where roomID = '1';

insert into roombooking
values ('2025-05-01','2025-05-05',20202022,1)
