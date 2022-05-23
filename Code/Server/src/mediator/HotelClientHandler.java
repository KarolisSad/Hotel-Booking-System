package mediator;

import com.google.gson.Gson;
import model.Guest;
import model.Model;
import model.Room;
import model.RoomBooking;

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
          ArrayList<RoomBookingTransferObject> allBookings = null;
          try
          {
            allBookings = model.getAllBookings("").getConvertedList();
          }
          catch (Exception e)
          {
            out.println(
                json.toJson(new RoomBookingTransfer("error", e.getMessage())));
          }

          RoomBookingTransfer roomBookingTransfer = new RoomBookingTransfer(
              "AllBookings", allBookings);

          jsonString = json.toJson(roomBookingTransfer);
          out.println(jsonString);
          break;
        }

        case "BookedBookings":
        {
          ArrayList<RoomBookingTransferObject> bookedBookings = null;
          try
          {
            bookedBookings = model.getAllBookings("booked").getConvertedList();
          }
          catch (Exception e)
          {
            e.printStackTrace();
            out.println(
                json.toJson(new RoomBookingTransfer("error", e.getMessage())));
          }

          RoomBookingTransfer roomBookingTransfer = new RoomBookingTransfer(
              "BookedBookings", bookedBookings);
          jsonString = json.toJson(roomBookingTransfer);
          out.println(jsonString);
          break;
        }

        case "InProgressBookings":
        {
          ArrayList<RoomBookingTransferObject> inProgressBookings = null;
          try
          {
            inProgressBookings = model.getAllBookings("inProgress")
                .getConvertedList();
          }
          catch (Exception e)
          {
            out.println(
                json.toJson(new RoomBookingTransfer("error", e.getMessage())));
          }

          RoomBookingTransfer roomBookingTransfer = new RoomBookingTransfer(
              "InProgressBookings", inProgressBookings);

          jsonString = json.toJson(roomBookingTransfer);
          out.println(jsonString);
          break;
        }

        case "CancelledBookings":
        {
          ArrayList<RoomBookingTransferObject> cancelledBookings = null;
          try
          {
            cancelledBookings = model.getAllBookings("cancelled")
                .getConvertedList();
          }
          catch (Exception e)
          {
            out.println(
                json.toJson(new RoomBookingTransfer("error", e.getMessage())));
          }

          RoomBookingTransfer roomBookingTransfer = new RoomBookingTransfer(
              "CancelledBookings", cancelledBookings);

          jsonString = json.toJson(roomBookingTransfer);
          out.println(jsonString);
          break;
        }

        case "ProcessBooking":
        {

          RoomBookingTransfer roomBooking = json.fromJson(message,
              RoomBookingTransfer.class);
          try
          {
            model.processBooking(roomBooking.getBookingNr());
          }
          catch (Exception e)
          {
            out.println(
                json.toJson(new RoomBookingTransfer("error", e.getMessage())));
          }
          out.println(
              json.toJson(new RoomBookingTransfer("Success", "Success")));
          break;
        }

        case "CancelBooking":
        {
          RoomBookingTransfer roomBookingTransfer = json.fromJson(message,
              RoomBookingTransfer.class);
          try
          {
            model.cancelBooking(roomBookingTransfer.getBookingNr());
            out.println(
                json.toJson(new RoomBookingTransfer("Success", "Success")));
          }
          catch (Exception e)
          {
            out.println(
                json.toJson(new RoomBookingTransfer("error", e.getMessage())));
          }
          break;
        }

        case "editGuest":
          GuestTransfer guest = json.fromJson(message, GuestTransfer.class);
          try
          {
            System.out.println(guest.getFullName());
            model.editGuest(guest.getBookingID(), guest.getfName(),
                guest.getlName(), guest.getEmail(), guest.getPhoneNr());
          }
          catch (Exception throwables)
          {
            out.println(json.toJson(
                new RoomTransfer("error", throwables.getMessage())));
          }
          out.println(successMessage);
          break;
        case "editGuestWithUsername":
          guest = json.fromJson(message, GuestTransfer.class);
          try
          {
            System.out.println(guest.getFullName());
            model.editGuestWithUsername(guest.getUsername(), guest.getfName(),
                    guest.getlName(), guest.getEmail(), guest.getPhoneNr());
          }
          catch (Exception throwables)
          {
            out.println(json.toJson(
                    new RoomTransfer("error", throwables.getMessage())));
          }
          out.println(successMessage);
          break;
        case "editBooking":
          RoomBookingTransfer bookingEdit = json.fromJson(message,
              RoomBookingTransfer.class);
          try
          {
            model.editBooking(bookingEdit.getBookingNr(),
                bookingEdit.getStartDate(), bookingEdit.getEndDate(),
                bookingEdit.getRoomID());
            out.println(successMessage);
          }
          catch (Exception e)
          {
            System.out.println(e.getMessage());
            out.println(
                json.toJson(new RoomBookingTransfer("error", e.getMessage())));
          }
          //out.println(successMessage);
          //out.println(new RoomTransfer("Success", "Success"));
          break;

        case "removeBooking":
          RoomBookingTransfer bookingRemove = json.fromJson(message,
              RoomBookingTransfer.class);
          try
          {
            model.cancelBooking(bookingRemove.getBookingNr());
            out.println(successMessage);
          }
          catch (Exception e)
          {
            out.println(
                json.toJson(new RoomBookingTransfer("error", e.getMessage())));
          }
          //out.println(new RoomTransfer("Success", "Success"));
          break;

        case "getRoom":
        {
          RoomTransfer getRoom = json.fromJson(message, RoomTransfer.class);
          try
          {
            Room roomie = model.getRoom(getRoom.getMessage());
            RoomTransfer roomieTransfer = new RoomTransfer("getRoom", roomie);

            out.println(json.toJson(roomieTransfer));
          }
          catch (Exception e)
          {
            e.printStackTrace();
            out.println(json.toJson(new RoomTransfer("error", e.getMessage())));
          }
          break;
        }

        case "getAllGuests":
          try
          {
            ArrayList<Guest> guests = model.getAllGuests();
            GuestTransfer guestTransfer = new GuestTransfer("Success", guests);
            out.println(json.toJson(new GuestTransfer("Success", guests)));
          }
          catch (Exception e)
          {
            out.println(
                json.toJson(new GuestTransfer("error", e.getMessage())));
          }
          break;

          /*
        case "getBookingWithGuest":
        {
          RoomBookingTransfer receivedRoomBookingTransfer = json.fromJson(
              message, RoomBookingTransfer.class);
          System.out.println("Client Handler start!");
          try
          {
            RoomBookingTransfer toSend = model.getBookingWithGuest(
                receivedRoomBookingTransfer.getBookingNr(),
                receivedRoomBookingTransfer.getGuestID());
            System.out.println("SENDING: " + toSend);
            out.println(json.toJson(toSend));
          }
          catch (Exception e)
          {
            out.println(
                json.toJson(new RoomBookingTransfer("error", e.getMessage())));
          }

          break;
        }
        case "getGuestByUsername":{
          GuestTransfer guestTransfer = json.fromJson(message, GuestTransfer.class);
          try {
            GuestTransfer transfer = model.getGuestByUsername(guestTransfer.getUsername());
            out.println(json.toJson(transfer));
          }
          catch (Exception e)
          {
            out.println(json.toJson(new GuestTransfer(e.getMessage())));
          }
          break;
        }

           */

        case "registerAGuest":
        {
          GuestTransfer guestTransfer = json.fromJson(message, GuestTransfer.class);
          try {
            model.register(guestTransfer.getfName(),guestTransfer.getlName(),guestTransfer.getEmail(),guestTransfer.getPhoneNr(),
                    guestTransfer.getUsername(), guestTransfer.getPassword());
            out.println(json.toJson(new GuestTransfer("Success")));
          }
          catch (Exception e)
          {
            out.println(json.toJson(new GuestTransfer(e.getMessage())));
          }
          break;
        }

        case "login":
        {
          GuestTransfer guestTransfer = json.fromJson(message,GuestTransfer.class);
          try {
          model.login(guestTransfer.getUsername(), guestTransfer.getPassword());
            out.println(json.toJson(new GuestTransfer("Success")));
          }
          catch (Exception e)
          {
            out.println(json.toJson(new GuestTransfer(e.getMessage())));
          }
          break;
        }

        case "bookARoomWhenLoggedIn":
        {
          try {
            RoomBookingTransfer roomBookingTransfer = json.fromJson(message, RoomBookingTransfer.class);
            model.bookARoomWhenLoggedIn(roomBookingTransfer.getRoomID(),roomBookingTransfer.getStartDate(),roomBookingTransfer.getEndDate(),roomBookingTransfer.getUsername());
            out.println(json.toJson(new RoomBookingTransfer("Success","null")));
          }
          catch (Exception e)
          {
            out.println(json.toJson(new RoomBookingTransfer("Success",e.getMessage())));
          }

          break;
        }

        case "getBookingsWhenLoggedIn":
        {
          RoomBookingTransfer roomBookingTransfer = json.fromJson(message, RoomBookingTransfer.class);
          ArrayList<RoomBookingTransferObject> allBookings = null;
          try
          {
            allBookings = model.getBookingsWhenLoggedIn(roomBookingTransfer.getUsername()).getConvertedList();
          }
          catch (Exception e)
          {
            out.println(json.toJson(new RoomBookingTransfer("error", e.getMessage())));
          }

          roomBookingTransfer = new RoomBookingTransfer("AllBookings", allBookings);

          jsonString = json.toJson(roomBookingTransfer);
          out.println(jsonString);
          break;
        }



      }

    }
  }

}
