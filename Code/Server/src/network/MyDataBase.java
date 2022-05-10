package network;

import mediator.RoomTransfer;
import model.Guest;
import model.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=hotel", "postgres", "123");
    }

    public void addOneRoom(String roomID, String roomType, int nrBeds) throws SQLException {
        try (Connection connection = getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO room(roomID, roomType, nrBeds) VALUES (?, ?, ?);");

                statement.setString(1, roomID);
                statement.setString(2, roomType);
                statement.setInt(3, nrBeds);
                statement.executeUpdate();
            } catch (Exception e) {
                String error = e.toString();
                // else if because you are not able to use .contains in switch :)
                if (error.contains("room_pkey")) {
                    throw new IllegalArgumentException("Room with ID: " + roomID + " already exists!") ;
                } else if (error.contains("room_roomtype_check")) {
                    throw new IllegalArgumentException("Room must be one of the fallowing: Family, Single, Double, Suite.");
                } else if (error.contains("room_nrbeds_check")) {
                    throw new IllegalArgumentException("Number of beds must be between 1 and 20");
                }
            }
        }
//        return "Room with ID: " + roomID + " was successfully added!";
    }

    // Return true if room exits.
    // PURPOSE: being able to throw exception. Otherwise it would try to remove not existent room. (Might be better solution)
    public boolean doesRoomExist(String roomID) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM room WHERE roomID = (?);");
            statement.setString(1, roomID);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    public void removeOneRoom(String ID) throws SQLException {
        if (!(doesRoomExist(ID))) {
            throw new IllegalArgumentException("Room with ID: " + ID + " doesn't exist!");
        }
        try (Connection connection = getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM room WHERE roomID =(?);");

                statement.setString(1, ID);
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Remove all bookings including this room.");
            }
        }
//        return "Room with ID: " + ID + " was removed.";
    }

    public ArrayList<Room> getAllRooms() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM room;");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Room> rooms = new ArrayList<>();

            while (resultSet.next()) {
                String roomId = resultSet.getString("roomid");
                String roomType = resultSet.getString("roomtype");
                int nrBends = resultSet.getInt("nrbeds");
                Room room = new Room(roomId, roomType, nrBends);
                rooms.add(room);
            }
            if (rooms == null)
            {
                throw new IllegalArgumentException("No rooms were added yet.");
            }
            return rooms;
        }
    }

    public RoomTransfer availableRooms(LocalDate startDate, LocalDate endDate) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from room where roomID in (select roomID from room\n" +
                    "except\n" +
                    "select roomID\n" +
                    "from roomBooking\n" +
                    "where startDate  between (?) and (?)\n" +
                    "OR endDate between (?) and (?));");
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
                Room room = new Room(roomId, roomType, nrBends);
                rooms.add(room);
            }
            if (rooms == null)
            {
                throw new IllegalArgumentException("No available room were found. Please select different date.");
            }
            return new RoomTransfer("availableRooms",rooms,"");
        }
    }

    public void book(String roomId, LocalDate startDate, LocalDate endDate, Guest guest) throws SQLException {
            addGuest(guest);
        try (Connection connection = getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement("insert into roomBooking(startDate, endDate, guest, roomID)\n" +
                        "values (?,?,?,?)");

                statement.setObject(1, startDate);
                statement.setObject(2, endDate);
                statement.setInt(3, guest.getPhoneNr());
                statement.setString(4,roomId);
                statement.executeUpdate();
            } catch (Exception e) {
                String message = e.getMessage();
                if (message.contains("end_date_is_before_start_date"))
                {
                    throw new IllegalArgumentException("End date must be later then start date!");
                }
                else if(message.contains("start_date_is_before_current_date"))
                {
                    throw new IllegalArgumentException("You cannot make bookings before today!");
                }
                else if(message.contains("double_booking()"))
                {
                    throw new IllegalArgumentException("Room "+roomId+" is already booked during these dates. Please select different room. ");
                }
                else if (message.contains("roombooking_roomid_fkey"))
                {
                    throw new IllegalArgumentException("Room with ID: " + roomId + " doesn't exist. Please choose different room.");
                }
                throw new IllegalArgumentException("Room wasn't booked");
            }
        }
//        return  "Room: "+ roomId + " From: "+ startDate.toString() + " To: " + endDate.toString()+ " was successfully booked!";
    }

    // Adding guest, if guest already exists, no exceptions will be thrown.
    public void addGuest(Guest guest) throws SQLException {
        try (Connection connection = getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO guest(fName, lName, email, phoneNr) VALUES (?, ?, ?, ?);");

                statement.setString(1, guest.getfName());
                statement.setString(2, guest.getlName());
                statement.setString(3, guest.getEmail());
                statement.setInt(4, guest.getPhoneNr());
                statement.executeUpdate();
            } catch (Exception e) {
                // :)
            }
        }
    }

    public void editRoomInfo(String roomID, String type, int nrBeds) throws SQLException {
        try (Connection connection = getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement("update room\n" +
                        "set nrBeds = ?,\n" +
                        "    roomType =?\n" +
                        "where roomID = ?;");

                statement.setString(3, roomID);
                statement.setString(2, type);
                statement.setInt(1, nrBeds);
                statement.executeUpdate();
            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to edit " + roomID+ " room.");
            }
        }
//        return "Changes were successfully made to " + roomID;
    }


}
