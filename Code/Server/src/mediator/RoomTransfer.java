package mediator;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Purpose of this object is to store different values when sending to a server
 * <p>
 * 2020-05-08
 *
 * @author Group5
 */

public class RoomTransfer {

    private String roomId;
    private RoomType roomType;
    private int nrBeds;
    private String type;
    private ArrayList<Room> roomList;
    private LocalDate startDate;
    private LocalDate endDate;
    private Guest guest;
    private String message;

    private RoomBookingList bookingList;

    public RoomTransfer(String type, String roomId, RoomType roomType, int nrBeds, ArrayList<Room> roomList) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.nrBeds = nrBeds;
        this.type = type;
        this.roomList = roomList;
    }

    //Book
    public RoomTransfer(String type, String roomId, LocalDate startDate, LocalDate endDate, Guest guest) {
        this.type = type;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guest = guest;
    }

    // TODO CHR ADDED
    // GET ALL BOOKINGS
    public RoomTransfer(String type, RoomBookingList bookingList)
    {
       this.type = type;
        this.bookingList = bookingList;
    }

    public RoomTransfer(String type, LocalDate startDate, LocalDate endDate) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public RoomTransfer(String type, String message) {
        this.type = type;
        this.message = message;
    }

    RoomTransfer(String type, String roomId, RoomType roomType, int nrBeds) {
        this.roomType =roomType;
        this.type = type;
        this.roomId = roomId;
        this.nrBeds = nrBeds;
    }

    public RoomTransfer(String type) {
        this.type = type;
    }

    public RoomTransfer(String type, ArrayList<Room> rooms) {
        this.roomList = rooms;
        this.type = type;
    }

    public RoomTransfer(String type, ArrayList<Room> roomList, String message) {
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

    public RoomType getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getType() {
        return type;
    }

    public int getNrBeds() {
        return nrBeds;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public String getMessage() {
        return message;
    }
}
