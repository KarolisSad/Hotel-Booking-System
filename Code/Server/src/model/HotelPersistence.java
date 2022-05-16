package model;

import mediator.RoomBookingTransfer;
import mediator.RoomTransfer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface HotelPersistence {
    void addRoom(String ID, RoomType type, int numberOfBeds) throws SQLException;
    void remove(String ID) throws SQLException;
    ArrayList<Room> getAllRooms() throws SQLException;
    void book(RoomBooking roomBooking) throws SQLException;
    ArrayList<Room> availableRooms(LocalDate startDate, LocalDate endDate) throws SQLException;

    void editGuest(int bookingID, String fName, String lName, String email, int phoneNr) throws SQLException;
   
    ArrayList<RoomBooking> getAllBookings(String type) throws SQLException;
    void processBooking(RoomBooking booking) throws SQLException;
    void cancelBooking(RoomBooking roomBooking) throws SQLException;
    RoomBookingTransfer getBookingWithGuest(int bookingNr, int phoneNr) throws SQLException;
    void editRoomInfo(String roomID, RoomType type, int nrBeds) throws SQLException;
    void editBooking (int bookingId, LocalDate startDate, LocalDate endDate, String roomId) throws SQLException;
    void removeBooking(int bookingId) throws SQLException;
}
