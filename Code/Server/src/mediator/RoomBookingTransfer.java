package mediator;

import model.Guest;
import model.Room;
import model.RoomBooking;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class used for transferring objects related to RoomBookings between the server and the client
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class RoomBookingTransfer
{
  private String type;

  private int bookingNr;
  private LocalDate startDate;
  private LocalDate endDate;
  private int guestID;
  private String roomID;
  private String stateString;
  private String username;

  private ArrayList<RoomBookingTransferObject> roomBookings;

  private String message;
  private RoomBooking booking;
  private Guest guest;

  private Room room;

  /**
   * 2 argument constructor used for transferring an ArrayList containing RoomBookingTransferObjects
   *
   * @param type         The type of transfer
   * @param roomBookings List of bookings
   */
  public RoomBookingTransfer(String type,
      ArrayList<RoomBookingTransferObject> roomBookings)
  {
    this.type = type;
    this.roomBookings = roomBookings;
  }

  /**
   * 2 argument constructor used to transfer exception messages.
   *
   * @param type    The type of transfer
   * @param message The exception message.
   */
  public RoomBookingTransfer(String type, String message)
  {
    this.type = type;
    this.message = message;
  }

  /**
   * 3 argument constructor used to request RoomBookingState updates.
   *
   * @param type      The type of transfer
   * @param bookingNr the bookingNr of the booking.
   * @param guestID guest identification.
   */
  public RoomBookingTransfer(String type, int bookingNr, int guestID)
  {
    this.type = type;
    this.bookingNr = bookingNr;
    this.guestID = guestID;
  }

  /**
   * 5 argument constructor used to transfer booking details to the client.
   *
   * @param type the type of transfer
   * @param guest The Guest object
   * @param startDate the start date of the booking.
   * @param endDate The end date of the booking.
   * @param room The room of the booking.
   */
  public RoomBookingTransfer(String type, Guest guest, LocalDate startDate,
      LocalDate endDate, Room room)
  {
    this.type = type;
    this.guest = guest;
    this.startDate = startDate;
    this.endDate = endDate;
    this.room = room;
  }

  /**
   * Getter for the type of transfer
   *
   * @return type
   */
  public String getType()
  {
    return type;
  }

  /**
   * Getter for the booking nr
   *
   * @return booking nr
   */
  public int getBookingNr()
  {
    return bookingNr;
  }

  /**
   * Getter for the startDate
   *
   * @return startDate
   */
  public LocalDate getStartDate()
  {
    return startDate;
  }

  /**
   * Getter for the endDate
   *
   * @return endDate
   */
  public LocalDate getEndDate()
  {
    return endDate;
  }

  /**
   * Getter for the Guest ID
   *
   * @return guest id
   */
  public int getGuestID()
  {
    return guestID;
  }

  /**
   * Getter for the Room ID
   *
   * @return room id
   */
  public String getRoomID()
  {
    return roomID;
  }

  /**
   * Getter for the Booking state
   *
   * @return Booking state as a String
   */
  public String getStateString()
  {
    return stateString;
  }

  /**
   * Getter for the ArrayList of bookings
   *
   * @return roomBookings ArrayList of RoomBookingTransferObjects
   */
  public ArrayList<RoomBookingTransferObject> getRoomBookings()
  {
    return roomBookings;
  }

  /**
   * Getter for the message
   *
   * @return message
   */
  public String getMessage()
  {
    return message;
  }

  /**
   * Getter for booking
   *
   * @return booking
   */
  public RoomBooking getBooking()
  {
    return booking;
  }

  /**
   * Getter for Guest
   *
   * @return Guest
   */
  public Guest getGuest()
  {
    return guest;
  }

  /**
   * Getter for the room
   * @return Room
   */
  public Room getRoom()
  {
    return room;
  }

  public String getUsername() {
    return username;
  }

  /**
   * Method for returning the object as a string
   *
   * @return All instance variables and their values as a String.
   */
  @Override public String toString()
  {
    return "RoomBookingTransfer{" + "type='" + type + '\'' + ", bookingNr="
        + bookingNr + ", startDate=" + startDate + ", endDate=" + endDate
        + ", guestID=" + guestID + ", roomID='" + roomID + '\''
        + ", stateString='" + stateString + '\'' + ", roomBookings="
        + roomBookings + ", message='" + message + '\'' + ", booking=" + booking
        + ", guest=" + guest + ", room=" + room + '}';
  }
}
