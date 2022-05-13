import mediator.HotelServer;
import model.Model;
import model.ModelManager;

import java.sql.SQLException;

public class StartServer {
    public static void main(String[] args)
    {
        Model model = new ModelManager();
        HotelServer hotelServer = new HotelServer(model);
        Thread serverThread = new Thread(hotelServer);
        serverThread.start();
    }
}