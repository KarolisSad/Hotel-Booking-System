package mediator;

import java.time.LocalDate;

public class RoomBookingTransferObject
{
  private LocalDate startDate;
  private LocalDate endDate;
  private String roomID;
  private int guestID;
  private int bookingID;
  private String bookingState;

  public RoomBookingTransferObject(LocalDate startDate, LocalDate endDate,
      String roomID, int guestID, int bookingID, String bookingState)
  {
    this.startDate = startDate;
    this.endDate = endDate;
    this.roomID = roomID;
    this.guestID = guestID;
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

  public int getGuestID()
  {
    return guestID;
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
    return "RoomBookingTransferObject{" + "startDate=" + startDate + ", endDate="
        + endDate + ", roomID='" + roomID + '\'' + ", guestID=" + guestID
        + ", bookingID=" + bookingID + ", bookingState='" + bookingState + '\''
        + '}';
  }
}
