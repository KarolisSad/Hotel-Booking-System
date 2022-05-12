package mediator;

import model.RoomBooking;
import model.RoomBookingList;

import java.util.ArrayList;

public class RoomBookingTransferList
{
  private ArrayList<RoomBookingTransfer> bookingsTransfer;

  public RoomBookingTransferList()
  {
    bookingsTransfer = new ArrayList<>();
  }

  public void add(RoomBookingTransfer bookingTransfer)
  {
    bookingsTransfer.add(bookingTransfer);
  }

  public void get(int index)
  {
    bookingsTransfer.get(index);
  }

  public ArrayList<RoomBookingTransfer> getBookingsTransferList()
  {
    return bookingsTransfer;
  }

  @Override public String toString()
  {
    return "RoomBookingTransferList{" + "bookingsTransfer=" + bookingsTransfer
        + '}';
  }
}
