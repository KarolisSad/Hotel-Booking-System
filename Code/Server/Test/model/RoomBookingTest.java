package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RoomBookingTest
{
  LocalDate today = LocalDate.now();
  LocalDate end = today.plusDays(5);
  Room testRoom = new Room("TestRoom101", RoomType.FAMILY, 3);
  Room testRoom2 = new Room("TestRoom102", RoomType.FAMILY, 3);
  Room testRoom3 = new Room("TestRoom103", RoomType.FAMILY, 3);
  Guest guest = new Guest("Bob", "Builder", "bob@builder.test", 12345678);
  Guest guest2 = new Guest("Wendy", "Worker", "wendy@worker.test", 87654321);
  Guest guest3 = new Guest("Allan", "Test", "allan@test.test", 55555555);
  RoomBooking testBooking;

  @BeforeEach void setUp()
  {
    testBooking = new RoomBooking(today, end, testRoom, guest);
  }

  // setDates test \\

  // Zero
  @Test void setDatesToNullValue()
  {
    assertThrows(NullPointerException.class,
        () -> testBooking.setStartAndEndDate(null, null));
  }

  @Test void setStartDateToZeroValue()
  {
    assertThrows(IllegalArgumentException.class,
        () -> testBooking.setStartAndEndDate(LocalDate.MIN, end));
  }

  @Test void setDatesToSameValue()
  {
    assertThrows(IllegalArgumentException.class,
        () -> testBooking.setStartAndEndDate(LocalDate.of(2022, 10, 10),
            LocalDate.of(2022, 10, 10)));
  }

  // One
  @Test void setDatesToDifferentValues1DayApart()
  {
    testBooking.setStartAndEndDate(LocalDate.of(2022, 10, 10),
        LocalDate.of(2022, 10, 11));
    assertEquals("2022-10-10, 2022-10-11",
        testBooking.getStartDate() + ", " + testBooking.getEndDate());
  }

  // Many
  @Test void setDatesToDifferentValues10DaysApart()
  {
    testBooking.setStartAndEndDate(LocalDate.of(2022, 10, 10),
        LocalDate.of(2022, 10, 20));
    assertEquals("2022-10-10, 2022-10-20",
        testBooking.getStartDate() + ", " + testBooking.getEndDate());
  }

  @Test void setDatesToDifferentValues5DaysApart()
  {
    testBooking.setStartAndEndDate(LocalDate.of(2022, 10, 10),
        LocalDate.of(2022, 10, 15));
    assertEquals("2022-10-10, 2022-10-15",
        testBooking.getStartDate() + ", " + testBooking.getEndDate());
  }

  // Boundary
  @Test void setDatesStartDate1DayBeforeToday()
  {
    assertThrows(IllegalArgumentException.class,
        () -> testBooking.setStartAndEndDate(today.minusDays(1), end));
  }

  @Test void setEndDayBeforeStartDay()
  {
    assertThrows(IllegalArgumentException.class,
        () -> testBooking.setStartAndEndDate(today, today.minusDays(1)));
  }

  // Exception
  // All exceptions tested above.

  // setRoom test \\
  // Zero
  @Test void setRoomToNullValue()
  {
    assertThrows(NullPointerException.class, ()-> testBooking.setRoom(null));
  }

  // One
  @Test void setRoomToNewValue()
  {
    testBooking.setRoom(testRoom2);
    assertEquals(testRoom2.getRoomId(), testBooking.getRoom().getRoomId());
  }

  // Many
  @Test void setRoomTo3DifferentValues()
  {
    testBooking.setRoom(testRoom2);
    testBooking.setRoom(testRoom);
    testBooking.setRoom(testRoom3);
    assertEquals(testRoom3.getRoomId(), testBooking.getRoom().getRoomId());
  }

  // Boundary
  //No Boundaries to test

  // Exception
  // Exception tested in Zero-case.

  // setGuest test \\
  // Zero
  @Test void setGuestToNullValue()
  {
    assertThrows(NullPointerException.class, ()-> testBooking.setGuest(null));
  }

  // One
  @Test void setGuestToNewValue()
  {
    testBooking.setGuest(guest2);
    assertEquals(guest2.toString(), testBooking.getGuest().toString());
  }

  // Many
  @Test void setGuestTo3Values()
  {
    testBooking.setGuest(guest2);
    testBooking.setGuest(guest);
    testBooking.setGuest(guest3);
    assertEquals(guest3.toString(), testBooking.getGuest().toString());
  }
  // Boundary
  // No boundaries to check

  // Exception
  // Already tested in Zero-case.

}