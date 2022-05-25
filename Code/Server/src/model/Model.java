package model;

import mediator.GuestTransfer;
import mediator.RoomBookingTransfer;
import mediator.RoomTransfer;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  ArrayList<Room> availableRooms(LocalDate startDate, LocalDate endDate) throws SQLException;
  void book(String roomId, LocalDate startDate, LocalDate endDate, Guest guest) throws SQLException;
  void addRoom(String roomId, RoomType type, int nrBeds, int price) throws SQLException;
  void removeRoom(String roomId) throws SQLException;
  void editRoomInfo(String roomId, RoomType type, int nrBeds, int price) throws SQLException;
  ArrayList<Room> getAllRooms() throws SQLException;

  RoomBookingList getAllBookings(String type) throws SQLException;
  void processBooking(int id) throws SQLException;
  void cancelBooking(int id) throws SQLException;
  void editBooking(int bookingId, LocalDate startDate, LocalDate endDate, String roomId) throws SQLException;

  //Nina:
  void editGuest(int bookingID, String getfName, String getlName, String email, int phoneNr) throws SQLException;

  ArrayList<Guest> getAllGuests() throws SQLException;

  Room getRoom(String roomId) throws SQLException;
  //RoomBookingTransfer getBookingWithGuest(int bookingNr, int phoneNr) throws SQLException;

    void register(String fName, String lName, String email, int phoneNr, String username, String password) throws SQLException;
  void login(String username, String password) throws SQLException;

  RoomBookingList getBookingsWhenLoggedIn(String username) throws SQLException;

    void bookARoomWhenLoggedIn(String roomID, LocalDate startDate, LocalDate endDate, String username) throws SQLException;

    void clearDatabase() throws SQLException;

    void editGuestWithUsername(String username, String getfName, String getlName, String email, int phoneNr) throws SQLException;

  GuestTransfer getGuestByUsername(String username) throws SQLException;

  ArrayList<Room> availableConferenceRooms(LocalDate startDate, LocalDate endDate) throws SQLException;
}
