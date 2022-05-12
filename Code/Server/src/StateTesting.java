import mediator.RoomBookingTransfer;
import mediator.RoomBookingTransferList;
import model.*;

import java.sql.SQLException;
import java.time.LocalDate;

public class StateTesting
{
  public static void main(String[] args)
  {
    try
    {
      Model model = new ModelManager();

      System.out.println(model.getAllBookings().getBooking(0));
      RoomBookingTransferList test = new RoomBookingTransferList();
      test.convertAndAdd(model.getAllBookings());
      System.out.println(test.getBookingsTransferList());

    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }
}
