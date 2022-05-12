package mediator;

import model.Guest;

import java.time.LocalDate;

public class RoomBookingTransferObject
{
  private LocalDate startDate;
  private LocalDate endDate;
  private String roomID;
  private Guest guest;
  private int bookingID;
  private String bookingState;

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

  public LocalDate getStartDate()
  {
    return startDate;
  }

  public LocalDate getEndDate()
  {
    return endDate;
  }

  public String getRoomID()
  {
    return roomID;
  }

  public Guest getGuest()
  {
    return guest;
  }

  public int getBookingID()
  {
    return bookingID;
  }

  public String getBookingState()
  {
    return bookingState;
  }

  @Override public String toString()
  {
    return "RoomBookingTransferObject{" + "startDate=" + startDate
        + ", endDate=" + endDate + ", roomID='" + roomID + '\'' + ", guest="
        + guest + ", bookingID=" + bookingID + ", bookingState='" + bookingState
        + '\'' + '}';
  }

}
