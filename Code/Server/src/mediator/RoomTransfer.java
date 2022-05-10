package mediator;

import model.Guest;
import model.Room;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Purpose of this object is to store different values when sending to a server
 *
 * 2020-05-08
 * @author Group5
 */

public class RoomTransfer {

    private String roomId;
    private String roomType;
    private int nrBeds;
    private String type;
    private ArrayList<Room> roomList;
    private LocalDate  startDate;
    private LocalDate endDate;
    private Guest guest;
    private String message;

    public RoomTransfer(String type , String roomId, String roomType, int nrBeds, ArrayList<Room> roomList)
    {
        this.roomId = roomId;
        this.roomType = roomType;
        this.nrBeds = nrBeds;
        this.type = type;
        this.roomList = roomList;
    }

    //Book
    public RoomTransfer(String type,String roomId, LocalDate startDate, LocalDate endDate, Guest guest)
    {
        this.type = type;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guest = guest;
    }

    public RoomTransfer(String type, LocalDate startDate, LocalDate endDate)
    {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public RoomTransfer(String type, String message)
    {
        this.type = type;
        this.message = message;
    }

    public RoomTransfer(String type)
    {
        this.type = type;
    }

    public RoomTransfer(ArrayList<Room> rooms)
    {
        this.roomList =rooms;
    }

    public RoomTransfer(String type, ArrayList<Room> roomList, String message)
    {
        this.type = type;
        this.roomList = roomList;
        this.message = message;
    }

    public Guest getGuest() {
        return guest;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getType() {
        return roomType;
    }

    public int getNrBeds() {
        return nrBeds;
    }

    public String getTypeOfAction() {
        return type;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public String getMessage() {
        return message;
    }
}
