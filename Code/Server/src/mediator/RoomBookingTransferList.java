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

  public void convertAndAdd(RoomBookingList bookingList)
  {
    for (int i = 0; i < bookingList.bookedRoomListSize(); i++)
    {
      RoomBooking book = bookingList.getBooking(i);
      bookingsTransfer.add(new RoomBookingTransfer(book.getStartDate(), book.getEndDate(), book.getRoom().getRoomId(), book.getGuest().getPhoneNr(),
          book.getBookingID(), book.getState()));
    }
  }


  public ArrayList<RoomBookingTransfer> getBookingsTransferList()
  {
    return bookingsTransfer;
  }
}
