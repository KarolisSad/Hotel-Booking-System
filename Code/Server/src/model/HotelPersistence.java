package model;

import mediator.GuestTransfer;
import mediator.RoomBookingTransfer;
import mediator.RoomTransfer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface HotelPersistence {
    void addRoom(Room room) throws SQLException;
    void remove(String ID) throws SQLException;
    ArrayList<Room> getAllRooms() throws SQLException;
    void book(RoomBooking roomBooking) throws SQLException;
    ArrayList<Room> availableRooms(LocalDate startDate, LocalDate endDate) throws SQLException;

    void editGuest(int bookingID, String fName, String lName, String email, int phoneNr) throws SQLException;
    ArrayList<RoomBooking> getAllBookings(String type) throws SQLException;
    void processBooking(RoomBooking booking) throws SQLException;
    void cancelBooking(RoomBooking roomBooking) throws SQLException;
    void editRoomInfo(String roomID, RoomType type, int nrBeds, int price) throws SQLException;
    void editBooking (int bookingId, LocalDate startDate, LocalDate endDate, String roomId) throws SQLException;
    Room getRoom(String roomId) throws SQLException;
    ArrayList<Guest> getAllGuests() throws SQLException;
   // RoomBookingTransfer getBookingWithGuest(int bookingNr, int phoneNr) throws SQLException;


    void register(Guest guest) throws SQLException;
    void login(String username, String password) throws SQLException;

    ArrayList<RoomBooking> getBookingsWhenLoggedIn(String username) throws SQLException;

    void bookARoomWhenLoggedIn(RoomBooking roomBooking) throws SQLException;

    void clearDatabase() throws SQLException;

    void editGuestWithUsername(String username, String getfName, String getlName, String email, int phoneNr) throws SQLException;

    GuestTransfer getGuestByUsername(String username) throws SQLException;

    ArrayList<Room> availableConferenceRooms(LocalDate startDate, LocalDate endDate) throws SQLException;
}
