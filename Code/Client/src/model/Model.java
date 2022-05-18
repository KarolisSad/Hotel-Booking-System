package model;

import mediator.GuestTransfer;
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
  RoomTransfer getAllRooms();
  RoomBookingTransfer editBooking(int bookingId, LocalDate startDate, LocalDate endDate, String roomid);
  RoomBookingTransfer removeBooking(int bookingId);
  GuestTransfer editGuest(String type, int bookingID, String fName, String lName, String email, int phoneNr);
  RoomBookingTransfer getAllBookings();
  RoomBookingTransfer getBookedBookings();
  RoomBookingTransfer getInProgressBookings();
  RoomBookingTransfer getCancelledBookings();
  RoomBookingTransfer processBooking(int bookingNumber);
  RoomBookingTransfer cancelBooking(int bookingNumber);
  GuestTransfer getAllGuests();
  RoomTransfer getRoom(String roomId);
  RoomBookingTransfer getBookingWithGuest(int bookingNumber, int phoneNumber);


}
