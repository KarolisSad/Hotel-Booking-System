package model;

import com.sun.scenario.animation.shared.SingleLoopClipEnvelope;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ModelManagerTest
{

  Model model;
  Guest bob;


  @BeforeEach void setUp() throws SQLException
  {
    model = new ModelManager();
    bob = new Guest("Bob", "Builder", "bob@builder.com", 12345678, "BobBuilder", "BobBuilderPassword");
  }

  @AfterEach void tearDown() throws SQLException
  {
    model.clearDatabase();
  }


  ////////////////////////////////////////////////////
  //                                                //
  //                 addRoom testing                //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  @Test void addRoomWithNullAsArguments()
  {
    assertThrows(NullPointerException.class, ()-> model.addRoom(null, null, 0));
  }

  //  One:

  @Test void addOneRoom()
  {
    try
    {
      assertEquals(0, model.getAllRooms().size());
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      assertEquals(1, model.getAllRooms().size());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Many:

  @Test void add3Rooms()
  {
    try
    {
      assertEquals(0, model.getAllRooms().size());
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.addRoom("Test Room 2", RoomType.DOUBLE, 2);
      model.addRoom("Test Room 3", RoomType.FAMILY, 3);
      assertEquals(3, model.getAllRooms().size());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void add5Rooms()
  {
    try
    {
      assertEquals(0, model.getAllRooms().size());
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.addRoom("Test Room 2", RoomType.DOUBLE, 2);
      model.addRoom("Test Room 3", RoomType.FAMILY, 3);
      model.addRoom("Test Room 4", RoomType.SUITE, 3);
      model.addRoom("Test Room 5", RoomType.FAMILY, 3);
      assertEquals(5, model.getAllRooms().size());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Boundary:

  @Test void addRoomWith0BedsLowBoundary()
  {
    assertThrows(IllegalArgumentException.class, ()-> model.addRoom("Test Room 1", RoomType.SINGLE, 0));
  }

  @Test void addRoomWith1BedNoException()
  {
    assertDoesNotThrow(()-> model.addRoom("Test Room 1", RoomType.SINGLE, 1));
  }

  @Test void addRoomWith20BedsNoException()
  {
    assertDoesNotThrow(()-> model.addRoom("Test Room 1", RoomType.SINGLE, 20));
  }

  @Test void addRoomWith21BedsBoundary()
  {
    assertThrows(IllegalArgumentException.class, ()-> model.addRoom("Test Room 1", RoomType.SINGLE, 21));
  }

  //  Exception:

  @Test void addRoomWithAlreadyExistingRoomID()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      assertThrows(IllegalArgumentException.class, ()-> model.addRoom("Test Room 1", RoomType.DOUBLE, 1));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }


  ////////////////////////////////////////////////////
  //                                                //
  //                removeRoom testing              //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  @Test void removeRoomNullArgument()
  {
    assertThrows(IllegalArgumentException.class, ()-> model.removeRoom(null));
  }

  @Test void removeRoomNotInDatabase()
  {
    assertThrows(IllegalArgumentException.class, ()-> model.removeRoom("Test Room 1"));
  }

  //  One:

  @Test void removeOneRoomWhenDatabaseContainsOneRoom()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.removeRoom("Test Room 1");
      assertEquals(0, model.getAllRooms().size());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void removeOneRoomWhenDatabaseContains3Rooms()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.addRoom("Test Room 2", RoomType.SINGLE, 1);
      model.addRoom("Test Room 3", RoomType.SINGLE, 1);
      model.removeRoom("Test Room 3");
      assertEquals(2, model.getAllRooms().size());
      assertThrows(IllegalArgumentException.class, ()-> model.getRoom("Test Room 3"));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Many:

  @Test void remove3RoomsWhenDatabaseContaining3Rooms()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.addRoom("Test Room 2", RoomType.SINGLE, 1);
      model.addRoom("Test Room 3", RoomType.SINGLE, 1);
      model.removeRoom("Test Room 1");
      model.removeRoom("Test Room 2");
      model.removeRoom("Test Room 3");

      assertEquals(0, model.getAllRooms().size());
      assertThrows(IllegalArgumentException.class, ()-> model.getRoom("Test Room 1"));
      assertThrows(IllegalArgumentException.class, ()-> model.getRoom("Test Room 2"));
      assertThrows(IllegalArgumentException.class, ()-> model.getRoom("Test Room 3"));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void remove5RoomsWhenDatabaseContains7Rooms()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.addRoom("Test Room 2", RoomType.SINGLE, 1);
      model.addRoom("Test Room 3", RoomType.SINGLE, 1);
      model.addRoom("Test Room 4", RoomType.SINGLE, 1);
      model.addRoom("Test Room 5", RoomType.SINGLE, 1);
      model.addRoom("Test Room 6", RoomType.SINGLE, 1);
      model.addRoom("Test Room 7", RoomType.SINGLE, 1);
      model.removeRoom("Test Room 1");
      model.removeRoom("Test Room 2");
      model.removeRoom("Test Room 3");
      model.removeRoom("Test Room 4");
      model.removeRoom("Test Room 5");
      assertEquals(2, model.getAllRooms().size());
      assertThrows(IllegalArgumentException.class, ()-> model.getRoom("Test Room 1"));
      assertThrows(IllegalArgumentException.class, ()-> model.getRoom("Test Room 2"));
      assertThrows(IllegalArgumentException.class, ()-> model.getRoom("Test Room 3"));
      assertThrows(IllegalArgumentException.class, ()-> model.getRoom("Test Room 4"));
      assertThrows(IllegalArgumentException.class, ()-> model.getRoom("Test Room 5"));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Boundary:

     // No boundaries to check.

  //  Exception:

  @Test void removeRoomHavingActiveBookings()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.book("Test Room 1", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      assertThrows(IllegalArgumentException.class, ()-> model.removeRoom("Test Room 1"));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void removeRoomHavingCancelledBooking()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.book("Test Room 1", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      model.cancelBooking(model.getAllBookings("").getBooking(0).getBookingID());
      assertThrows(IllegalArgumentException.class, ()-> model.removeRoom("Test Room 1"));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }


  ////////////////////////////////////////////////////
  //                                                //
  //              availableRooms testing            //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  @Test void availableRoomsWhenNoRoomsCreated()
  {
    assertThrows(IllegalArgumentException.class, ()-> model.availableRooms(LocalDate.now(), LocalDate.now().plusDays(2)));
  }

  @Test void availableRoomsWhenStartDateIsNull()
  {
    assertThrows(NullPointerException.class, ()->model.availableRooms(null, LocalDate.now()));
  }

  @Test void availableRoomsWhenEndDateIsNull()
  {
    assertThrows(NullPointerException.class, ()->model.availableRooms(LocalDate.now(), null));
  }

  //  One:
  @Test void availableRoomsWith1RoomCreatedAvailable()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      assertEquals("[Room number: Test Room 1, Type: Single, Number of beds: 1]", model.availableRooms(LocalDate.now(), LocalDate.now().plusDays(2)).toString()
      );
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void availableRoomsWith1RoomCreatedUnavailable()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.book("Test Room 1", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      assertThrows(IllegalArgumentException.class, ()-> model.availableRooms(LocalDate.now(), LocalDate.now().plusDays(5)));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Many:

  @Test void availableRoomsWith5RoomsCreatedAllAvailable()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.addRoom("Test Room 2", RoomType.SINGLE, 1);
      model.addRoom("Test Room 3", RoomType.SINGLE, 1);
      model.addRoom("Test Room 4", RoomType.SINGLE, 1);
      model.addRoom("Test Room 5", RoomType.SINGLE, 1);
      assertEquals("[Room number: Test Room 1, Type: Single, Number of beds: 1, Room number: Test Room 2, Type: Single, Number of beds: 1, Room number: Test Room 3, Type: Single, Number of beds: 1, Room number: Test Room 4, Type: Single, Number of beds: 1, Room number: Test Room 5, Type: Single, Number of beds: 1]", model.availableRooms(LocalDate.now(), LocalDate.now().plusDays(2)).toString()
      );
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void availableRoomsWith5RoomsCreated3Available()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.addRoom("Test Room 2", RoomType.SINGLE, 1);
      model.addRoom("Test Room 3", RoomType.SINGLE, 1);
      model.addRoom("Test Room 4", RoomType.SINGLE, 1);
      model.addRoom("Test Room 5", RoomType.SINGLE, 1);
      model.book("Test Room 1", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      model.book("Test Room 2", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      assertEquals("[Room number: Test Room 3, Type: Single, Number of beds: 1, Room number: Test Room 4, Type: Single, Number of beds: 1, Room number: Test Room 5, Type: Single, Number of beds: 1]", model.availableRooms(LocalDate.now(), LocalDate.now().plusDays(2)).toString()
      );
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void availableRoomsWith5RoomsCreatedNoneAvailable()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.addRoom("Test Room 2", RoomType.SINGLE, 1);
      model.addRoom("Test Room 3", RoomType.SINGLE, 1);
      model.addRoom("Test Room 4", RoomType.SINGLE, 1);
      model.addRoom("Test Room 5", RoomType.SINGLE, 1);
      model.book("Test Room 1", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      model.book("Test Room 2", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      model.book("Test Room 3", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      model.book("Test Room 4", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      model.book("Test Room 5", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      assertThrows(IllegalArgumentException.class, ()->model.availableRooms(LocalDate.now(),
          LocalDate.now().plusDays(2)));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Boundary:

  @Test void availableRoomsStartDateIsBeforeToday()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.addRoom("Test Room 2", RoomType.SINGLE, 1);
      model.addRoom("Test Room 3", RoomType.SINGLE, 1);
      model.addRoom("Test Room 4", RoomType.SINGLE, 1);
      model.addRoom("Test Room 5", RoomType.SINGLE, 1);
      assertThrows(IllegalArgumentException.class, ()->model.availableRooms(LocalDate.now().minusDays(2),
          LocalDate.now()));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void availableRoomsEndDateIsEqualToStartDate()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.addRoom("Test Room 2", RoomType.SINGLE, 1);
      model.addRoom("Test Room 3", RoomType.SINGLE, 1);
      model.addRoom("Test Room 4", RoomType.SINGLE, 1);
      model.addRoom("Test Room 5", RoomType.SINGLE, 1);
      assertThrows(IllegalArgumentException.class, ()->model.availableRooms(LocalDate.now(),
          LocalDate.now()));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void availableRoomsEndDateIsBeforeStartDate()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.addRoom("Test Room 2", RoomType.SINGLE, 1);
      model.addRoom("Test Room 3", RoomType.SINGLE, 1);
      model.addRoom("Test Room 4", RoomType.SINGLE, 1);
      model.addRoom("Test Room 5", RoomType.SINGLE, 1);
      assertThrows(IllegalArgumentException.class, ()->model.availableRooms(LocalDate.now().plusDays(8),
          LocalDate.now()));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Exception:

      // All exceptions checked in previous cases.


  ////////////////////////////////////////////////////
  //                                                //
  //                    book testing                //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  @Test void bookWithNullElements()
  {
    assertThrows(NullPointerException.class, ()-> model.book(null, null, null, null));
  }

  //  One:

  @Test void bookOneRoom()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      assertEquals(0, model.getAllBookings("").bookedRoomListSize());
      model.book("Test Room 1", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      assertEquals(1, model.getAllBookings("").bookedRoomListSize());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Many:

  @Test void bookOneRoom3Times()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      assertEquals(0, model.getAllBookings("").bookedRoomListSize());
      model.book("Test Room 1", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      model.book("Test Room 1", LocalDate.now().plusDays(3), LocalDate.now().plusDays(5), bob);
      model.book("Test Room 1", LocalDate.now().plusDays(6), LocalDate.now().plusDays(8), bob);
      assertEquals(3, model.getAllBookings("").bookedRoomListSize());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void book5Rooms3TimesEach()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.addRoom("Test Room 2", RoomType.SINGLE, 1);
      model.addRoom("Test Room 3", RoomType.SINGLE, 1);
      model.addRoom("Test Room 4", RoomType.SINGLE, 1);
      model.addRoom("Test Room 5", RoomType.SINGLE, 1);
      assertEquals(0, model.getAllBookings("").bookedRoomListSize());
      model.book("Test Room 1", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      model.book("Test Room 1", LocalDate.now().plusDays(3), LocalDate.now().plusDays(5), bob);
      model.book("Test Room 1", LocalDate.now().plusDays(6), LocalDate.now().plusDays(8), bob);
      model.book("Test Room 2", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      model.book("Test Room 2", LocalDate.now().plusDays(3), LocalDate.now().plusDays(5), bob);
      model.book("Test Room 2", LocalDate.now().plusDays(6), LocalDate.now().plusDays(8), bob);
      model.book("Test Room 3", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      model.book("Test Room 3", LocalDate.now().plusDays(3), LocalDate.now().plusDays(5), bob);
      model.book("Test Room 3", LocalDate.now().plusDays(6), LocalDate.now().plusDays(8), bob);
      model.book("Test Room 4", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      model.book("Test Room 4", LocalDate.now().plusDays(3), LocalDate.now().plusDays(5), bob);
      model.book("Test Room 4", LocalDate.now().plusDays(6), LocalDate.now().plusDays(8), bob);
      model.book("Test Room 5", LocalDate.now(), LocalDate.now().plusDays(2), bob);
      model.book("Test Room 5", LocalDate.now().plusDays(3), LocalDate.now().plusDays(5), bob);
      model.book("Test Room 5", LocalDate.now().plusDays(6), LocalDate.now().plusDays(8), bob);
      assertEquals(15, model.getAllBookings("").bookedRoomListSize());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Boundary:

     // All relevant boundaries already tested in AvailableRooms-case.

  //  Exception:

      // Only exception tested in Zero case.


  ////////////////////////////////////////////////////
  //                                                //
  //              editRoomInfo testing              //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  @Test void editRoomInfoAllNullValues()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      assertThrows(NullPointerException.class, ()-> model.editRoomInfo(null, null, 0));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void editRoomInfoRoomIdNullValue()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      assertThrows(NullPointerException.class, ()-> model.editRoomInfo(null, RoomType.SINGLE, 2));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void editRoomInfoRoomTypeNullValue()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      assertThrows(NullPointerException.class, ()-> model.editRoomInfo("Test Room 1", null, 2));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void editRoomInfoNrBedsNullValue()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      assertThrows(IllegalArgumentException.class, ()-> model.editRoomInfo("Test Room 1", RoomType.SINGLE, 0));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  One:

  @Test void editRoomInfoEditTypeOfOneRoom()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.editRoomInfo("Test Room 1", RoomType.DOUBLE, 1);
      assertEquals("Double", model.getRoom("Test Room 1").getRoomType().toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void editRoomInfoEditNrBedsOfOneRoom()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      model.editRoomInfo("Test Room 1", RoomType.SINGLE, 3);
      assertEquals(3, model.getRoom("Test Room 1").getNumberOfBeds());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Many:

  @Test void editRoomInfoEditAllPossibleVariables()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      assertEquals("Room number: Test Room 1, Type: Single, Number of beds: 1", model.getRoom("Test Room 1").toString());
      model.editRoomInfo("Test Room 1", RoomType.SUITE, 3);
      assertEquals("Room number: Test Room 1, Type: Suite, Number of beds: 3", model.getRoom("Test Room 1").toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Boundary:

  @Test void editRoomInfoSetNrBedsToBelowLowBoundary()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      assertThrows(IllegalArgumentException.class, ()-> model.editRoomInfo("Test Room 1", RoomType.SINGLE, 0));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void editRoomInfoSetNrBedsToAboveMaxBoundary()
  {
    try
    {
      model.addRoom("Test Room 1", RoomType.SINGLE, 1);
      assertThrows(IllegalArgumentException.class, ()-> model.editRoomInfo("Test Room 1", RoomType.SINGLE, 21));
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Exception:

      // No further exceptions to check.


  ////////////////////////////////////////////////////
  //                                                //
  //               getAllRooms testing              //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  @Test void getAllRoomsWithNoRoomsCreated()
  {
    assertThrows(IllegalArgumentException.class, ()-> model.getAllRooms());
  }

  //  One:

  @Test void getAllRoomsWithOneRoomCreated()
  {
    try
    {
      model.addRoom("1", RoomType.SINGLE, 1);
      assertEquals("[Room number: 1, Type: Single, Number of beds: 1]", model.getAllRooms().toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Many:

  @Test void getAllRoomsWith3RoomsCreated()
  {
    try
    {
      model.addRoom("1", RoomType.SINGLE, 1);
      model.addRoom("2", RoomType.SINGLE, 1);
      model.addRoom("3", RoomType.SINGLE, 1);
      assertEquals("[Room number: 1, Type: Single, Number of beds: 1,"
          + " Room number: 2, Type: Single, Number of beds: 1,"
          + " Room number: 3, Type: Single, Number of beds: 1]", model.getAllRooms().toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getAllRoomsWith5RoomsCreated()
  {
    try
    {
      model.addRoom("1", RoomType.SINGLE, 1);
      model.addRoom("2", RoomType.SINGLE, 1);
      model.addRoom("3", RoomType.SINGLE, 1);
      model.addRoom("4", RoomType.SINGLE, 1);
      model.addRoom("5", RoomType.SINGLE, 1);
      assertEquals("[Room number: 1, Type: Single, Number of beds: 1,"
          + " Room number: 2, Type: Single, Number of beds: 1,"
          + " Room number: 3, Type: Single, Number of beds: 1,"
          + " Room number: 4, Type: Single, Number of beds: 1,"
          + " Room number: 5, Type: Single, Number of beds: 1]", model.getAllRooms().toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Boundary:

      // No boundaries to check

  //  Exception:

      // Only possible exception already checked in zero case.


  ////////////////////////////////////////////////////
  //                                                //
  //             getAllBookings testing             //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  @Test void getAllBookingsWithNoBookings()
  {
    try
    {
      assertEquals("[]", model.getAllBookings("").toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getAllCancelledBookingsZero()
  {
    try
    {
      model.addRoom("1", RoomType.SINGLE, 1);
      model.book("1", LocalDate.now(), LocalDate.now().plusDays(1), bob);
      model.book("1", LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), bob);
      model.processBooking(model.getAllBookings("").getBooking(1).getBookingID());
      assertEquals("[]", model.getAllBookings("cancelled").toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getAllArchivedBookingsZero()
  {
    try
    {
      model.addRoom("1", RoomType.SINGLE, 1);
      model.book("1", LocalDate.now(), LocalDate.now().plusDays(1), bob);
      model.book("1", LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), bob);
      model.processBooking(model.getAllBookings("").getBooking(1).getBookingID());
      assertEquals("[]", model.getAllBookings("archived").toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getAllInProgressBookingsZero()
  {
    try
    {
      model.addRoom("1", RoomType.SINGLE, 1);
      model.book("1", LocalDate.now(), LocalDate.now().plusDays(1), bob);
      model.book("1", LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), bob);
      model.processBooking(model.getAllBookings("").getBooking(1).getBookingID());
      model.processBooking(model.getAllBookings("").getBooking(1).getBookingID());
      assertEquals("[]", model.getAllBookings("inProgress").toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getAllBookedBookingsZero()
  {
    try
    {
      model.addRoom("1", RoomType.SINGLE, 1);
      model.book("1", LocalDate.now(), LocalDate.now().plusDays(1), bob);
      model.book("1", LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), bob);
      model.processBooking(model.getAllBookings("").getBooking(1).getBookingID());
      model.processBooking(model.getAllBookings("").getBooking(0).getBookingID());
      assertEquals("[]", model.getAllBookings("booked").toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  One:

  @Test void getAllBookingsWith1BookingInDB()
  {
    try
    {
      model.addRoom("1", RoomType.SINGLE, 1);
      model.book("1", LocalDate.now(), LocalDate.now().plusDays(1), bob);
      assertEquals("[<RoomBooking> BookingID: " + model.getAllBookings("").getBooking(0).getBookingID() +
              ", StartDate: " + LocalDate.now() + ","
              + " EndDate: "+ LocalDate.now().plusDays(1) +", Room: Room number: 1,"
              + " Type: Single, Number of beds: 1, Guest: Guest: 'First Name: 'Bob'"
              + "Last Name: Builder'Email address: bob@builder.com'Phone Nr: 12345678, State: Booked]",
          model.getAllBookings("").toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getAllCancelledBookingsWith1InDB()
  {
    try
    {
      model.addRoom("1", RoomType.SINGLE, 1);
      model.book("1", LocalDate.now(), LocalDate.now().plusDays(1), bob);
      model.book("1", LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), bob);
      model.book("1", LocalDate.now().plusDays(4), LocalDate.now().plusDays(5), bob);
      model.cancelBooking(model.getAllBookings("").getBooking(0).getBookingID());

      assertEquals("[<RoomBooking> BookingID: " + model.getAllBookings("cancelled").getBooking(0).getBookingID() + ","
              + " StartDate: " + LocalDate.now() + ","
              + " EndDate: "+LocalDate.now().plusDays(1)+", Room: Room number: 1,"
              + " Type: Single, Number of beds: 1, Guest: Guest: 'First Name: 'Bob'"
              + "Last Name: Builder'Email address: bob@builder.com'Phone Nr: 12345678, State: Cancelled]",
          model.getAllBookings("cancelled").toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getAllArchivedBookingsWith1InDB()
  {
    try
    {
      model.addRoom("1", RoomType.SINGLE, 1);
      model.book("1", LocalDate.now(), LocalDate.now().plusDays(1), bob);
      model.book("1", LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), bob);
      model.book("1", LocalDate.now().plusDays(4), LocalDate.now().plusDays(5), bob);
      model.processBooking(model.getAllBookings("").getBooking(0).getBookingID());
      model.processBooking(model.getAllBookings("").getBooking(0).getBookingID());

      assertEquals("[<RoomBooking> BookingID: " + model.getAllBookings("archived").getBooking(0).getBookingID() + ","
              + " StartDate: " + LocalDate.now() + ","
          + " EndDate: "+LocalDate.now().plusDays(1)+", Room: Room number: 1,"
              + " Type: Single, Number of beds: 1, Guest: Guest: 'First Name: 'Bob'"
              + "Last Name: Builder'Email address: bob@builder.com'Phone Nr: 12345678, State: Archived]",
          model.getAllBookings("archived").toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getAllInProgressBookingsWith1InDB()
  {
    try
    {
      model.addRoom("1", RoomType.SINGLE, 1);
      model.book("1", LocalDate.now(), LocalDate.now().plusDays(1), bob);
      model.book("1", LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), bob);
      model.book("1", LocalDate.now().plusDays(4), LocalDate.now().plusDays(5), bob);
      model.processBooking(model.getAllBookings("").getBooking(0).getBookingID());

      assertEquals("[<RoomBooking> BookingID: " + model.getAllBookings("inProgress").getBooking(0).getBookingID() + ","
              + " StartDate: " + LocalDate.now() + ","
              + " EndDate: "+LocalDate.now().plusDays(1)+", Room: Room number: 1,"
              + " Type: Single, Number of beds: 1, Guest: Guest: 'First Name: 'Bob'"
              + "Last Name: Builder'Email address: bob@builder.com'Phone Nr: 12345678, State: In progress]",
          model.getAllBookings("inProgress").toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Test void getAllBookedBookingsWith1InDB()
  {
    try
    {
      model.addRoom("1", RoomType.SINGLE, 1);
      model.book("1", LocalDate.now(), LocalDate.now().plusDays(1), bob);
      model.book("1", LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), bob);
      model.book("1", LocalDate.now().plusDays(4), LocalDate.now().plusDays(5), bob);
      model.processBooking(model.getAllBookings("").getBooking(1).getBookingID());
      model.processBooking(model.getAllBookings("").getBooking(2).getBookingID());

      assertEquals("[<RoomBooking> BookingID: " + model.getAllBookings("booked").getBooking(0).getBookingID() + ","
              + " StartDate: " + LocalDate.now() + ","
              + " EndDate: "+LocalDate.now().plusDays(1)+", Room: Room number: 1,"
              + " Type: Single, Number of beds: 1, Guest: Guest: 'First Name: 'Bob'"
              + "Last Name: Builder'Email address: bob@builder.com'Phone Nr: 12345678, State: Booked]",
          model.getAllBookings("booked").toString());
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  //  Many:

  //  Boundary:

  //  Exception:


  ////////////////////////////////////////////////////
  //                                                //
  //             processBooking testing             //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  //  One:

  //  Many:

  //  Boundary:

  //  Exception:


  ////////////////////////////////////////////////////
  //                                                //
  //              cancelBooking testing             //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  //  One:

  //  Many:

  //  Boundary:

  //  Exception:


  ////////////////////////////////////////////////////
  //                                                //
  //             editBooking testing                //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  //  One:

  //  Many:

  //  Boundary:

  //  Exception:


  ////////////////////////////////////////////////////
  //                                                //
  //                editGuest testing               //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  //  One:

  //  Many:

  //  Boundary:

  //  Exception:


  ////////////////////////////////////////////////////
  //                                                //
  //             getAllGuests testing               //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  //  One:

  //  Many:

  //  Boundary:

  //  Exception:


  ////////////////////////////////////////////////////
  //                                                //
  //                getRoom testing                 //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  //  One:

  //  Many:

  //  Boundary:

  //  Exception:


  ////////////////////////////////////////////////////
  //                                                //
  //               register testing                 //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  //  One:

  //  Many:

  //  Boundary:

  //  Exception:


  ////////////////////////////////////////////////////
  //                                                //
  //                login testing                   //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  //  One:

  //  Many:

  //  Boundary:

  //  Exception:


  ////////////////////////////////////////////////////
  //                                                //
  //         getBookingsWhenLoggedIn testing        //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  //  One:

  //  Many:

  //  Boundary:

  //  Exception:


  ////////////////////////////////////////////////////
  //                                                //
  //         bookARoomWhenLoggedIn testing          //
  //                                                //
  ////////////////////////////////////////////////////

  //  Zero:

  //  One:

  //  Many:

  //  Boundary:

  //  Exception:

}