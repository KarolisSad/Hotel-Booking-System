package model;

import mediator.RoomBookingTransfer;
import mediator.RoomTransfer;
import network.MyDataBase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HotelDataBase implements HotelPersistence {

    public HotelDataBase() {
    }

    @Override
    public void addRoom(String ID, RoomType type, int numberOfBeds) throws SQLException {
        MyDataBase dataBase = MyDataBase.getInstance();
        dataBase.addOneRoom(ID, type, numberOfBeds);
    }

    @Override
    public void remove(String ID) throws SQLException {
        MyDataBase dataBase = MyDataBase.getInstance();
        dataBase.removeOneRoom(ID);
    }

    @Override
    public ArrayList<Room> getAllRooms() throws SQLException {
        MyDataBase dataBase = MyDataBase.getInstance();
        return dataBase.getAllRooms();
    }

    @Override
    public ArrayList<Room> availableRooms(LocalDate startDate, LocalDate endDate) throws SQLException {
        MyDataBase dataBase = MyDataBase.getInstance();
        return dataBase.availableRooms(startDate,endDate);
    }

    @Override
    public void editRoomInfo(String roomID, RoomType type, int nrBeds) throws SQLException {
        MyDataBase dataBase = MyDataBase.getInstance();
        dataBase.editRoomInfo(roomID,type,nrBeds);
    }

    @Override
    public void editGuest(int bookingID, String fName, String lName, String email, int phoneNr) throws SQLException {
        MyDataBase dataBase = MyDataBase.getInstance();
        dataBase.editGuest(bookingID, fName, lName, email,  phoneNr);
    }

    /**
     * Method used for getting all bookings of a specific type from the database.
     * @param type the type of rooms to get
     * @return An ArrayList of bookings of the type corresponding to the String passed as argument.
     *
     */
    @Override public ArrayList<RoomBooking> getAllBookings(String type)
        throws SQLException
    {
        MyDataBase dataBase = MyDataBase.getInstance();
        return dataBase.getAllRoomBookings(type);
    }

    /**
     * Method used for updating the state of a room in the database.
     * @param booking the booking to be updated
     *
     */
    @Override public void processBooking(RoomBooking booking)
        throws SQLException
    {
        MyDataBase dataBase = MyDataBase.getInstance();
        dataBase.processBooking(booking);
    }

    //TODO What should this do??
    @Override public void cancelBooking(RoomBooking roomBooking)
        throws SQLException
    {
        MyDataBase dataBase = MyDataBase.getInstance();
        dataBase.cancelBooking(roomBooking);
    }

    @Override public RoomBookingTransfer getBookingWithGuest(int bookingNr, int phoneNr)
        throws SQLException
    {
        MyDataBase dataBase = MyDataBase.getInstance();
        return dataBase.getRoomWithGuest(bookingNr, phoneNr);

    @Override public void editBooking(int bookingId, LocalDate startDate,
        LocalDate endDate, String roomId) throws SQLException
    {
        MyDataBase dataBase = MyDataBase.getInstance();
        dataBase.editBooking(bookingId, startDate, endDate, roomId);
    }

    @Override public void removeBooking(int bookingId) throws SQLException
    {
        MyDataBase dataBase = MyDataBase.getInstance();
        dataBase.removeBooking(bookingId);

    }

    /**
     * Method used for adding a booking to the database.
     * @param roomBooking The booking to add.
     *
     */
    @Override
    public void book(RoomBooking roomBooking) throws SQLException {
        MyDataBase dataBase = MyDataBase.getInstance();
        dataBase.book(roomBooking);
    }




}
