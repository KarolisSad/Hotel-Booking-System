import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class creating a Console GUI for setting up the database.
 *
 * @author Group 5
 * @version 30/05/2022
 */
public class SimpleConsoleView
{
  private Scanner input;
  private boolean running;

  /**
   * Constructor initializing the Scanner, setting the running boolean to true and executing the run() method.
   */
  public SimpleConsoleView()
  {
    input = new Scanner(System.in);
    running = true;
    run();
  }

  /**
   * Method starting a simple Console GUI while the boolean running is true.
   */
  private void run()
  {
    while (running)
    {
      System.out.println("Database setup:");
      System.out.println("You only need to run this program once!");
      System.out.println("Press 1 to setup database.");
      System.out.println("Press 2 to exit setup.");

      int choice = input.nextInt();
      input.nextLine();

      if (choice == 1)
      {
        System.out.println("Starting setup....");
        System.out.println("Please enter your PostgreSQL password set during the installation: ");

        String password = input.nextLine();
        System.out.println("Password entered");
        try
        {
          MyDatabase database = MyDatabase.getInstance(password);
          database.createDatabase();
          System.out.println("Database is being created");
          try
          {
            Thread.sleep(15000);
          }
          catch (InterruptedException e)
          {
            throw new RuntimeException(e);
          }
          System.out.println("Database created! Inserting data");
          database.insertData();
          try
          {
            Thread.sleep(15000);
          }
          catch (InterruptedException e)
          {
            throw new RuntimeException(e);
          }
          System.out.println("Setup done, you can now exit this program and run Server.bat");
          System.out.println("Press 1 to exit");

          int exit = input.nextInt();
          if (exit == 1)
          {
            running = false;
          }
        }
        catch (Exception e)
        {
          System.out.println("Something went wrong.");

          if (e.getMessage().contains("already exists"))
          {
            System.out.println("Database already created.");
          }
          else
          {
            e.printStackTrace();
            System.out.println("\nEither PostgreSQL is not installed, or you entered the wrong password.");
            System.out.println("Please try again");
          }
          System.out.println("\n\n\n");
        }
      }
      else if (choice == 2)
      {
        running = false;
      }
    }

  }
}