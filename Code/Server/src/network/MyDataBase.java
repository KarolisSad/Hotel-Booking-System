package network;

import mediator.RoomTransfer;
import model.Guest;
import model.Room;
import model.RoomBooking;
import model.RoomType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class MyDataBase {

    private static MyDataBase instance;

    private MyDataBase() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized MyDataBase getInstance() throws SQLException {
        if (instance == null) {
            instance = new MyDataBase();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {

        // TODO - My password is different, so needed to change this.

        // Karolis
         //return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hotel", "postgres", "123");
        // Nina
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hotel","postgres", "Milit@ria2003");
        // Christian
            // return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hotel","postgres", "123456789");
        // Juste
       // return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hotel","postgres", "JUSTESPASSWORD");
    }

    public void addOneRoom(String roomID, RoomType roomType, int nrBeds)
            throws SQLException {
        try (Connection connection = getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO room(roomID, roomType, nrBeds) VALUES (?, ?, ?);");

                statement.setString(1, roomID);
                statement.setString(2, roomType.toString());
                statement.setInt(3, nrBeds);
                statement.executeUpdate();
            } catch (Exception e) {
                String error = e.toString();
                // else if because you are not able to use .contains in switch :)
                if (error.contains("room_pkey")) {
                    throw new IllegalArgumentException(
                            "Room with ID: " + roomID + " already exists!");
                } else if (error.contains("room_roomtype_check")) {
                    throw new IllegalArgumentException(
                            "Room must be one of the fallowing: Family, Single, Double, Suite.");
                } else if (error.contains("room_nrbeds_check")) {
                    throw new IllegalArgumentException(
                            "Number of beds must be between 1 and 20");
                }
                throw new IllegalArgumentException("Room wasn't added!");
            }
        }
    }

    // Return true if room exits.
    // PURPOSE: being able to throw exception. Otherwise it would try to remove not existent room. (Might be better solution)
    public boolean doesRoomExist(String roomID) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM room WHERE roomID = (?);");
            statement.setString(1, roomID);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    public void removeOneRoom(String ID) throws SQLException {
        if (!(doesRoomExist(ID))) {
            throw new IllegalArgumentException(
                    "Room with ID: " + ID + " doesn't exist!");
        }
        try (Connection connection = getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM room WHERE roomID =(?);");

                statement.setString(1, ID);
                statement.executeUpdate();
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "This room has active bookings, cannot be removed..");
            }
        }
    }

    public ArrayList<Room> getAllRooms() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM room;");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Room> rooms = new ArrayList<>();

            while (resultSet.next()) {
                String roomId = resultSet.getString("roomid");
                String roomType = resultSet.getString("roomtype");
                int nrBends = resultSet.getInt("nrbeds");
                Room room = new Room(roomId,
                        RoomType.valueOf(roomType.toUpperCase(Locale.ROOT)), nrBends);
                rooms.add(room);
            }
            if (rooms == null) {
                throw new IllegalArgumentException("No rooms were added yet.");
            }
            return rooms;
        }
    }

    public ArrayList<Room> availableRooms(LocalDate startDate, LocalDate endDate)
            throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "" + "select * from room where roomID in (select roomID from room\n"
                            + "except\n" + "select roomID\n" + "from roomBooking\n"
                            + "where startDate  between (?) and (?)\n"
                            + "OR endDate between (?) and (?));");
            statement.setObject(1, startDate);
            statement.setObject(2, endDate);
            statement.setObject(3, startDate);
            statement.setObject(4, endDate);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Room> rooms = new ArrayList<>();

            while (resultSet.next()) {
                String roomId = resultSet.getString("roomid");
                String roomType = resultSet.getString("roomtype");
                int nrBends = resultSet.getInt("nrbeds");
                Room room = new Room(roomId,
                        RoomType.valueOf(roomType.toUpperCase(Locale.ROOT)), nrBends);
                rooms.add(room);
            }
            if (rooms == null) {
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
    public void book(RoomBooking roomBooking) throws SQLException {

        addGuest(roomBooking.getGuest());
        try (Connection connection = getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "insert into roomBooking(startDate, endDate, guest, roomID, state)\n"
                                + "values (?,?,?,?, ?)");

                statement.setObject(1, roomBooking.getStartDate());
                statement.setObject(2, roomBooking.getEndDate());
                statement.setInt(3, roomBooking.getGuest().getPhoneNr());
                statement.setString(4, roomBooking.getRoom().getRoomId());
                statement.setString(5, roomBooking.getState());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method used to add a guest to the database. If the database already contains a guest with the same phone number, nothing will be added.
     *
     * @param guest the guest to be added.
     */
    public void addGuest(Guest guest) throws SQLException {
        try (Connection connection = getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO guest(fName, lName, email, phoneNr) VALUES (?, ?, ?, ?);");

                statement.setString(1, guest.getfName());
                statement.setString(2, guest.getlName());
                statement.setString(3, guest.getEmail());
                statement.setInt(4, guest.getPhoneNr());
                statement.executeUpdate();
            } catch (Exception e) {
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
    public void editRoomInfo(String roomID, RoomType type, int nrBeds)
            throws SQLException {
        try (Connection connection = getConnection()) {
            System.out.println(type.toString());
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "update room\n" + "set nrBeds = ?,\n" + "    roomType =?\n"
                                + "where roomID = ?;");

                statement.setString(3, roomID);
                statement.setString(2, type.toString());
                statement.setInt(1, nrBeds);
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
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
            throws SQLException {
        ArrayList<RoomBooking> roomBookings = new ArrayList<>();

        switch (type) {
            case "": {
                try (Connection connection = getConnection()) {
                    PreparedStatement statement = connection.prepareStatement(
                            "SELECT * FROM roombooking;");
                    ResultSet resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        int bookingID = resultSet.getInt("bookingid");
                        LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
                        LocalDate endDate = resultSet.getDate("enddate").toLocalDate();
                        Guest guest = getGuest(resultSet.getInt("guest"));
                        Room room = getRoom(resultSet.getString("roomid"));
                        String state = resultSet.getString("state");

                        RoomBooking roomBooking = new RoomBooking(startDate, endDate, room,
                                guest, state, bookingID);
                        roomBookings.add(roomBooking);
                        System.out.println(roomBooking);
                    }
                }
                break;
            }

            case "booked": {
                try (Connection connection = getConnection()) {
                    PreparedStatement statement = connection.prepareStatement(
                            "SELECT * FROM roombooking WHERE state = 'Booked';");
                    ResultSet resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        int bookingID = resultSet.getInt("bookingid");
                        LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
                        LocalDate endDate = resultSet.getDate("enddate").toLocalDate();
                        Guest guest = getGuest(resultSet.getInt("guest"));
                        Room room = getRoom(resultSet.getString("roomid"));
                        String state = resultSet.getString("state");

                        RoomBooking roomBooking = new RoomBooking(startDate, endDate, room,
                                guest, state, bookingID);
                        roomBookings.add(roomBooking);
                    }
                }
                break;
            }

            case "inProgress": {
                try (Connection connection = getConnection()) {
                    PreparedStatement statement = connection.prepareStatement(
                            "SELECT * FROM roombooking WHERE state = 'In progress';");
                    ResultSet resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        int bookingID = resultSet.getInt("bookingid");
                        LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
                        LocalDate endDate = resultSet.getDate("enddate").toLocalDate();
                        Guest guest = getGuest(resultSet.getInt("guest"));
                        Room room = getRoom(resultSet.getString("roomid"));
                        String state = resultSet.getString("state");

                        RoomBooking roomBooking = new RoomBooking(startDate, endDate, room,
                                guest, state, bookingID);
                        roomBookings.add(roomBooking);
                    }
                }
                break;
            }

            case "cancelled": {
                try (Connection connection = getConnection()) {
                    PreparedStatement statement = connection.prepareStatement(
                            "SELECT * FROM roombooking WHERE state = 'Cancelled';");
                    ResultSet resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        int bookingID = resultSet.getInt("bookingid");
                        LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
                        LocalDate endDate = resultSet.getDate("enddate").toLocalDate();
                        Guest guest = getGuest(resultSet.getInt("guest"));
                        Room room = getRoom(resultSet.getString("roomid"));
                        String state = resultSet.getString("state");

                        RoomBooking roomBooking = new RoomBooking(startDate, endDate, room,
                                guest, state, bookingID);
                        roomBookings.add(roomBooking);
                    }
                }
                break;
            }

            case "archived": {
                try (Connection connection = getConnection()) {
                    PreparedStatement statement = connection.prepareStatement(
                            "SELECT * FROM roombooking WHERE state = 'Archived';");
                    ResultSet resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        int bookingID = resultSet.getInt("bookingid");
                        LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
                        LocalDate endDate = resultSet.getDate("enddate").toLocalDate();
                        Guest guest = getGuest(resultSet.getInt("guest"));
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
     * @param guestId The identifier used for finding the guest.
     * @return The guest object from the database.
     * @throws IllegalArgumentException if Guest is not found.
     */
    public Guest getGuest(int guestId) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * from guest where phoneNr = (?);");
            statement.setInt(1, guestId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String fName = resultSet.getString("fname");
                String lName = resultSet.getString("lname");
                String email = resultSet.getString("email");
                int phoneNr = resultSet.getInt("phoneNr");

                return new Guest(fName, lName, email, phoneNr);
            } else {
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
    public Room getRoom(String roomId) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * from room where roomID = (?);");
            statement.setString(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("roomid");
                String roomtype = resultSet.getString("roomtype");
                int nrBeds = resultSet.getInt("nrbeds");

                return new Room(id, Room.convertRoomTypeFromString(roomtype), nrBeds);
            } else {
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
    public void processBooking(RoomBooking booking) throws SQLException {
        try (Connection connection = getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE roombooking SET state = (?) WHERE bookingid = (?)");

                statement.setInt(2, booking.getBookingID());
                statement.setString(1, booking.getState());
                statement.executeUpdate();
            } catch (Exception e) {
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
    public void cancelBooking(RoomBooking roomBooking) throws SQLException {
        try (Connection connection = getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE roombooking SET state = (?) WHERE bookingid = (?)");

                statement.setInt(2, roomBooking.getBookingID());
                statement.setString(1, roomBooking.getState());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException(
                        "Unable to edit booking: " + roomBooking.getBookingID());
            }
        }
    }

    public void editGuest(int bookingID, String fName, String lName, String email, int phoneNr) throws SQLException {
        System.out.println("Edit guest values" + bookingID + fName + lName + email + phoneNr);

        try (Connection connection = getConnection()) {

            //searching for a quest in bookingGuest
            PreparedStatement statement = connection.prepareStatement("select distinct roomBooking.guest from roomBooking where bookingid = ?;");
            statement.setInt(1, bookingID);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("before: ");
            System.out.println(resultSet.next());
            int phoneNR = resultSet.getInt("guest");
            System.out.println(phoneNR + ": phonrNR");

            //updating info about the guest
            PreparedStatement statement3 = connection.prepareStatement("update guest\n" +
                    "set fname = ?,\n" +
                    "    lname =?,\n" +
                    "    email =?\n" +
                    "where phonenr = ?;");

            statement3.setInt(4, phoneNR);
            statement3.setString(3, email);
            statement3.setString(2, lName);
            statement3.setString(1, fName);
            statement3.executeUpdate();
            System.out.println("Done with editing");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Guest> getAllGuests() throws SQLException {
        try (Connection connection = getConnection()) {
            ArrayList<Guest> allGuests = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * from guest;");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String fName = resultSet.getString("fname");
                String lName = resultSet.getString("lname");
                String email = resultSet.getString("email");
                int phonenr = resultSet.getInt("phonenr");
                allGuests.add(new Guest(fName,lName,email,phonenr));
                return allGuests;
            } else {
                throw new IllegalArgumentException("Room not found");
            }

        }
    }
}
