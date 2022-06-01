import mediator.HotelServer;
import model.Model;
import model.ModelManager;

import java.util.Scanner;

public class SimpleConsoleView
{
  private Scanner input;
  private boolean running;


  public SimpleConsoleView()
  {
    input = new Scanner(System.in);
    running = true;
    run();
  }

  private void run()
  {
    while (running)
    {
      System.out.println("Press 1 to start server.");
      System.out.println("Press 2 to exit.");

      int choice = input.nextInt();
      input.nextLine();

      if (choice == 1)
      {
        System.out.println("Please enter your PostgreSQL password set during the installation: ");

        String password = input.nextLine();
        System.out.println("Password entered");
        try
        {
          Model model = new ModelManager(password);
          model.getAllRooms();
          HotelServer server = new HotelServer(model);
          Thread serverThread = new Thread(server);
          serverThread.start();

          int exit = input.nextInt();


          if (exit == 1)
          {
            running = false;
          }
        }
        catch (Exception e)
        {
          System.out.println("Something went wrong.");
          System.out.println("\nEither the database is not setup, or you entered the wrong password.");
          System.out.println("Please close this window and make sure that you have run SetupDatabase.bat.");
          System.out.println("If the database setup is already done, please close this window and run Server.bat again.");
          running = false;
        }
      }
      else if (choice == 2)
      {
        running = false;
      }
    }

  }
}