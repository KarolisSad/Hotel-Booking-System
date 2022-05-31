package model;

import mediator.GuestTransfer;
import mediator.RoomBookingTransfer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class that implements the Model interface and manages the bookings.
 *
 * @author Group 5
 * @version 24/05/2022
 */

public class ModelManager implements Model
{
    private RoomList roomList;

    private RoomBookingList bookingList;
    private PropertyChangeSupport property;
    private HotelPersistence dataBaseAdapter;

    /**
     * A constructor that is meant to initialize
     * the instance variables as a new array lists
     * that will store a list of all rooms and a list of booked rooms.
     * This constructor also initializes the adapter used for communication with the database.
     */
    public ModelManager(String postgreSQLpassword)
    {
        bookingList = new RoomBookingList();
        roomList = new RoomList();
        property = new PropertyChangeSupport(this);
        this.dataBaseAdapter = new HotelDataBase(postgreSQLpassword);
    }


    /**
     * A method that returns all available rooms by selected date
     *
     * @param startDate start date
     * @param endDate   end date
     * @return available rooms
     */
    @Override public ArrayList<Room> availableRooms(LocalDate startDate,
        LocalDate endDate) throws SQLException
    {
        checkForLegalDates(startDate, endDate);
        return dataBaseAdapter.availableRooms(startDate, endDate);
    }

    /**
     * Method creating a new Room object and adding it to a RoomList array.
     *
     * @param roomId the ID of the room to be added
     * @param type   the type of room to be added
     * @param nrBeds the number of beds in the room to be added
     * @return true if room is added successfully
     */
    @Override public void addRoom(String roomId, RoomType type, int nrBeds, int price)
        throws SQLException
    {
        Room room = new Room(roomId, type, nrBeds, price);
        dataBaseAdapter.addRoom(room);
    }

    /**
     * Method removing a room from the list of rooms in the system.
     * To prevent data-corruption, it checks if the room corresponding to the ID passed as an argument has any current or future bookings
     * using the isBookingAllowed method, and if this is not the case - removes it from the list.
     *
     * @param roomId the ID of the room to be removed
     * @return true if room is removed successfully
     * @throws IllegalArgumentException if room to be removed has any current or future bookings.
     */
    @Override public void removeRoom(String roomId) throws SQLException
    {
        //            property.firePropertyChange("RoomRemove", roomList, roomId);
        dataBaseAdapter.remove(roomId);
    }

    /**
     * Method returning the list of all rooms currently in the system.
     *
     * @return list of all rooms.
     */
    @Override public ArrayList<Room> getAllRooms() throws SQLException
    {
        return dataBaseAdapter.getAllRooms();
    }

    /**
     * A method calling the database and filling the bookingList with all bookings corresponding to the type given as argument.
     * @param type The type of bookings to get.
     * @return the bookinglist variable, filled with bookings corresponding to the type given as argument.
     *
     */
    @Override public RoomBookingList getAllBookings(String type)
        throws SQLException
    {
        bookingList.setAllBookings(dataBaseAdapter.getAllBookings(type));

        return bookingList;
    }

    /**
     * A method calling the processBooking methods in the bookinglist, and in the database.
     * By doing this, the state of the booking is changed according to the RoomBookingState-class and it's subclasses, and the booking is updated accordingly in the database.
     * @param id The id of the booking to process
     * @throws SQLException
     */
    @Override public void processBooking(int id) throws SQLException
    {
        bookingList.processBooking(id);
        dataBaseAdapter.processBooking(bookingList.getBookingById(id));
    }


    /**
     * Method calling the cancelBooking() method in the bookingList on the booking with a ID matching the one passed as argument,
     * setting the RoomBooking state of the booking to "cancelled".
     * After this, calls the cancelBooking() method in the databaseAdapter, to update the database accordingly.
     * @param id The Booking ID of the booking to be cancelled.
     * @throws SQLException
     */
    @Override public void cancelBooking(int id) throws SQLException
    {
        bookingList.cancelBooking(id);
        dataBaseAdapter.cancelBooking(bookingList.getBookingById(id));
    }

    /**
     * Method used for editing a room already added to the system.
     * Firstly, the room is received from the roomList and then the roomtype and number of beds variables are changed according to the values passed as arguments.
     *
     * @param roomId the room id of the room to be edited (The room id of the room is intentionally not possible to change with this method)
     * @param type   A string value representing the (new) type of the room.
     * @param nrBeds The (new) number of beds in the room.
     * @return true if editing succeeds
     */
    @Override public void editRoomInfo(String roomId, RoomType type, int nrBeds, int price)
        throws SQLException
    {
        if (roomId.isEmpty() || roomId == null)
        {
            throw new NullPointerException("Room ID can not be null or an empty string when editing a room.");
        }
        dataBaseAdapter.editRoomInfo(roomId, type, nrBeds, price);
    }

    /**
     * A method editing a booking in the system
     *
     * @param bookingId booking ID
     * @param startDate start date
     * @param endDate end date
     * @param roomId room number
     * @throws SQLException
     */
    @Override public void editBooking(int bookingId, LocalDate startDate,
        LocalDate endDate, String roomId) throws SQLException
    {
        checkForLegalDates(startDate, endDate);
        dataBaseAdapter.editBooking(bookingId, startDate, endDate, roomId);
    }

    /**
     * Method used for getting a room object with a specific room ID from the database, using the ID passed as argument.
     * @param roomId The RoomID of the room to get from the databse.
     * @return A Room object with a Room ID matching the one passed as an argument.
     * @throws SQLException
     */
    @Override public Room getRoom(String roomId) throws SQLException
    {
        return dataBaseAdapter.getRoom(roomId);
    }

    /**
     * Method used for getting a RoomBookingTransfer object containing information about the booking and the guest associated with it.
     * @param bookingNr The Booking ID of the booking to get.
     * @param phoneNr The phone number of the customer associated with the booking.
     * @return A RoomBookingTransfer object containing information about the booking and the guest associated with it.
     * @throws SQLException
     */
    /*
    @Override public RoomBookingTransfer getBookingWithGuest(int bookingNr,
        int phoneNr) throws SQLException
    {
        return dataBaseAdapter.getBookingWithGuest(bookingNr, phoneNr);
    }

     */

    /**
     * A method that is meant for booking a room.
     *
     * @param roomId    room number
     * @param startDate start date
     * @param endDate   end date
     * @param guest     guest
     * @return true if the room is booked and false if the room is not booked
     */
    @Override public void book(String roomId, LocalDate startDate,
        LocalDate endDate, Guest guest) throws SQLException
    {
        checkForLegalDates(startDate, endDate);
        Room room = null;
        for (Room i : getAllRooms())
        {
            if (i.getRoomId().equals(roomId))
            {
                room = i;
            }
        }
        RoomBooking booking = new RoomBooking(startDate, endDate, room, guest);
        dataBaseAdapter.book(booking);
    }

    //todo delete??
    @Override public void addListener(PropertyChangeListener listener)
    {
        property.addPropertyChangeListener(listener);
    }

    //todo delete??
    @Override public void removeListener(PropertyChangeListener listener)
    {
        property.removePropertyChangeListener(listener);
    }

    /**
     * Method setting the start and end date variables to the values passed as argument.
     *
     * @param startDate startDate
     * @param endDate   endDate
     * @throws IllegalArgumentException if any of the following are true:
     *                                  - Start date is before current date.
     *                                  - End date is the same as start date
     *                                  - End date is before start date.
     */

    public void checkForLegalDates(LocalDate startDate, LocalDate endDate)
    {
        if (startDate == null || endDate == null)
        {
            throw new NullPointerException(
                "Please enter a start date and an end date.");
        }

        else if (startDate.isBefore(LocalDate.now()))
        {
            throw new IllegalArgumentException(
                "Start date should not be before current date: " + LocalDate.now());
        }
        else if (endDate.isEqual(startDate))
        {
            throw new IllegalArgumentException(
                "End date cannot be the same date as start-date.");

        }
        else if (endDate.isBefore(startDate))
        {
            throw new IllegalArgumentException(
                "End date cannot be before start date.");
        }
    }

    /**
     * Method used to edit information about a customer in the database.
     * Calls the editGuest method in the dataBaseAdapter passing along the arguments given.
     * @param bookingID The Booking ID of the booking that was selected client-side.
     * @param fName The value that the first name should be changed to as a String.
     * @param lName The value that the last name should be changed to as a String.
     * @param email The value that the email should be changed to as a String.
     * @param phoneNr The phone number of the customer.
     * @throws SQLException
     */
    @Override
    public void editGuest(int bookingID, String fName, String lName, String email, int phoneNr) throws SQLException {

      checkGuestBeforeEditing("dummyUsername", fName, lName, email, phoneNr);
        dataBaseAdapter.editGuest(bookingID, fName, lName, email, phoneNr);
    }

    /**
     * Method used for getting an ArrayList containing all guest in the system.
     * Calls the getAllGuests method in dataBaseAdapter.
     * @return An ArrayList containing all the guests currently in the system.
     * @throws SQLException
     */
    @Override public ArrayList<Guest> getAllGuests() throws SQLException
    {
        return dataBaseAdapter.getAllGuests();
    }

    /**
     * Method used for registering the guest in the system.
     * Calls the register() method in dataBaseAdapter.
     * @param fName
     * @param lName
     * @param email
     * @param phoneNr
     * @param username
     * @param password
     * @throws SQLException
     */
    @Override
    public void register(String fName, String lName, String email, int phoneNr, String username, String password) throws SQLException {
        Guest guest = new Guest(fName, lName, email, phoneNr, username, password);
        dataBaseAdapter.register(guest);

    }

    /**
     * Method used to check if there is any guest having this username and password in the database.
     * If yes, this method gives the access to the personal account of the guest.
     * Calls login() method in the dataBaseAdapter.
     * @param username
     * @param password
     * @throws SQLException
     */
    @Override
    public void login(String username, String password) throws SQLException {
        dataBaseAdapter.login(username,password);
    }

    /**
     * Method used to get all bookings of a guest that is logged in the system.
     * Calls getBookingsWhenLoggedIn() method in dataBaseAdapter.
     * @param username
     * @return bookingList with the list of bookings made by the guest.
     * @throws SQLException
     */
    @Override
    public RoomBookingList getBookingsWhenLoggedIn(String username) throws SQLException {
       bookingList.setAllBookings(dataBaseAdapter.getBookingsWhenLoggedIn(username));
       return bookingList;

    }

    /**
     * Method used to make a booking, when a guest is logged in the system.
     * Calls bookARoomWhenLoggedIn() method in dataBaseAdapter.
     * @param roomID
     * @param startDate
     * @param endDate
     * @param username
     * @throws SQLException
     */
    @Override
    public void bookARoomWhenLoggedIn(String roomID, LocalDate startDate, LocalDate endDate, String username) throws SQLException {
        checkForLegalDates(startDate, endDate);
        // No checking for Room object, because when we choose room, we check if there's available rooms -
        // and if there is it means room value already is legal
        RoomBooking roomBooking = new RoomBooking(startDate,endDate,roomID,username);
        dataBaseAdapter.bookARoomWhenLoggedIn(roomBooking);
    }

    /**
     * Method used for clearing all data in the database. Primarily intended to be used for testing purposes.
     *
     */
    @Override public void clearDatabase() throws SQLException
    {
        dataBaseAdapter.clearDatabase();
    }

    /**
     * Method used to edit a guest with a specific username.
     * Calls editGuestWithUsername method in dataBaseAdapter.
     * @param username
     * @param name
     * @param lastName
     * @param email
     * @param phoneNr
     * @throws SQLException
     */
    @Override
    public void editGuestWithUsername(String username, String name, String lastName, String email, int phoneNr) throws IllegalArgumentException, SQLException {
            checkGuestBeforeEditing(username,name, lastName,email,phoneNr);
        dataBaseAdapter.editGuestWithUsername(username,  name,  lastName,  email, phoneNr);
    }

    private void checkGuestBeforeEditing(String username, String name, String lastName, String email, int phoneNr) {

        try
        {
            Guest guest = new Guest(username, name, lastName, email, phoneNr);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Method used to get details about a guest with a specific username.
     * Calls getGuestByUsername method in dataBaseAdapter.
     * @param username
     * @return
     * @throws SQLException
     */
    @Override
    public GuestTransfer getGuestByUsername(String username) throws SQLException {
        return dataBaseAdapter.getGuestByUsername(username);
    }

    @Override
    public ArrayList<Room> availableConferenceRooms(LocalDate startDate, LocalDate endDate) throws SQLException {
        checkForLegalDates(startDate, endDate);
        return dataBaseAdapter.availableConferenceRooms(startDate,endDate);
    }
}
