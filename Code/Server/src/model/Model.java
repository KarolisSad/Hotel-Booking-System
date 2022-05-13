package model;

import mediator.GuestTransfer;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  ArrayList<Room> availableRooms(LocalDate startDate, LocalDate endDate) throws SQLException;
  void book(String roomId, LocalDate startDate, LocalDate endDate, Guest guest) throws SQLException;
  void addRoom(String roomId, RoomType type, int nrBeds) throws SQLException;
  void removeRoom(String roomId) throws SQLException;
  void editRoomInfo(String roomId, RoomType type, int nrBeds) throws SQLException;
  ArrayList<Room> getAllRooms() throws SQLException;

  // Christian added:
  RoomBookingList getAllBookings(String type) throws SQLException;
  void processBooking(int id) throws SQLException;
  void cancelBooking(int id) throws SQLException;

  //Nina:
  void editGuest(int bookingID, String getfName, String getlName, String email, int phoneNr) throws SQLException;
}
