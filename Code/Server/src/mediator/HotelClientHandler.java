package mediator;

import com.google.gson.Gson;
import model.Model;
import model.Room;
import model.RoomBooking;
import model.RoomBookingList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Receives message from client and sends back requested infromation.
 * 2022-05-08
 *
 * @author Group5
 */

public class HotelClientHandler implements Runnable
{

  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson json;
  private Model model;
  private String message;

  public HotelClientHandler(Socket socket, Model model) throws IOException
  {
    this.socket = socket;
    this.model = model;
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(new PrintWriter(socket.getOutputStream()), true);
    json = new Gson();
    message = null;
  }

  /**
   * Receives messages from client in Json string then converts it to a RoomTransfer Object,
   * checks value of type and chooses right method to call on server side.
   * Takes returned value and converts it to Json and sends back to the client.
   */
  @Override public void run()
  {
    while (true)
    {
      String successMessage = json.toJson(new RoomTransfer("Success"));

      try
      {
        message = in.readLine();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      String type = (String) json.fromJson(message, Map.class).get("type");

      switch (type)
      {
        case "addOneRoom":
          RoomTransfer room = json.fromJson(message, RoomTransfer.class);
          try
          {
            model.addRoom(room.getRoomId(), room.getRoomType(),
                room.getNrBeds());
            out.println(successMessage);
          }
          catch (Exception e)
          {
            RoomTransfer roomTransfer = new RoomTransfer("error",
                e.getMessage());
            String jsonString = json.toJson(roomTransfer);
            out.println(jsonString);
          }
          break;

        case "removeOneRoom":
          room = json.fromJson(message, RoomTransfer.class);
          try
          {
            model.removeRoom(room.getRoomId());
            out.println(successMessage);
          }
          catch (Exception e)
          {
            out.println(json.toJson(new RoomTransfer("error", e.getMessage())));
          }
          break;

        case "getAllRooms":
          ArrayList<Room> rooms = null;
          try
          {
            rooms = model.getAllRooms();
          }
          catch (Exception e)
          {
            out.println(json.toJson(new RoomTransfer("error", e.getMessage())));
          }
          room = new RoomTransfer("getALlRooms", rooms);
          String jsonString = json.toJson(room);
          out.println(jsonString);
          break;

        case "availableRooms":
          room = json.fromJson(message, RoomTransfer.class);
          try
          {
            RoomTransfer transfer = new RoomTransfer("availableRooms",
                model.availableRooms(room.getStartDate(), room.getEndDate()));
            jsonString = json.toJson(transfer);
            out.println(jsonString);
          }
          catch (Exception e)
          {
            out.println(json.toJson(new RoomTransfer("error", e.getMessage())));
          }
          break;

        case "bookOneRoom":
          room = json.fromJson(message, RoomTransfer.class);
          try
          {
            model.book(room.getRoomId(), room.getStartDate(), room.getEndDate(),
                room.getGuest());
            out.println(successMessage);
          }
          catch (Exception e)
          {
            out.println(json.toJson(new RoomTransfer("error", e.getMessage())));
          }
          break;

        case "edit":
          room = json.fromJson(message, RoomTransfer.class);
          try
          {
            System.out.println(room.getRoomType());
            model.editRoomInfo(room.getRoomId(), room.getRoomType(),
                room.getNrBeds());

            out.println(successMessage);
          }
          catch (Exception e)
          {
            out.println(json.toJson(new RoomTransfer("error", e.getMessage())));
          }
          break;

        case "AllBookings":
        {
          RoomBookingTransferList allBookings = new RoomBookingTransferList();
          try
          {
            allBookings.convertAndAdd(model.getAllBookings());
          }
          catch (Exception e)
          {
            out.println(json.toJson(new RoomTransfer("error", e.getMessage())));
          }

          room = new RoomTransfer("AllBookings", allBookings);

          jsonString = json.toJson(room);
          out.println(jsonString);
          break;


        }

      }

    }
  }
}
