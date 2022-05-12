import model.Model;
import model.ModelManager;

public class ClientTestingChr
{
  public static void main(String[] args)
  {
    Model model = new ModelManager();
    System.out.println("LIST CLIENT: " + model.getAllBookings().getRoomBookings()
    );

    System.out.println("OBJECT: " + model.getAllBookings());
  }
}
