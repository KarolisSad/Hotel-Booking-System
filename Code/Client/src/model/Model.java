package model;

import mediator.RoomBookingTransferList;
import mediator.RoomTransfer;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  RoomTransfer availableRooms(LocalDate startDate, LocalDate endDate);
  RoomTransfer book(String roomId, LocalDate startDate, LocalDate endDate, Guest guest);
  RoomTransfer addRoom(String roomId, RoomType type, int nrBeds);
  RoomTransfer removeRoom(String roomId);
  RoomTransfer editRoomInfo(String roomId, RoomType type, int nrBeds);
  RoomTransfer getAllRooms();

  // TODO CHR
  RoomBookingTransferList getAllBookings();
}
