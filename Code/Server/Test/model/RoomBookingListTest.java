package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RoomBookingListTest
{
  Room room1 = new Room("1.01", RoomType.SINGLE, 1);
  Room room2 = new Room("1.02", RoomType.DOUBLE, 2);
  Room room3 = new Room("1.03", RoomType.FAMILY, 3);
  Room room4 = new Room("1.04", RoomType.SUITE, 2);
  Room room5 = new Room("1.05", RoomType.SINGLE, 1);
  Room room6 = new Room("1.06", RoomType.SINGLE, 1);
  Room room7 = new Room("1.07", RoomType.SINGLE, 1);

  Guest guest = new Guest("Bob", "Builder", "bob@builder.test", 88888888);
  RoomBooking book1 = new RoomBooking(LocalDate.now(), LocalDate.now().plusDays(10), room1, guest);
  RoomBooking book2 = new RoomBooking(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20), room1, guest);
  RoomBooking book3 = new RoomBooking(LocalDate.now(), LocalDate.now().plusDays(10), room2, guest);
  RoomBooking book4 = new RoomBooking(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20), room2, guest);
  RoomBooking book5 = new RoomBooking(LocalDate.now(), LocalDate.now().plusDays(10), room3, guest);
  RoomBooking book6 = new RoomBooking(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20), room3, guest);
  RoomBooking book7 = new RoomBooking(LocalDate.now(), LocalDate.now().plusDays(10), room4, guest);
  RoomBooking book8 = new RoomBooking(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20), room4, guest);
  RoomBooking book9 = new RoomBooking(LocalDate.now(), LocalDate.now().plusDays(10), room5, guest);
  RoomBooking book10 = new RoomBooking(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20), room5, guest);
  RoomBooking book11 = new RoomBooking(LocalDate.now(), LocalDate.now().plusDays(10), room6, guest);
  RoomBooking book12 = new RoomBooking(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20), room6, guest);
  RoomBooking book13 = new RoomBooking(LocalDate.now(), LocalDate.now().plusDays(10), room7, guest);
  RoomBooking book14= new RoomBooking(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20), room7, guest);



  RoomBookingList roomBookingListEmpty;
  RoomBookingList roomBookingListWith1Booking;
  RoomBookingList roomBookingListWith3Bookings;
  RoomBookingList roomBookingListWith5Bookings;

  @BeforeEach void setUp()
  {
    roomBookingListEmpty = new RoomBookingList();
    roomBookingListWith1Booking = new RoomBookingList();
    roomBookingListWith1Booking.addBooking(book1);
    roomBookingListWith3Bookings = new RoomBookingList();
    roomBookingListWith3Bookings.addBooking(book1);
    roomBookingListWith3Bookings.addBooking(book2);
    roomBookingListWith3Bookings.addBooking(book3);
    roomBookingListWith5Bookings = new RoomBookingList();
    roomBookingListWith5Bookings.addBooking(book1);
    roomBookingListWith5Bookings.addBooking(book2);
    roomBookingListWith5Bookings.addBooking(book3);
    roomBookingListWith5Bookings.addBooking(book4);
    roomBookingListWith5Bookings.addBooking(book5);
  }

  // addBooking Test \\
  // Zero
  @Test void addNullValueBookingToList()
  {
    assertThrows(NullPointerException.class, ()-> roomBookingListWith3Bookings.addBooking(null));
  }

  @Test void addBookingToEmptyList()
  {
    roomBookingListEmpty.addBooking(book1);

    assertEquals(book1, roomBookingListEmpty.getBooking(0));
  }

  // One

  @Test void add1BookingToListContaining3()
  {
    roomBookingListWith3Bookings.addBooking(book4);
    assertEquals(4, roomBookingListWith3Bookings.bookedRoomListSize());
    assertEquals(book4, roomBookingListWith3Bookings.getBooking(3));
  }

  // Many
  @Test void add3BookingsToListContaining3()
  {
    roomBookingListWith3Bookings.addBooking(book4);
    roomBookingListWith3Bookings.addBooking(book5);
    roomBookingListWith3Bookings.addBooking(book6);
    assertEquals(6, roomBookingListWith3Bookings.bookedRoomListSize());
    assertEquals(book4, roomBookingListWith3Bookings.getBooking(3));
    assertEquals(book5, roomBookingListWith3Bookings.getBooking(4));
    assertEquals(book6, roomBookingListWith3Bookings.getBooking(5));
  }

  @Test void add5BookingsToListContaining3()
  {
    roomBookingListWith3Bookings.addBooking(book4);
    roomBookingListWith3Bookings.addBooking(book5);
    roomBookingListWith3Bookings.addBooking(book6);
    roomBookingListWith3Bookings.addBooking(book7);
    roomBookingListWith3Bookings.addBooking(book8);
    assertEquals(8, roomBookingListWith3Bookings.bookedRoomListSize());
    assertEquals(book4, roomBookingListWith3Bookings.getBooking(3));
    assertEquals(book5, roomBookingListWith3Bookings.getBooking(4));
    assertEquals(book6, roomBookingListWith3Bookings.getBooking(5));
    assertEquals(book7, roomBookingListWith3Bookings.getBooking(6));
    assertEquals(book8, roomBookingListWith3Bookings.getBooking(7));
  }

  @Test void add3BookingsToListContaining5()
  {
    roomBookingListWith5Bookings.addBooking(book6);
    roomBookingListWith5Bookings.addBooking(book7);
    roomBookingListWith5Bookings.addBooking(book8);
    assertEquals(8, roomBookingListWith5Bookings.bookedRoomListSize());
    assertEquals(book6, roomBookingListWith5Bookings.getBooking(5));
    assertEquals(book7, roomBookingListWith5Bookings.getBooking(6));
    assertEquals(book8, roomBookingListWith5Bookings.getBooking(7));
  }

  @Test void add5BookingsToListContaining5()
  {
    roomBookingListWith5Bookings.addBooking(book6);
    roomBookingListWith5Bookings.addBooking(book7);
    roomBookingListWith5Bookings.addBooking(book8);
    roomBookingListWith5Bookings.addBooking(book9);
    roomBookingListWith5Bookings.addBooking(book10);
    assertEquals(10, roomBookingListWith5Bookings.bookedRoomListSize());
    assertEquals(book6, roomBookingListWith5Bookings.getBooking(5));
    assertEquals(book7, roomBookingListWith5Bookings.getBooking(6));
    assertEquals(book8, roomBookingListWith5Bookings.getBooking(7));
    assertEquals(book9, roomBookingListWith5Bookings.getBooking(8));
    assertEquals(book10, roomBookingListWith5Bookings.getBooking(9));
  }

  // Boundary
  // No boundaries to test.

  // Exception
  // Already tested in Zero-case.
}