package model;

import mediator.RoomTransfer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model
{
  RoomTransfer availableRooms(LocalDate startDate, LocalDate endDate) throws SQLException;
  void book(String roomId, LocalDate  startDate, LocalDate  endDate, Guest guest) throws SQLException;
  boolean isBookingAllowed(String roomId, LocalDate  startDate, LocalDate  endDate);
  void addRoom(String roomID, String type, int numberOfBeds) throws SQLException;
  void removeRoom(String ID) throws SQLException;
  ArrayList<Room> getAllRooms() throws SQLException;
  void editRoomInfo(String roomID, String type, int nrBeds) throws SQLException;
}
