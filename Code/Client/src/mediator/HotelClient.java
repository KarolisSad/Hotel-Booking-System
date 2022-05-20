package mediator;

import com.google.gson.Gson;
import model.Guest;
import model.Model;
import model.Room;
import model.RoomType;

import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;

/**
 * Sending request to the server and receiving requested infromation
 * 2022-05-08
 *
 * @author Group5
 */

public class HotelClient implements Model
{

  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson json;
  private Model model;
  private String message;
  private String username;

  /**
   * @param model modelManager
   * @throws IOException if Input stream or Output stream is closed while trying to access them
   */

  public HotelClient(Model model) throws IOException
  {
    this.model = model;
    socket = new Socket("localhost", 2917);
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(new PrintWriter(socket.getOutputStream()), true);
    json = new Gson();
    message = null;
    username = null;
    startReader();
  }

  /**
   * Creates HotelClientReader object and start separate thread with that object
   */
  private void startReader()
  {
    HotelClientReader hotelClientReader = new HotelClientReader(this, in);
    Thread thread = new Thread(hotelClientReader);
    thread.setDaemon(true);
    thread.start();
  }

  /**
   * When message is received set message instance variable to received message
   *
   * @param message received message from server
   */
  public synchronized void receive(String message)
  {
    this.message = message;
    notify();
  }

  /**
   * Creates RoomTransfer object, transfer it to Json format and sends it to server
   * while putting itself to wait until message is received from a server
   *
   * @param roomID       room unique ID
   * @param type         room type
   * @param numberOfBeds number of beds in a room
   * @return message containing information if room was successfully added
   */
  @Override public synchronized RoomTransfer addRoom(String roomID,
      RoomType type, int numberOfBeds)
  {
    sendToServerAsJson(
        new RoomTransfer("addOneRoom", roomID, type, numberOfBeds, null));
    message = null;
    while (message == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    return json.fromJson(message, RoomTransfer.class);
  }

  /**
   * Creates RoomTransfer object, transfer it to Json format and sends it to server
   * while putting itself to wait until message is received from a server
   *
   * @param roomID unique room ID
   * @param type   room type
   * @param nrBeds number of beds in a room
   * @return message containing information if room was successfully edited
   */
  @Override public synchronized RoomTransfer editRoomInfo(String roomID,
      RoomType type, int nrBeds)
  {
    sendToServerAsJson(new RoomTransfer("edit", roomID, type, nrBeds));
    message = null;
    try
    {
      wait();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    return json.fromJson(message, RoomTransfer.class);
  }

  /**
   * Creates GuestTransfer object, transfers it to Json format and sends it to server
   *  while putting itself to wait until message is received from a server
   * @param type  room type
   * @param bookingID booking identification
   * @param fName  guest's first name
   * @param lName  guest's last name
   * @param email guest's email
   * @param phoneNr  guest's phone number
   *
   * @return message containing information if room was successfully edited
   */
  @Override public synchronized GuestTransfer editGuest(String type,
      int bookingID, String fName, String lName, String email, int phoneNr)
  {
    sendToServerAsJson(
        new GuestTransfer(type, bookingID, fName, lName, email, phoneNr));
    message = null;
    while (message == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }

    return json.fromJson(message, GuestTransfer.class);
  }

  /**
   * Creates RoomTransfer object, transfer it to Json format and sends it to server
   * while putting itself to wait until message is received from a server
   *
   * @param ID unique room ID
   * @return message containing information if room was successfully removed
   */
  @Override public synchronized RoomTransfer removeRoom(String ID)
  {
    sendToServerAsJson(new RoomTransfer("removeOneRoom", ID, null, null, null));
    message = null;
    while (message == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    return json.fromJson(message, RoomTransfer.class);
  }

  /**
   * Creates RoomTransfer object, transfer it to Json format and sends it to server
   * while putting itself to wait until message is received from a server
   *
   * @return arrayList of all rooms
   */
  @Override public synchronized RoomTransfer getAllRooms()
  {
    sendToServerAsJson(new RoomTransfer("getAllRooms"));
    message = null;
    while (message == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    return json.fromJson(message, RoomTransfer.class);
  }

  /**
   * Creates RoomTransfer object, transfer it to Json format and sends it to server
   * while putting itself to wait until message is received from a server
   *
   * @param startDate startDate in LocalDate Object
   * @param endDate   endDate in LocalDate Object
   * @return RoomTransfer object which contains available room list
   */
  @Override public synchronized RoomTransfer availableRooms(LocalDate startDate,
      LocalDate endDate)
  {

    sendToServerAsJson(new RoomTransfer("availableRooms", startDate, endDate));
    message = null;
    while (message == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    return json.fromJson(message, RoomTransfer.class);
  }

  /**
   * @param roomId    unique room ID
   * @param startDate startDate in LocalDate Object
   * @param endDate   endDate in LocalDate Object
   * @param guest     guest
   * @return message containing information if booking was successfully created
   */
  @Override public synchronized RoomTransfer book(String roomId,
      LocalDate startDate, LocalDate endDate, Guest guest)
  {
    sendToServerAsJson(
        new RoomTransfer("bookOneRoom", roomId, startDate, endDate, guest));
    message = null;
    while (message == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    return json.fromJson(message, RoomTransfer.class);
  }

  /**
   * Method used for getting a list of all bookings in the database that have a state of Booked.
   *
   * @return RoomBookingTransfer object containing an ArrayList of all RoomBookings with a state of Booked.
   */
  @Override public synchronized RoomBookingTransfer getBookedBookings()
  {
    RoomBookingTransfer test = new RoomBookingTransfer("BookedBookings");
    sendToServerAsJsonBooking(test);
    message = null;
    while (message == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    return json.fromJson(message, RoomBookingTransfer.class);
  }

  /**
   * Method used for getting a list of all bookings in the database.
   *
   * @return RoomBookingTransfer object containing an ArrayList of all RoomBookings.
   */
  @Override public synchronized RoomBookingTransfer getAllBookings()
  {
    // sendToServer
    sendToServerAsJsonBooking(new RoomBookingTransfer("AllBookings"));
    message = null;
    while (message == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    RoomBookingTransfer transfer = json.fromJson(message, RoomBookingTransfer.class);
    return json.fromJson(message, RoomBookingTransfer.class);
  }

  /**
   * Method used for getting a list of all bookings in the database that have a state of Cancelled.
   *
   * @return RoomBookingTransfer object containing an ArrayList of all RoomBookings with a state of Cancelled.
   */
  @Override public synchronized RoomBookingTransfer getCancelledBookings()
  {
    sendToServerAsJsonBooking(new RoomBookingTransfer("CancelledBookings"));
    message = null;
    while (message == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    return json.fromJson(message, RoomBookingTransfer.class);
  }

  /**
   * Method used for getting a list of all bookings in the database that have a state of In progress.
   *
   * @return RoomBookingTransfer object containing an ArrayList of all RoomBookings with a state of In progress.
   */
  @Override public synchronized RoomBookingTransfer getInProgressBookings()
  {
    sendToServerAsJsonBooking(new RoomBookingTransfer("InProgressBookings"));
    message = null;
    while (message == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    return json.fromJson(message, RoomBookingTransfer.class);
  }

  /**
   * Method used to send a request for an update of BookingState for a specific booking.
   *
   * @param bookingNumber The bookingID of the booking to be processed
   * @return A RoomBookingTransfer object containing a Success message if the operation succeeded, or an exception-message if not.
   */
  @Override public synchronized RoomBookingTransfer processBooking(
      int bookingNumber)
  {
    sendToServerAsJsonBooking(
        new RoomBookingTransfer("ProcessBooking", bookingNumber));
    message = null;
    try
    {
      wait();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }

    return json.fromJson(message, RoomBookingTransfer.class);
  }

  /**
   * Method used to send a request to get a list of all guests.
   * @return A success message with a list of guests if succeeded.
   */
  @Override public synchronized RoomBookingTransfer cancelBooking(int bookingNumber)
  {
    sendToServerAsJsonBooking(
        new RoomBookingTransfer("CancelBooking", bookingNumber));
    message = null;
    try
    {
      wait();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    return json.fromJson(message, RoomBookingTransfer.class);  }

  /**
   * Method used to send a request to get a booking information.
   * @return A success message with booking information.
   */
  @Override public synchronized GuestTransfer getAllGuests()
  {
    sendToServerAsJson(new GuestTransfer("getAllGuests"));
    message = null;
    while (message == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }

    return json.fromJson(message, GuestTransfer.class);
  }

  /**
   * A method meant for getting a room from the server.
   * @param roomId booking ID.
   * @return RoomTransfer object.
   */
  @Override public synchronized RoomTransfer getRoom(String roomId)
  {
    RoomTransfer roomTransfer = new RoomTransfer("getRoom", roomId);
    sendToServerAsJson(roomTransfer);
    message = null;
    while (message == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    return json.fromJson(message, RoomTransfer.class);
  }

  /**
   * Method used to send a request to get a booking information and a guest object.
   * @param bookingNumber a booking identification
   * @param phoneNumber guest's phone number
   * @return A success message with booking and guest information.
   */
  @Override public synchronized RoomBookingTransfer getBookingWithGuest(int bookingNumber,
      int phoneNumber)
  {
    sendToServerAsJsonBooking(
        new RoomBookingTransfer("getBookingWithGuest", bookingNumber,
            phoneNumber));
    try
    {
      wait();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    return json.fromJson(message, RoomBookingTransfer.class);
  }



  /**
   * Makes received object into Json format and sends it to a server
   *
   * @param roomTransfer object which is used to transfer information to server
   */
  public synchronized void sendToServerAsJson(RoomTransfer roomTransfer)
  {
    String jsonString = json.toJson(roomTransfer);
    out.println(jsonString);
  }

  /**
   * Makes received object into Json format and sends it to a server
   *
   * @param roomBookingTransfer object which is used to transfer information to server
   */
  public synchronized void sendToServerAsJsonBooking(
      RoomBookingTransfer roomBookingTransfer)
  {
    String jsonString = json.toJson(roomBookingTransfer);
    out.println(jsonString);
  }

  /**
   * Makes received object into Json format and sends it to a server
   *
   * @param guestTransfer object which is used to transfer information to server
   */
  private void sendToServerAsJson(GuestTransfer guestTransfer)
  {
    String jsonString = json.toJson(guestTransfer);
    out.println(jsonString);
  }

  /**
   * A method meant for making object into a json format and sends it to a server.
   *
   * @param bookingId booking ID
   * @param startDate start date
   * @param endDate   end date
   * @param roomid    room number
   * @return RoomBookingTransfer object in json format
   */
  @Override public synchronized RoomBookingTransfer editBooking(int bookingId,
      LocalDate startDate, LocalDate endDate, String roomid)
  {
    sendToServerAsJsonBooking(
        new RoomBookingTransfer("editBooking", bookingId, startDate, endDate,
            roomid));
    message = null;


    try
    {
      wait();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    return json.fromJson(message, RoomBookingTransfer.class);
  }

  /**
   * A method meant for making RoomBookingTransfer object into a json format and sends it to a server.
   * It request to remove the specified booking.
   * @param bookingId booking ID
   */
  @Override public synchronized RoomBookingTransfer removeBooking(int bookingId)
  {
    sendToServerAsJsonBooking(
        new RoomBookingTransfer("removeBooking", bookingId));
    message = null;

    try
    {
      wait();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    return json.fromJson(message, RoomBookingTransfer.class);
  }

  @Override
  public synchronized void logOutForGuest() {
    this.username = null;
  }

  @Override
  public synchronized GuestTransfer login(String username, String password) {
    sendToServerAsJson(new GuestTransfer("login",username,password));
    message = null;
    while (message == null)
    {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    GuestTransfer guestTransfer = json.fromJson(message,GuestTransfer.class);
    if (guestTransfer.getType().equals("Success"))
    {
      this.username = username;
    }
    return json.fromJson(message,GuestTransfer.class);
  }

  @Override
  public synchronized GuestTransfer register(String fName, String lName, String email, int phoneNumber, String username, String password) {
    sendToServerAsJson(new GuestTransfer("registerAGuest",fName,lName,email,phoneNumber,username,password));
    message = null;
    while (message == null)
    {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    GuestTransfer guestTransfer = json.fromJson(message, GuestTransfer.class);
    System.out.println(guestTransfer.getType() + " styppe");
    if (guestTransfer.getType().equals("Success"))
    {
      this.username = username;
    }
    return guestTransfer;
  }

  @Override
  public synchronized RoomBookingTransfer getBookingsWhenLoggedIn() {
    sendToServerAsJsonBooking(new RoomBookingTransfer("getBookingsWhenLoggedIn", username));
    System.out.println("Username: " + username + " When getting all bookings.");
    try
    {
      wait();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    return json.fromJson(message, RoomBookingTransfer.class);
  }

  @Override
  public synchronized RoomBookingTransfer bookARoomWhenLoggedIn(String roomName, LocalDate startDate, LocalDate endDate) {
    sendToServerAsJsonBooking(new RoomBookingTransfer("bookARoomWhenLoggedIn", roomName,startDate,endDate,username));
    message = null;
    while (message == null)
    {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return json.fromJson(message, RoomBookingTransfer.class);
  }


  @Override public void addListener(PropertyChangeListener listener)
  {

  }

  @Override public void removeListener(PropertyChangeListener listener)
  {

  }
}
