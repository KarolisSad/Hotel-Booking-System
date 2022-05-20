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

  void logOutForGuest();
  GuestTransfer login(String username, String password) throws InterruptedException;
  GuestTransfer register(String fName, String lName, String email, int phoneNumber, String username, String password);
  RoomBookingTransfer getBookingsWhenLoggedIn();

  RoomBookingTransfer bookARoomWhenLoggedIn(String roomName, LocalDate startDate, LocalDate endDate);

    GuestTransfer editGuestWithUsername(String editGuestWithUsername, String username, String getfName, String getlName, String email, int parseInt);
}
