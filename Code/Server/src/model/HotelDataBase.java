package model;

import mediator.GuestTransfer;
import mediator.RoomBookingTransfer;
import mediator.RoomTransfer;
import network.MyDataBase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class connecting the server to the database.
 *
 * @author Group 5
 * @version 04/05/2022
 */
public class HotelDataBase implements HotelPersistence
{

  public HotelDataBase()
  {
  }

  /**
   * A method that requests an adding of a new room, from database.
   * @param ID
   * @param type
   * @param numberOfBeds
   * @throws SQLException
   */
  @Override public void addRoom(String ID, RoomType type, int numberOfBeds)
      throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.addOneRoom(ID, type, numberOfBeds);
  }

  /**
   * A method that requests an removing a specific room, from database.
   * @param ID
   * @throws SQLException
   */
  @Override public void remove(String ID) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.removeOneRoom(ID);
  }

  /**
   * A method that requests list of all rooms, from database.
   * @return ArrayList of rooms
   * @throws SQLException
   */
  @Override public ArrayList<Room> getAllRooms() throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getAllRooms();
  }

  /**
   * A method that requests list of all available rooms in a specific time interval, from database.
   * @param startDate
   * @param endDate
   * @return ArrayList of rooms
   * @throws SQLException
   */
  @Override public ArrayList<Room> availableRooms(LocalDate startDate,
      LocalDate endDate) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.availableRooms(startDate, endDate);
  }

  /**
   *  A method that requests editing specific room's information, from database.
   * @param roomID
   * @param type
   * @param nrBeds
   * @throws SQLException
   */
  @Override public void editRoomInfo(String roomID, RoomType type, int nrBeds)
      throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.editRoomInfo(roomID, type, nrBeds);
  }

  /**
   *  A method that requests editing specific guest's information, from database.
   * @param bookingID
   * @param fName
   * @param lName
   * @param email
   * @param phoneNr
   * @throws SQLException
   */
  @Override public void editGuest(int bookingID, String fName, String lName,
      String email, int phoneNr) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.editGuest(bookingID, fName, lName, email, phoneNr);
  }

  /**
   * Method used for getting all bookings of a specific type from the database.
   *
   * @param type the type of rooms to get
   * @return An ArrayList of bookings of the type corresponding to the String passed as argument.
   */
  @Override public ArrayList<RoomBooking> getAllBookings(String type)
      throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getAllRoomBookings(type);
  }

  /**
   * Method used for updating the state of a room in the database.
   *
   * @param booking the booking to be updated
   */
  @Override public void processBooking(RoomBooking booking) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.processBooking(booking);
  }

  /**
   *  A method that requests canceling a specific booking, from database.
   * @param roomBooking
   * @throws SQLException
   */
  @Override public void cancelBooking(RoomBooking roomBooking)
      throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.cancelBooking(roomBooking);
  }

  /**
   *  A method that requests editing specific booking, from database.
   * @param bookingId
   * @param startDate
   * @param endDate
   * @param roomId
   * @throws SQLException
   */
  @Override public void editBooking(int bookingId, LocalDate startDate,
      LocalDate endDate, String roomId) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.editBooking(bookingId, startDate, endDate, roomId);
  }

  /**
   *  A method that requests getting specific room information, from database.
   * @param roomId
   * @return
   * @throws SQLException
   */
  @Override public Room getRoom(String roomId) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getRoom(roomId);

  }

  /**
   *  A method that requests list of all guests, from database.
   * @return
   * @throws SQLException
   */
  @Override public ArrayList<Guest> getAllGuests() throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getAllGuests();
  }

  /*
  @Override public RoomBookingTransfer getBookingWithGuest(int bookingNr,
      int phoneNr) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getRoomWithGuest(bookingNr, phoneNr);
  }

   */

  /**
   * Method used for adding a booking to the database.
   *
   * @param roomBooking The booking to add.
   */
  @Override public void book(RoomBooking roomBooking) throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.book(roomBooking);
  }

  /**
   *  A method that requests registering a guest, from database.
   * @param guest
   * @throws SQLException
   */
  @Override
  public void register(Guest guest) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.register(guest);
  }

  /**
   *  A method that requests logging in a guest with passed username and password, from database.
   * @param username
   * @param password
   * @throws SQLException
   */
  @Override
  public void login(String username, String password) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.login(username,password);
  }

  /*

  @Override public ArrayList<RoomBooking> getAllBookings(String type)
          throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getAllRoomBookings(type);}

   */

  /**
   *  A method that requests getting a list of bookings, from database.
   *  Used only when a guest is logged in the system.
   * @param username
   * @return
   * @throws SQLException
   */
  @Override
  public ArrayList<RoomBooking> getBookingsWhenLoggedIn(String username) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getBookingsWhenLoggedIn(username);
  }

  /**
   * A method that requests making a booking in database.
   * Used only when a guest is logged in the system.
   * @param roomBooking
   * @throws SQLException
   */
  @Override
  public void bookARoomWhenLoggedIn(RoomBooking roomBooking) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.bookARoomWhenLoggedIn(roomBooking);
  }

  /**
   * A method used to clear all data in the database.
   * @throws SQLException
   */
  @Override public void clearDatabase() throws SQLException
  {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.clearDatabase();
  }

  /**
   * A method that requests from database editing guest's details.
   * Used only when a guest is logged in the system.
   * @param username
   * @param getfName
   * @param getlName
   * @param email
   * @param phoneNr
   * @throws SQLException
   */
  @Override
  public void editGuestWithUsername(String username, String getfName, String getlName, String email, int phoneNr) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    dataBase.editGuestWithUsername( username,  getfName,  getlName,  email,  phoneNr);
  }

  /**
   * A method that requests from database getting all guest details,
   * based on his username.
   *
   * @param username
   * @return
   * @throws SQLException
   */
  @Override
  public GuestTransfer getGuestByUsername(String username) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.getGuestByUsername(username);
  }

  @Override
  public ArrayList<Room> availableConferenceRooms(LocalDate startDate, LocalDate endDate) throws SQLException {
    MyDataBase dataBase = MyDataBase.getInstance();
    return dataBase.availableConferenceRooms(startDate,endDate);
  }

}
