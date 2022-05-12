package mediator;

import model.Guest;
import model.RoomBooking;

import java.time.LocalDate;
import java.util.ArrayList;

public class RoomBookingTransfer
{
  private String type;

  private int bookingNr;
  private LocalDate startDate;
  private LocalDate endDate;
  private int guestID;
  private String roomID;
  private String stateString;

  private ArrayList<RoomBookingTransferObject> roomBookings;

  private String message;


  // Might not be used
  private RoomBooking booking;
  private Guest guest;


  //// CONSTRUCTORS

  public RoomBookingTransfer(String type, int bookingNr, LocalDate startDate,
      LocalDate endDate, int guestID, String roomID, String stateString)
  {
    this.type = type;
    this.bookingNr = bookingNr;
    this.startDate = startDate;
    this.endDate = endDate;
    this.guestID = guestID;
    this.roomID = roomID;
    this.stateString = stateString;
  }


  public RoomBookingTransfer(String type, ArrayList<RoomBookingTransferObject> roomBookings)
  {
    this.type = type;
    this.roomBookings = roomBookings;
  }

  // For exceptions
  public RoomBookingTransfer(String type, String message)
  {
    this.type = type;
    this.message = message;
  }

  public RoomBookingTransfer(String type)
  {
    this.type = type;
  }

  // For check in / out
  public RoomBookingTransfer(String type, int bookingNr)
  {
    this.type = type;
    this.bookingNr = bookingNr;
  }

  public String getType()
  {
    return type;
  }

  public int getBookingNr()
  {
    return bookingNr;
  }

  public LocalDate getStartDate()
  {
    return startDate;
  }

  public LocalDate getEndDate()
  {
    return endDate;
  }

  public int getGuestID()
  {
    return guestID;
  }

  public String getRoomID()
  {
    return roomID;
  }

  public String getStateString()
  {
    return stateString;
  }

  public ArrayList<RoomBookingTransferObject> getRoomBookings()
  {
    return roomBookings;
  }

  public String getMessage()
  {
    return message;
  }

  public RoomBooking getBooking()
  {
    return booking;
  }

  public Guest getGuest()
  {
    return guest;
  }

  @Override public String toString()
  {
    return "RoomBookingTransfer{" + "type='" + type + '\'' + ", bookingNr="
        + bookingNr + ", startDate=" + startDate + ", endDate=" + endDate
        + ", guestID=" + guestID + ", roomID='" + roomID + '\''
        + ", stateString='" + stateString + '\'' + ", roomBookings="
        + roomBookings + ", message='" + message + '\'' + ", booking=" + booking
        + ", guest=" + guest + '}';
  }
}
