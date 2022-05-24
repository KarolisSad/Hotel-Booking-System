import model.Model;
import model.ModelManager;
import model.RoomType;

import java.sql.SQLException;

public class DELETEWHENDONE_JustTesting
{
  public static void main(String[] args) throws SQLException
  {
    Model model = new ModelManager();

    //model.addRoom("Room1", RoomType.SINGLE, 1, 100);
    //model.addRoom("Room2", RoomType.SINGLE, 1, 100);
    System.out.println(model.getAllRooms());
    //model.editRoomInfo("Room1", RoomType.SUITE, 4, 998);

    System.out.println(model.getRoom("Room1"));
  }
}
