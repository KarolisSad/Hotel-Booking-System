package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class that implements the Model interface and manages the bookings.
 *
 * @author Group 5
 * @version 04/05/2022
 */

public class ModelManager implements Model
{
  private RoomList roomList;

  // todo CHR
  private RoomBookingList bookingList;
  private PropertyChangeSupport property;
  private HotelPersistence dataBaseAdapter;

  /**
   * A constructor that is meant to initialize
   * the instance variables as a new array lists
   * that will store a list of all rooms and a list of booked rooms.
   * This constructor also initializes the adapter used for communication with the database.
   */
  public ModelManager()
  {
    bookingList = new RoomBookingList();
    roomList = new RoomList();
    property = new PropertyChangeSupport(this);
    this.dataBaseAdapter = new HotelDataBase();
    createDummyData();
  }


  //todo delete?
  private void createDummyData()
  {
    //        roomList.addRoom(new Room("1.01", RoomType.SINGLE, 1));
    //        roomList.addRoom(new Room("1.02", RoomType.DOUBLE, 1));
    //        roomList.addRoom(new Room("1.03", RoomType.FAMILY, 3));
    //        roomList.addRoom(new Room("1.04", RoomType.DOUBLE, 1));
    //        roomList.addRoom(new Room("1.05", RoomType.SUITE, 3));
  }

  /**
   * A method that returns all available rooms by selected date
   *
   * @param startDate start date
   * @param endDate   end date
   * @return available rooms
   */
  @Override public ArrayList<Room> availableRooms(LocalDate startDate,
      LocalDate endDate) throws SQLException
  {
    checkForLegalDates(startDate, endDate);
    return dataBaseAdapter.availableRooms(startDate, endDate);
  }

  /**
   * Method creating a new Room object and adding it to a RoomList array.
   *
   * @param roomId the ID of the room to be added
   * @param type   the type of room to be added
   * @param nrBeds the number of beds in the room to be added
   * @return true if room is added successfully
   */
  @Override public void addRoom(String roomId, RoomType type, int nrBeds)
      throws SQLException
  {

    if (roomId.equals(""))
    {
      // todo change it
      throw new IllegalArgumentException("non");
    }
    dataBaseAdapter.addRoom(roomId, type, nrBeds);
  }

  /**
   * Method removing a room from the list of rooms in the system.
   * To prevent data-corruption, it checks if the room corresponding to the ID passed as an argument has any current or future bookings
   * using the isBookingAllowed method, and if this is not the case - removes it from the list.
   *
   * @param roomId the ID of the room to be removed
   * @return true if room is removed successfully
   * @throws IllegalArgumentException if room to be removed has any current or future bookings.
   */
  @Override public void removeRoom(String roomId) throws SQLException
  {
    //            property.firePropertyChange("RoomRemove", roomList, roomId);
    dataBaseAdapter.remove(roomId);
  }

  /**
   * Method returning the list of all rooms currently in the system.
   *
   * @return list of all rooms.
   */
  @Override public ArrayList<Room> getAllRooms() throws SQLException
  {
    return dataBaseAdapter.getAllRooms();
  }

  /**
   * A method calling the database and filling the bookingList with all bookings corresponding to the type given as argument.
   * @param type The type of bookings to get.
   * @return the bookinglist variable, filled with bookings corresponding to the type given as argument.
   *
   */
  @Override public RoomBookingList getAllBookings(String type)
      throws SQLException
  {
    bookingList.setAllBookings(dataBaseAdapter.getAllBookings(type));
    return bookingList;
  }

  /**
   * A method calling the processBooking methods in the bookinglist, and in the database.
   * By doing this, the state of the booking is changed according to the RoomBookingState-class and it's subclasses, and the booking is updated accordingly in the database.
   * @param id The id of the booking to process
   * @throws SQLException
   */
  @Override public void processBooking(int id) throws SQLException
  {
    bookingList.processBooking(id);
    dataBaseAdapter.processBooking(bookingList.getBookingById(id));
  }

  // TODO should probably be changed - we need to decide what to do when cancelling a booking. @Karolis??
  @Override public void cancelBooking(int id) throws SQLException
  {
    bookingList.cancelBooking(id);
    dataBaseAdapter.cancelBooking(bookingList.getBookingById(id));
  }

  /**
   * Method used for editing a room already added to the system.
   * Firstly, the room is received from the roomList and then the roomtype and number of beds variables are changed according to the values passed as arguments.
   *
   * @param roomId the room id of the room to be edited (The room id of the room is intentionally not possible to change with this method)
   * @param type   A string value representing the (new) type of the room.
   * @param nrBeds The (new) number of beds in the room.
   * @return true if editing succeeds
   */
  @Override public void editRoomInfo(String roomId, RoomType type, int nrBeds)
      throws SQLException
  {
    dataBaseAdapter.editRoomInfo(roomId, type, nrBeds);
  }

  /**
   * A method that is meant for booking a room.
   *
   * @param roomId    room number
   * @param startDate start date
   * @param endDate   end date
   * @param guest     guest
   * @return true if the room is booked and false if the room is not booked
   */
  @Override public void book(String roomId, LocalDate startDate,
      LocalDate endDate, Guest guest) throws SQLException
  {
    checkForLegalDates(startDate, endDate);
    Room room = null;
    for (Room i : getAllRooms())
    {
      if (i.getRoomId().equals(roomId))
      {
        room = i;
      }

    }
    RoomBooking booking = new RoomBooking(startDate, endDate, room, guest);
    dataBaseAdapter.book(booking);
  }

  //todo delete??
  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  //todo delete??
  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }

  /**
   * Method setting the start and end date variables to the values passed as argument.
   *
   * @param startDate startDate
   * @param endDate   endDate
   * @throws IllegalArgumentException if any of the following are true:
   *                                  - Start date is before current date.
   *                                  - End date is the same as start date
   *                                  - End date is before start date.
   */

  public void checkForLegalDates(LocalDate startDate, LocalDate endDate)
  {
    if (startDate == null || endDate == null)
    {
      throw new NullPointerException(
          "Please enter a start date and an end date.");
    }

    else if (startDate.isBefore(LocalDate.now()))
    {
      throw new IllegalArgumentException(
          "Start date should not be before current date: " + LocalDate.now());
    }
    else if (endDate.isEqual(startDate))
    {
      throw new IllegalArgumentException(
          "End date cannot be the same date as start-date.");

    }
    else if (endDate.isBefore(startDate))
    {
      throw new IllegalArgumentException(
          "End date cannot be before start date.");
    }
  }

  @Override
  public void editGuest(int bookingID, String fName, String lName, String email, int phoneNr) throws SQLException {
    dataBaseAdapter.editGuest(bookingID, fName, lName, email, phoneNr);
  }

  @Override
  public ArrayList<Guest> getAllGuests() throws SQLException {
    return dataBaseAdapter.getAllGuests();
  }
}

