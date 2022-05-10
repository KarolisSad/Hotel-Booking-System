package model;

import mediator.RoomTransfer;
import network.MyDataBase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HotelDataBase implements HotelPersistence {

    public HotelDataBase() {
    }

    @Override
    public void addRoom(String ID, String type, int numberOfBeds) throws SQLException {
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
    public RoomTransfer availableRooms(LocalDate startDate, LocalDate endDate) throws SQLException {
        MyDataBase dataBase = MyDataBase.getInstance();
        return dataBase.availableRooms(startDate,endDate);
    }

    @Override
    public void editRoomInfo(String roomID, String type, int nrBeds) throws SQLException {
        MyDataBase dataBase = MyDataBase.getInstance();
        dataBase.editRoomInfo(roomID,type,nrBeds);
    }

    @Override
    public void book(String roomId, LocalDate startDate, LocalDate endDate, Guest guest) throws SQLException {
        MyDataBase dataBase = MyDataBase.getInstance();
        dataBase.book(roomId,startDate,endDate,guest);

    }


}
