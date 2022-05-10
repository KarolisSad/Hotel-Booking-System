package model;

import mediator.RoomTransfer;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  RoomTransfer availableRooms(LocalDate startDate, LocalDate endDate);
  RoomTransfer book(String roomId, LocalDate startDate, LocalDate endDate, Guest guest);
  RoomTransfer isBookingAllowed(String roomId, LocalDate startDate, LocalDate endDate);
  RoomTransfer addRoom(String roomId, String type, int nrBeds);
  RoomTransfer removeRoom(String roomId);
  RoomTransfer editRoomInfo(String roomId, String type, int nrBeds);
  RoomTransfer getAllRooms();
}
