package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ModelManagerTest
{
  Model managerEmpty;
  Model manager1Booking;
  Model manager3Bookings;
  Model manager5Bookings;
  Model manager4Bookings;

  Guest guest = new Guest("Bob", "Builder", "bob@builder.test", 88888888);

  @BeforeEach void setUp()
  {
    managerEmpty = new ModelManager();
    manager1Booking = new ModelManager();
    manager1Booking.book("1.01", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);

    manager3Bookings = new ModelManager();
    manager3Bookings.book("1.01", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);
    manager3Bookings.book("1.02", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);
    manager3Bookings.book("1.03", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);

    manager5Bookings = new ModelManager();
    manager5Bookings.book("1.01", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);
    manager5Bookings.book("1.02", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);
    manager5Bookings.book("1.03", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);
    manager5Bookings.book("1.04", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);
    manager5Bookings.book("1.05", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);

    manager4Bookings = new ModelManager();
    manager4Bookings.book("1.01", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);
    manager4Bookings.book("1.02", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);
    manager4Bookings.book("1.03", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);
    manager4Bookings.book("1.04", LocalDate.now(), LocalDate.now().plusDays(10),
        guest);
  }

  // availableRooms test \\

  // Zero
  @Test void availableRoomsOnEmptyList()
  {
    assertEquals(5, managerEmpty.availableRooms(LocalDate.now(),
        LocalDate.now().plusDays(10)).size());
  }

  @Test void availableRoomsNoRoomsAvailable()
  {
    assertEquals(0, manager5Bookings.availableRooms(LocalDate.now(),
        LocalDate.now().plusDays(5)).size());
  }

  // One
  @Test void availableRooms1Booking()
  {
    assertEquals(4, manager1Booking.availableRooms(LocalDate.now(),
        LocalDate.now().plusDays(10)).size());
  }

  @Test void availableRoomsAllButOneBooked()
  {
    assertEquals(1, manager4Bookings.availableRooms(LocalDate.now(),
        LocalDate.now().plusDays(10)).size());
  }

  // Many
  @Test void availableRooms2Available()
  {
    assertEquals(2, manager3Bookings.availableRooms(LocalDate.now(),
        LocalDate.now().plusDays(10)).size());
  }

  // Boundary
  // None to test

  // Exception
  // No exceptions to test

  // book testing \\

  // Zero
  @Test void bookOnEmptyList()
  {
    assertTrue(
        managerEmpty.book("1.01", LocalDate.now(), LocalDate.now().plusDays(10),
            guest));
  }

  @Test void bookOnFullList()
  {
    assertThrows(IllegalArgumentException.class,
        () -> manager5Bookings.book("1.01", LocalDate.now(),
            LocalDate.now().plusDays(10), guest));
  }

  // One
  @Test void bookOnListWith1Booking()
  {
    assertTrue(manager1Booking.book("1.02", LocalDate.now(), LocalDate.now().plusDays(10), guest));
  }


  // Many
  @Test void bookManyOnSameList()
  {
    assertTrue(managerEmpty.book("1.01", LocalDate.now(), LocalDate.now().plusDays(10), guest));
    assertTrue(managerEmpty.book("1.02", LocalDate.now(), LocalDate.now().plusDays(10), guest));
    assertTrue(managerEmpty.book("1.03", LocalDate.now(), LocalDate.now().plusDays(10), guest));
    assertTrue(managerEmpty.book("1.04", LocalDate.now(), LocalDate.now().plusDays(10), guest));
    assertTrue(managerEmpty.book("1.05", LocalDate.now(), LocalDate.now().plusDays(10), guest));
  }

  // Boundary
  // No boundaries to check

  // Exception
  // Exception already checked in Zero-case

}