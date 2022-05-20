package mediator;

import model.Guest;

import java.time.LocalDate;

/**
 * Class used to store all instance variables of a RoomBooking object, in order to be able to send between client and server.
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class RoomBookingTransferObject
{
  private LocalDate startDate;
  private LocalDate endDate;
  private String roomID;
  private Guest guest;
  private int bookingID;
  private String bookingState;

  /**
   * 6 argument constructor used to set all instance variables.
   *
   * @param startDate    Start date
   * @param endDate      End date
   * @param roomID       Room ID
   * @param guest        Guest
   * @param bookingID    Booking ID
   * @param bookingState Booking state as a String
   */
  public RoomBookingTransferObject(LocalDate startDate, LocalDate endDate,
      String roomID, Guest guest, int bookingID, String bookingState)
  {
    this.startDate = startDate;
    this.endDate = endDate;
    this.roomID = roomID;
    this.guest = guest;
    this.bookingID = bookingID;
    this.bookingState = bookingState;
  }

  /**
   * Method returning the start date
   *
   * @return startDate
   */
  public LocalDate getStartDate()
  {
    return startDate;
  }

  /**
   * Method returning the end date
   *
   * @return endDate
   */
  public LocalDate getEndDate()
  {
    return endDate;
  }

  /**
   * Method returning the Room ID
   *
   * @return Room ID
   */
  public String getRoomID()
  {
    return roomID;
  }

  /**
   * Method returning the Guest object associated with the booking
   *
   * @return Guest
   */
  public Guest getGuest()
  {
    return guest;
  }

  /**
   * Method returning the Booking ID
   *
   * @return Booking ID
   */
  public int getBookingID()
  {
    return bookingID;
  }

  /**
   * Method returning the Booking State
   *
   * @return Booking state
   */
  public String getBookingState()
  {
    return bookingState;
  }

  /**
   * ToString method
   *
   * @return All instance variables and their values as a String.
   */
  @Override public String toString()
  {
    return "RoomBookingTransferObject{" + "startDate=" + startDate
        + ", endDate=" + endDate + ", roomID='" + roomID + '\'' + ", guest="
        + guest + ", bookingID=" + bookingID + ", bookingState='" + bookingState
        + '\'' + '}';
  }
}
