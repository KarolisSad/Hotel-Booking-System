package model;

import mediator.RoomTransfer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface HotelPersistence {
    void addRoom(String ID, RoomType type, int numberOfBeds) throws SQLException;
    void remove(String ID) throws SQLException;
    ArrayList<Room> getAllRooms() throws SQLException;
    void book(String roomId, LocalDate startDate, LocalDate endDate, Guest guest) throws SQLException;
    ArrayList<Room> availableRooms(LocalDate startDate, LocalDate endDate) throws SQLException;
    void editRoomInfo(String roomID, RoomType type, int nrBeds) throws SQLException;
}
