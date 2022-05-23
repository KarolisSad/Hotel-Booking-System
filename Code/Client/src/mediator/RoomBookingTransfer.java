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
   * 1 argument constructor initializing the type variable.
   *
   * @param type the type to be set.
   */
  public RoomBookingTransfer(String type)
  {
    this.type = type;
  }

  /**
   * 2 argument constructor used to request RoomBookingState updates.
   *
   * @param type      The type of transfer
   * @param bookingNr the bookingNr of the booking to be changed.
   */
  public RoomBookingTransfer(String type, int bookingNr)
  {
    this.type = type;
    this.bookingNr = bookingNr;
  }

  /**
   * 5 argument constructor used to transfer booking details to the server.
   * Used for booking a room when a guest is logged in the system.
   * @param type The type of transfer
   * @param roomID the room identification of the booking.
   * @param startDate the start date of the booking.
   * @param endDate the end date of the booking.
   * @param username
   */
  public RoomBookingTransfer(String type,String roomID ,LocalDate startDate, LocalDate endDate, String username)
  {
    this.type = type;
    this.roomID = roomID;
    this.startDate = startDate;
    this.endDate = endDate;
    this.username = username;
  }

  /**
   * 2 argument constructor used to request all bookings
   * made by the guest, when one's logged in.
   *
   * @param type The type of transfer
   * @param username
   */
  public RoomBookingTransfer(String type, String username)
  {
    this.type = type;
    this.username = username;
  }

  /**
   * 5 argument constructor used to transfer booking details to the server.
   * @param type The type of transfer
   * @param bookingId the booking identification of the booking to be changed.
   * @param startDate the start date of the booking.
   * @param endDate the end date of the booking.
   * @param roomid the room identification.
   */
  public RoomBookingTransfer(String type, int bookingId,
      LocalDate startDate, LocalDate endDate, String roomid)
  {
    this.type = type;
    this.bookingNr = bookingId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.roomID = roomid;
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
   * Getter for room
   *
   * @return Room object
   */
  public Room getRoom()
  {
    return room;
  }

  /**
   * Method for returning the object as a string
   *
   * @return All instance variables and their values as a String.
   */
  @Override
  public String toString() {
    return "RoomBookingTransfer{" +
            "type='" + type + '\'' +
            ", bookingNr=" + bookingNr +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", guestID=" + guestID +
            ", roomID='" + roomID + '\'' +
            ", stateString='" + stateString + '\'' +
            ", username='" + username + '\'' +
            ", roomBookings=" + roomBookings +
            ", message='" + message + '\'' +
            ", booking=" + booking +
            ", guest=" + guest +
            ", room=" + room +
            '}';
  }
}
