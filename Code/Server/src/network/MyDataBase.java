package network;

import mediator.GuestTransfer;
import mediator.RoomBookingTransfer;
import model.Guest;
import model.Room;
import model.RoomBooking;
import model.RoomType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A class connecting the server to the database.
 * Created as Singleton.
 * @author Group 5
 * @version 24/05/2022
 */
public class MyDataBase
{

  private static MyDataBase instance;

  /**
   * A constructor registering the Driver.
   */
  private MyDataBase() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Method creating an instance variable of the class for the first time.
   * Then returning the same variable every time its called.
   *
   * @return MyDataBase instance variable.
   * @throws SQLException
   */
  public static synchronized MyDataBase getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new MyDataBase();
    }
    return instance;
  }

  /**
   * Getting connection with database.
   * @return database connection
   * @throws SQLException
   */
  private Connection getConnection() throws SQLException
  {
          ////////////////////////////////////////////////////
          //                                                //
          //       USE ONE OF THE BELOW FOR NORMAL USE      //
          //                                                //
          ////////////////////////////////////////////////////

    // Karolis
    // return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hotel", "postgres", "123");
    // Nina
     // return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hotel","postgres", "Milit@ria2003");
    // Christian
     //  return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hotel","postgres", "123456789");
    // Juste
    // return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hotel","postgres", "Lopukas1");


          ////////////////////////////////////////////////////
          //                                                //
          //     USE ONE OF THE BELOW FOR jUnit Testing     //
          //                                                //
          ////////////////////////////////////////////////////

    // Karolis
    //return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hoteltest", "postgres", "123");
    // Nina
    //return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hoteltest","postgres", "Milit@ria2003");
    // Christian
      return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hoteltest","postgres", "123456789");
    // Juste
   // return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hoteltest","postgres", "Lopukas1");


  }

  public void addOneRoom(Room room)

  /**
   * A method adding a room to the database.
   * @param roomID
   * @param roomType
   * @param nrBeds
   * @throws SQLException
   */
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      try
      {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO room(roomID, roomType, nrBeds, dailyPrice) VALUES (?, ?, ?, ?);");

        statement.setString(1, room.getRoomId());
        statement.setString(2, room.getRoomType().toString());
        statement.setInt(3, room.getNumberOfBeds());
        statement.setInt(4, room.getPrice());
        statement.executeUpdate();
      }
      catch (Exception e)
      {
        String error = e.toString();
        // else if because you are not able to use .contains in switch :)
        if (error.contains("room_pkey"))
        {
          throw new IllegalArgumentException(
              "Room with ID: " + room.getRoomId() + " already exists!");
        }
        else if (error.contains("room_roomtype_check"))
        {
          throw new IllegalArgumentException(
              "Room must be one of the fallowing: Family, Single, Double, Suite.");
        }
        else if (error.contains("room_nrbeds_check"))
        {
          throw new IllegalArgumentException(
              "Number of beds must be between 1 and 20");
        }
        throw new IllegalArgumentException("Room wasn't added!");
      }
    }
  }

  /**
   * A method checking if a specific room is registered in the database.
   * @param roomID
   * @return true if room exits.
   * @throws SQLException
   */
  // Return true if room exits.
  // PURPOSE: being able to throw exception. Otherwise it would try to remove not existent room. (Might be better solution)
  public boolean doesRoomExist(String roomID) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM room WHERE roomID = (?);");
      statement.setString(1, roomID);
      ResultSet resultSet = statement.executeQuery();
      return resultSet.next();
    }
  }

  /**
   * A method removing a room with specific ID, from the database.
   * @param ID
   * @throws SQLException
   */
  public void removeOneRoom(String ID) throws SQLException
  {
    if (!(doesRoomExist(ID)))
    {
      throw new IllegalArgumentException(
          "Room with ID: " + ID + " doesn't exist!");
    }
    try (Connection connection = getConnection())
    {
      try
      {
        PreparedStatement statement = connection.prepareStatement(
            "DELETE FROM room WHERE roomID =(?);");

        statement.setString(1, ID);
        statement.executeUpdate();
      }
      catch (Exception e)
      {
        throw new IllegalArgumentException(
            "This room has active bookings, cannot be removed..");
      }
    }
  }

  /**
   * A method getting information about all rooms that are in the database.
   * @return ArrayList of rooms
   * @throws SQLException
   */
  public ArrayList<Room> getAllRooms() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM room ORDER BY roomid;");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Room> rooms = new ArrayList<>();

      while (resultSet.next())
      {
        String roomId = resultSet.getString("roomid");
        String roomType = resultSet.getString("roomtype");
        int nrbeds = resultSet.getInt("nrbeds");
        int price = resultSet.getInt("dailyprice");
        Room room = new Room(roomId,
            RoomType.valueOf(roomType.toUpperCase(Locale.ROOT)), nrbeds, price);
        rooms.add(room);
      }
      if (rooms == null)
      {
        throw new IllegalArgumentException("No rooms were added yet.");
      }
      return rooms;
    }
  }

  /**
   * A method getting information about all rooms that are in the database.
   * @param startDate
   * @param endDate
   * @return ArrayList of rooms
   * @throws SQLException
   */
  public ArrayList<Room> availableRooms(LocalDate startDate, LocalDate endDate)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT *\n" + "FROM regularrooms\n" + "WHERE roomID IN (SELECT roomID\n"
              + "    FROM room\n" + "    EXCEPT\n" + "        SELECT roomID\n"
              + "                 FROM roomBooking\n"
              + "                 WHERE state in ('Booked', 'In Progress', 'Archived') AND\n"
              + "                         (startDate BETWEEN (?) AND (?)\n"
              + "                         OR endDate BETWEEN (?) AND (?)))"
              + "ORDER BY roomID;");
      statement.setObject(1, startDate);
      statement.setObject(2, endDate);
      statement.setObject(3, startDate);
      statement.setObject(4, endDate);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Room> rooms = new ArrayList<>();

      while (resultSet.next())
      {
        String roomId = resultSet.getString("roomid");
        String roomType = resultSet.getString("roomtype");
        int nrBends = resultSet.getInt("nrbeds");
        int price = resultSet.getInt("dailyprice");
        Room room = new Room(roomId,
            RoomType.valueOf(roomType.toUpperCase(Locale.ROOT)), nrBends, price);
        rooms.add(room);
      }
      if (rooms.isEmpty())
      {
        throw new IllegalArgumentException(
            "No available room were found. Please select different date.");
      }
      return rooms;
    }
  }

  /**
   * A method used to add a RoomBooking to the database.
   *
   * @param roomBooking the RoomBooking to be added.
   */
  public void book(RoomBooking roomBooking) throws SQLException
  {
    addGuest(roomBooking.getGuest());
    try (Connection connection = getConnection())
    {
      try
      {
        PreparedStatement statement = connection.prepareStatement(
            "insert into roomBooking(startDate, endDate, guest, roomID, state)\n"
                + "values (?,?,?,?, ?)");

        statement.setObject(1, roomBooking.getStartDate());
        statement.setObject(2, roomBooking.getEndDate());
        statement.setString(3, roomBooking.getGuest().getUsername());
        statement.setString(4, roomBooking.getRoom().getRoomId());
        statement.setString(5, roomBooking.getState());
        statement.executeUpdate();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }

  /**
   * Method used to add a guest to the database. If the database already contains a guest with the same phone number, nothing will be added.
   *
   * @param guest the guest to be added.
   */
  public void addGuest(Guest guest) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      try
      {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO guest(username, fName, lName, email, phoneNr) VALUES (?, ?, ?, ?, ?);");

        statement.setString(1, guest.getUsername());
        statement.setString(2, guest.getfName());
        statement.setString(3, guest.getlName());
        statement.setString(4, guest.getEmail());
        statement.setInt(5, guest.getPhoneNr());
        statement.executeUpdate();
      }
      catch (Exception e)
      {
      }
    }
  }

  /**
   * Method used for editing information about a room in
   *
   * @param roomID the ID of the room to be edited.
   * @param type   the new type to be assigned to the room.
   * @param nrBeds The new number of beds to be assigned to the room.
   * @throws IllegalArgumentException if room is able to be edited.
   */
  public void editRoomInfo(String roomID, RoomType type, int nrBeds, int price)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {

      try
      {
        PreparedStatement statement = connection.prepareStatement(
            "update room\n" + "set nrBeds = ?,\n" + "    roomType =?,\n" + " dailyPrice=?"
                + "where roomID = ?;");

        statement.setString(4, roomID);
        statement.setString(2, type.toString());
        statement.setInt(1, nrBeds);
        statement.setInt(3, price);
        statement.executeUpdate();
      }
      catch (Exception e)
      {
        throw new IllegalArgumentException(
            "Unable to edit " + roomID + " room.");
      }
    }
  }

  /**
   * Method returning an ArrayList of RoomBooking objects whose contents depend on the argument.
   *
   * @param type What type of bookings to get. The following options are valid:
   *             "" (an empty string) returns all bookings from the database.
   *             "booked" returns all bookings that is currently in a booked state.
   *             "inProgress" returns all bookings that is currently in an in progress state.
   *             "cancelled" returns all bookings that have been cancelled.
   *             "archived" returns all bookings that have been archived.
   * @return An arrayList of RoomBooking objects with state specified by the argument.
   * @throws SQLException
   */
  public ArrayList<RoomBooking> getAllRoomBookings(String type)
      throws SQLException
  {
    ArrayList<RoomBooking> roomBookings = new ArrayList<>();

    switch (type)
    {
      case "":
      {
        try (Connection connection = getConnection())
        {
          PreparedStatement statement = connection.prepareStatement(
              "SELECT * FROM roombooking ORDER BY bookingid;");
          ResultSet resultSet = statement.executeQuery();

          while (resultSet.next())
          {
            int bookingID = resultSet.getInt("bookingid");
            LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
            LocalDate endDate = resultSet.getDate("enddate").toLocalDate();
            Guest guest = getGuest(resultSet.getString("guest"));
            Room room = getRoom(resultSet.getString("roomid"));
            String state = resultSet.getString("state");

            RoomBooking roomBooking = new RoomBooking(startDate, endDate, room,
                guest, state, bookingID);
            roomBookings.add(roomBooking);
          }
        }
        break;
      }

      case "booked":
      {
        try (Connection connection = getConnection())
        {
          PreparedStatement statement = connection.prepareStatement(
              "SELECT * FROM roombooking WHERE state = 'Booked' ORDER BY bookingid;");
          ResultSet resultSet = statement.executeQuery();

          while (resultSet.next())
          {
            int bookingID = resultSet.getInt("bookingid");
            LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
            LocalDate endDate = resultSet.getDate("enddate").toLocalDate();
            Guest guest = getGuest(resultSet.getString("guest"));
            Room room = getRoom(resultSet.getString("roomid"));
            String state = resultSet.getString("state");

            RoomBooking roomBooking = new RoomBooking(startDate, endDate, room,
                guest, state, bookingID);
            roomBookings.add(roomBooking);
          }
        }
        break;
      }

      case "inProgress":
      {
        try (Connection connection = getConnection())
        {
          PreparedStatement statement = connection.prepareStatement(
              "SELECT * FROM roombooking WHERE state = 'In progress' ORDER BY bookingid;");
          ResultSet resultSet = statement.executeQuery();

          while (resultSet.next())
          {
            int bookingID = resultSet.getInt("bookingid");
            LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
            LocalDate endDate = resultSet.getDate("enddate").toLocalDate();
            Guest guest = getGuest(resultSet.getString("guest"));
            Room room = getRoom(resultSet.getString("roomid"));
            String state = resultSet.getString("state");

            RoomBooking roomBooking = new RoomBooking(startDate, endDate, room,
                guest, state, bookingID);
            roomBookings.add(roomBooking);
          }
        }
        break;
      }

      case "cancelled":
      {
        try (Connection connection = getConnection())
        {
          PreparedStatement statement = connection.prepareStatement(
              "SELECT * FROM roombooking WHERE state = 'Cancelled' ORDER BY bookingid;");
          ResultSet resultSet = statement.executeQuery();

          while (resultSet.next())
          {
            int bookingID = resultSet.getInt("bookingid");
            LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
            LocalDate endDate = resultSet.getDate("enddate").toLocalDate();
            Guest guest = getGuest(resultSet.getString("guest"));
            Room room = getRoom(resultSet.getString("roomid"));
            String state = resultSet.getString("state");

            RoomBooking roomBooking = new RoomBooking(startDate, endDate, room,
                guest, state, bookingID);
            roomBookings.add(roomBooking);
          }
        }
        break;
      }

      case "archived":
      {
        try (Connection connection = getConnection())
        {
          PreparedStatement statement = connection.prepareStatement(
              "SELECT * FROM roombooking WHERE state = 'Archived' ORDER BY bookingid;");
          ResultSet resultSet = statement.executeQuery();

          while (resultSet.next())
          {
            int bookingID = resultSet.getInt("bookingid");
            LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
            LocalDate endDate = resultSet.getDate("enddate").toLocalDate();
            Guest guest = getGuest(resultSet.getString("guest"));
            Room room = getRoom(resultSet.getString("roomid"));
            String state = resultSet.getString("state");

            RoomBooking roomBooking = new RoomBooking(startDate, endDate, room,
                guest, state, bookingID);
            roomBookings.add(roomBooking);
          }
        }
        break;
      }
    }
    return roomBookings;
  }

  /**
   * Method that tries to get a guest object from the database.
   *
   * @param //guestId The identifier used for finding the guest.
   * @return The guest object from the database.
   * @throws IllegalArgumentException if Guest is not found.
   */
  public Guest getGuest(String guestUserName) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * from guest where username = (?);");
      statement.setString(1, guestUserName);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String userName = resultSet.getString("username");
        String fName = resultSet.getString("fname");
        String lName = resultSet.getString("lname");
        String email = resultSet.getString("email");
        int phoneNr = resultSet.getInt("phoneNr");

        return new Guest(userName, fName, lName, email, phoneNr);
      }
      else
      {
        throw new IllegalArgumentException("Guest not found");
      }

    }
  }

  /**
   * Method returning a room from the database.
   *
   * @param roomId the ID of the room to be returned.
   * @return A room object with the id given as argument.
   * @throws IllegalArgumentException if room is not found.
   */
  public Room getRoom(String roomId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * from room where roomid = (?);");
      statement.setString(1, roomId);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String id = resultSet.getString("roomid");
        String roomtype = resultSet.getString("roomtype");
        int nrBeds = resultSet.getInt("nrbeds");
        int price = resultSet.getInt("dailyprice");

        return new Room(id, Room.convertRoomTypeFromString(roomtype), nrBeds, price);
      }
      else
      {

        throw new IllegalArgumentException("Room not found");
      }
    }
  }

  /**
   * A method used for updating the state of a booking in the database.
   *
   * @param booking the booking update to perform the operation on.
   * @throws IllegalArgumentException If room booking can not be edited.
   */
  public void processBooking(RoomBooking booking) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      try
      {
        PreparedStatement statement = connection.prepareStatement(
            "UPDATE roombooking SET state = (?) WHERE bookingid = (?)");

        statement.setInt(2, booking.getBookingID());
        statement.setString(1, booking.getState());
        statement.executeUpdate();
      }
      catch (Exception e)
      {
        e.printStackTrace();
        throw new IllegalArgumentException(
            "Unable to edit booking: " + booking.getBookingID());
      }
    }
  }

  /**
   * A method used for cancelling a RoomBooking (setting the state to 'Cancelled')
   *
   * @param roomBooking the RoomBooking to be cancelled
   * @throws IllegalArgumentException if Booking cannot be edited.
   */
  public void cancelBooking(RoomBooking roomBooking) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      try
      {
        PreparedStatement statement = connection.prepareStatement(
            "UPDATE roombooking SET state = (?) WHERE bookingid = (?)");

        statement.setInt(2, roomBooking.getBookingID());
        statement.setString(1, roomBooking.getState());
        statement.executeUpdate();
      }
      catch (Exception e)
      {
        e.printStackTrace();
        throw new IllegalArgumentException(
            "Unable to edit booking: " + roomBooking.getBookingID());
      }
    }
  }

  /**
   * A method that changes guest details in the database.
   * Using bookingID to identify the guest.
   * @param bookingID
   * @param fName
   * @param lName
   * @param email
   * @param phoneNr
   * @throws SQLException
   */
  public void editGuest(int bookingID, String fName, String lName, String email,
      int phoneNr) throws SQLException
  {

    try (Connection connection = getConnection())
    {

      //searching for a quest in bookingGuest
      PreparedStatement statement = connection.prepareStatement(
          "select distinct roomBooking.guest from roomBooking where bookingid = ?;");
      statement.setInt(1, bookingID);
      ResultSet resultSet = statement.executeQuery();
      String username = resultSet.getString("guest");

      //updating info about the guest
      PreparedStatement statement3 = connection.prepareStatement(
          "update guest\n" + "set fname = ?,\n" + "lname =?,\n"
              + "    email =?,\n" + "phonenr = ? where username = ?;");

      statement3.setInt(4, phoneNr);
      statement3.setString(3, email);
      statement3.setString(2, lName);
      statement3.setString(1, fName);
      statement3.setString(5, username);
      statement3.executeUpdate();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

  }

  /**
   * A method that edits booking details in the database.
   * Using bookingId to identify the booking.
   * @param bookingId
   * @param startDate
   * @param endDate
   * @param roomId
   * @throws SQLException
   */
  public void editBooking(int bookingId, LocalDate startDate, LocalDate endDate,
      String roomId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      try
      {
        PreparedStatement statement = connection.prepareStatement(
            "update roombooking set startdate = (?), enddate = (?), roomid = (?) where bookingid = (?);");

        statement.setObject(1, startDate);
        statement.setObject(2, endDate);
        statement.setString(3, roomId);
        statement.setInt(4, bookingId);

        statement.executeUpdate();
      }
      catch (Exception e)
      {
        //e.printStackTrace();
        throw new IllegalArgumentException(e.getMessage());
      }
    }
  }

  /**
   * Method getting details about all guests registered in the database.
   * @return ArrayList of guests
   * @throws SQLException
   */
  public ArrayList<Guest> getAllGuests() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      ArrayList<Guest> allGuests = new ArrayList<>();
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * from guest;");
      ResultSet resultSet = statement.executeQuery();
      /* BEFORE
      if (resultSet.next())
      {
        String fName = resultSet.getString("fname");
        String lName = resultSet.getString("lname");
        String email = resultSet.getString("email");
        int phonenr = resultSet.getInt("phonenr");
        allGuests.add(new Guest(fName, lName, email, phonenr));
        return allGuests;
      }
      else
      {
        throw new IllegalArgumentException("Room not found");
      }
       */
      // NOW
      while (resultSet.next())
      {
        String userName = resultSet.getString("username");
        String fName = resultSet.getString("fname");
        String lName = resultSet.getString("lname");
        String email = resultSet.getString("email");
        int phonenr = resultSet.getInt("phonenr");
        allGuests.add(new Guest(userName, fName, lName, email, phonenr));
      }
      return allGuests;

    }
  }

  /**
   * A method that adds a new guest to the database.
   * @param guest
   * @throws SQLException
   */
  public void register(Guest guest) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      try
      {
        PreparedStatement statement = connection.prepareStatement(
            "insert into guest (username, fName, lName, email, phoneNr)\n"
                + "values (?,?,?,?,?);");

        statement.setString(1, guest.getUsername());
        statement.setString(2, guest.getfName());
        statement.setString(3, guest.getlName());
        statement.setString(4, guest.getEmail());
        statement.setInt(5, guest.getPhoneNr());
        statement.executeUpdate();

        PreparedStatement statement2 = connection.prepareStatement(
            "insert into login(username, userPassword)\n" + "values (?,?);");
        statement2.setString(1, guest.getUsername());
        statement2.setString(2, guest.getPassword());
        statement2.executeUpdate();
      }
      catch (Exception e)
      {
        //e.printStackTrace();
        throw new IllegalArgumentException(
            "Unable to register user with: " + guest.getUsername()
                + " username.");
      }
    }
  }

  /**
   * A method checking if the guest with specific username and password exist in the database.
   * @param username
   * @param password
   * @throws SQLException
   */
  public void login(String username, String password) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "select * from login where username = ? AND userPassword =?;");
      statement.setString(1, username);
      statement.setString(2, password);
      ResultSet resultSet = statement.executeQuery();

      if (!(resultSet.next()))
      {
        throw new IllegalArgumentException(
            "User with username: " + username + " doesn't exist");
      }
    }
  }

  /**
   * Method getting all the bookings made by a person who is logged in the system.
   * @param username
   * @return ArrayList of bookings
   * @throws SQLException
   */
  public ArrayList<RoomBooking> getBookingsWhenLoggedIn(String username)
      throws SQLException
  {
    ArrayList<RoomBooking> toSend = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "select * from roomBooking where guest = ?;");
      statement.setString(1, username);
      ResultSet result = statement.executeQuery();
      while (result.next())
      {
        //        Guest guest = getGuest(result.getInt("guest"));

        Room room = getRoom(result.getString("roomid"));
        LocalDate startDate = result.getDate("startdate").toLocalDate();
        LocalDate endDate = result.getDate("enddate").toLocalDate();
        String state = result.getString("state");
        int bookingID = result.getInt("bookingid");
        RoomBooking test = new RoomBooking(bookingID, startDate, endDate, room,
            state);
        toSend.add(test);
      }
    }
    return toSend;
  }

  /**
   * Method inserting a new booking to the database.
   * Made for a person who is logged in the database.
   * @param roomBooking
   * @throws SQLException
   */
  public void bookARoomWhenLoggedIn(RoomBooking roomBooking) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      try
      {
        PreparedStatement statement = connection.prepareStatement(
            "insert into roomBooking(startDate, endDate, guest, roomID, state)\n"
                + "values (?,?,?,?,?);");

        statement.setObject(1, roomBooking.getStartDate());
        statement.setObject(2, roomBooking.getEndDate());
        statement.setString(3, roomBooking.getUsername());
        statement.setString(4, roomBooking.getRoomID());
        statement.setString(5, roomBooking.getState());
        statement.executeUpdate();
      }
      catch (Exception e)
      {
        throw new IllegalArgumentException("Booking wasn't added");
      }
    }
  }

  /**
   * Method deleting all data from all tables in database.
   * @throws SQLException
   */
  public void clearDatabase() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      try
      {
        PreparedStatement statement = connection.prepareStatement(
            "DELETE FROM roombooking;" + "DELETE FROM room;"
                + "DELETE FROM login;" + "DELETE FROM guest;");

        statement.executeUpdate();
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Method changing information about guest in the database for a guest
   * having specific username.
   * @param username
   * @param fName
   * @param lName
   * @param email
   * @param phoneNr
   */
  public void editGuestWithUsername(String username, String fName, String lName, String email, int phoneNr) {

    try (Connection connection = getConnection())
    {

      //updating info about the guest
      PreparedStatement statement3 = connection.prepareStatement(
              "update guest\n"+ "set fname = ?,\n" + "   lname =?,\n"
                      + "    email =?,\n" + " phonenr = ?\n"
                      + "where username = ?;");

      statement3.setInt(4, phoneNr);
      statement3.setString(3, email);
      statement3.setString(2, lName);
      statement3.setString(1, fName);
      statement3.setString(5, username);
      statement3.executeUpdate();
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException("User couldn't be edited.");
    }

  }

  /**
   * Method getting all information about a guest from the database.
   * @param username
   * @return GuestTransfer object called guestTransfer
   */
  public GuestTransfer getGuestByUsername(String username) {
    GuestTransfer guestTransfer =null;
    try (Connection connection = getConnection())
    {
      //updating info about the guest
      PreparedStatement statement = connection.prepareStatement(
              "select * from guest where username = ?;");

      statement.setString(1, username);

      ResultSet guest = statement.executeQuery();
      while (guest.next()) {
        String usernameg = guest.getString("username");
        String fName = guest.getString("fname");
        String lName = guest.getString("lname");
        String email = guest.getString("email");
        int phonenr = guest.getInt("phonenr");

        guestTransfer = new GuestTransfer("getGuestWithUsername", usernameg, fName, lName, email, phonenr);
      }
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException("User can't be found.");
    }
    return guestTransfer;
  }

    public ArrayList<Room> availableConferenceRooms(LocalDate startDate, LocalDate endDate) throws SQLException {
      try (Connection connection = getConnection())
      {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT *\n" + "FROM conferenceRooms\n" + "WHERE roomID IN (SELECT roomID\n"
                        + "    FROM room\n" + "    EXCEPT\n" + "        SELECT roomID\n"
                        + "                 FROM roomBooking\n"
                        + "                 WHERE state in ('Booked', 'In Progress', 'Archived') AND\n"
                        + "                         (startDate BETWEEN (?) AND (?)\n"
                        + "                         OR endDate BETWEEN (?) AND (?)))"
                        + "ORDER BY roomID;");
        statement.setObject(1, startDate);
        statement.setObject(2, endDate);
        statement.setObject(3, startDate);
        statement.setObject(4, endDate);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Room> rooms = new ArrayList<>();

        while (resultSet.next())
        {
          String roomId = resultSet.getString("roomid");
          String roomType = resultSet.getString("roomtype");
          int nrBends = resultSet.getInt("nrbeds");
          int price = resultSet.getInt("dailyPrice");
          Room room = new Room(roomId,
                  RoomType.valueOf(roomType.toUpperCase(Locale.ROOT)), nrBends, price);
          rooms.add(room);
        }
        if (rooms.isEmpty())
        {
          throw new IllegalArgumentException(
                  "No available room were found. Please select different date.");
        }
        return rooms;
      }
    }
}

