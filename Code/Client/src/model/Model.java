package model;

import mediator.RoomBookingTransfer;
import mediator.RoomTransfer;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.time.LocalDate;

public interface Model extends UnnamedPropertyChangeSubject
{
  RoomTransfer availableRooms(LocalDate startDate, LocalDate endDate);
  RoomTransfer book(String roomId, LocalDate startDate, LocalDate endDate, Guest guest);
  RoomTransfer addRoom(String roomId, RoomType type, int nrBeds);
  RoomTransfer removeRoom(String roomId);
  RoomTransfer editRoomInfo(String roomId, RoomType type, int nrBeds);


  // TODO CHR
  RoomBookingTransfer getAllBookings();
  RoomBookingTransfer getBookedBookings();
  RoomBookingTransfer getInProgressBookings();
  RoomBookingTransfer getCancelledBookings();
  RoomBookingTransfer processBooking(int bookingNumber);

  RoomTransfer getAllRooms();
}
