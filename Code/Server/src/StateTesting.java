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
      //model.addRoom("1.01", RoomType.SINGLE, 1);
      // model.book("1.01", LocalDate.now().plusDays(9), LocalDate.now().plusDays(15), new Guest("Christian", "Pedersen", "315269@via.dk", 52198082));

      System.out.println(model.getAllBookings().getBooking(0));

    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }
}
