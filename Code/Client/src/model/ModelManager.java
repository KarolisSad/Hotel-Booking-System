package model;

import mediator.GuestTransfer;
import mediator.HotelClient;
import mediator.RoomBookingTransfer;
import mediator.RoomTransfer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.time.LocalDate;

/**
 * A class that implements the Model interface and manages the bookings.
 *
 * @author Group 5
 * @version 04/23/2022
 */

public class ModelManager implements Model {
    private PropertyChangeSupport property;
    private HotelClient hotelClient;

    /**
     * A constructor that is meant to initialize
     * the instance variables as a new array lists
     * that will store a list of all rooms and a list of booked rooms.
     */
    public ModelManager() {
        try {
            hotelClient = new HotelClient(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        property = new PropertyChangeSupport(this);
    }


    /**
     * A method that returns all available rooms by selected date
     *
     * @param startDate start date
     * @param endDate   end date
     * @return available rooms
     */
    @Override
    public RoomTransfer availableRooms(LocalDate startDate, LocalDate endDate) {
        return hotelClient.availableRooms(startDate, endDate);
    }

    /**
     * Method creating a new Room object and adding it to a RoomList array.
     *
     * @param roomId the ID of the room to be added
     * @param type   the type of room to be added
     * @param nrBeds the number of beds in the room to be added
     * @return true if room is added successfully
     */
    @Override
    public RoomTransfer addRoom(String roomId, RoomType type, int nrBeds, int dailyPrice) {
        return hotelClient.addRoom(roomId, type, nrBeds, dailyPrice);
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
    @Override
    public RoomTransfer removeRoom(String roomId) {
        return hotelClient.removeRoom(roomId);
    }

    /**
     * Method returning the list of all rooms currently in the system.
     *
     * @return list of all rooms.
     */
    @Override
    public RoomTransfer getAllRooms() {
        return hotelClient.getAllRooms();
    }

    /**
     * A method editing a booking that is already in the system
     *
     * @param bookingId booking ID
     * @param startDate start date
     * @param endDate   end date
     * @param roomid    room number
     * @return RoomBookingTransfer object
     */
    @Override
    public RoomBookingTransfer editBooking(int bookingId,
                                           LocalDate startDate, LocalDate endDate, String roomid) {
        return hotelClient.editBooking(bookingId, startDate, endDate, roomid);
    }

    /**
     * A method calling the removeBooking() method from the HotelClient.
     *
     * @param bookingId booking ID
     * @return RoomBookingTransfer object
     */
    @Override
    public RoomBookingTransfer removeBooking(int bookingId) {
        return hotelClient.removeBooking(bookingId);
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
    @Override
    public RoomTransfer editRoomInfo(String roomId, RoomType type, int nrBeds, int dailyPrice) {
        return hotelClient.editRoomInfo(roomId, type, nrBeds, dailyPrice);
    }

    /**
     * A method calling the editGuest() method from the HotelClient.
     *
     * @param type type of the GuestTransferObject
     * @param bookingID booking identification
     * @param fName  guest's first name
     * @param lName    guest's last name
     * @param email    guest's email
     * @param phoneNr guest's phone number
     * @return GuestTransfer object
     */
    @Override
    public GuestTransfer editGuest(String type, int bookingID, String fName, String lName, String email, int phoneNr) {
        return hotelClient.editGuest(type, bookingID, fName, lName, email, phoneNr);
    }

    /**
     * Method for getting a RoomBookingTransfer object containing a list of all bookings
     *
     * @return RoomBookingTransfer object containing a list of all bookings
     */
    @Override
    public RoomBookingTransfer getAllBookings() {
        return hotelClient.getAllBookings();
    }

    /**
     * A method that is meant for booking a room.
     *
     * @param roomId    room number
     * @param startDate start date
     * @param endDate   end date
     * @param guest     guest
     * @return true if the room is booked and false if the room is not booked
     */
    @Override
    public RoomTransfer book(String roomId, LocalDate startDate, LocalDate endDate, Guest guest) {
        return hotelClient.book(roomId, startDate, endDate, guest);
    }

    /**
     * Method for getting a RoomBookingTransfer object containing a list of all bookings with a state of In progress
     *
     * @return RoomBookingTransfer object containing a list of all bookings with a state of In progress
     */
    @Override
    public RoomBookingTransfer getInProgressBookings() {
        return hotelClient.getInProgressBookings();
    }

    /**
     * Method for getting a RoomBookingTransfer object containing a list of all bookings with a state of Cancelled
     *
     * @return RoomBookingTransfer object containing a list of all bookings with a state of Cancelled
     */
    @Override
    public RoomBookingTransfer getCancelledBookings() {
        return hotelClient.getCancelledBookings();
    }

    /**
     * Method for getting a RoomBookingTransfer object containing a list of all bookings with a state of Booked
     *
     * @return RoomBookingTransfer object containing a list of all bookings with a state of Booked
     */
    @Override
    public RoomBookingTransfer getBookedBookings() {
        return hotelClient.getBookedBookings();
    }

    /**
     * Method for requesting a change in RoomBookingState from the server
     *
     * @param bookingNumber The bookingNumber of the booking to be changed
     * @return RoomBookingTransfer object containing either a success message, or an exception message.
     */
    @Override
    public RoomBookingTransfer processBooking(int bookingNumber) {
        return hotelClient.processBooking(bookingNumber);
    }


    /**
     * Method for requesting a canceling of the booking from the server
     *
     * @param bookingNumber The bookingNumber of the booking to be changed
     * @return RoomBookingTransfer object containing either a success message, or an exception message.
     */
    @Override
    public RoomBookingTransfer cancelBooking(int bookingNumber) {
        return hotelClient.cancelBooking(bookingNumber);
    }

    /**
     * Method for requesting getting a list of all guests from te server.
     *
     * @return GuestTransfer object containing either a success message with a list of guests
     * or an exception message.
     */
    @Override
    public GuestTransfer getAllGuests() {
        return hotelClient.getAllGuests();
    }

    /**
     * Method used for getting a specific room from the system.
     * Firstly, the room is received from the roomList and send to the client.
     *
     * @param roomId the room id of the room to be edited (The room id of the room is intentionally not possible to change with this method)
     * @return RoomTransfer object.
     */
    @Override
    public RoomTransfer getRoom(String roomId) {
        return hotelClient.getRoom(roomId);
    }

    /**
     * Method for requesting getting a specific booking from the server.
     *
     * @param bookingNumber The booking identification
     * @param phoneNumber The guest identification
     * @return RoomBookingTransfer object containing either a success message, or an exception message.
     */
    @Override
    public RoomBookingTransfer getBookingWithGuest(int bookingNumber,
                                                   int phoneNumber) {
        return hotelClient.getBookingWithGuest(bookingNumber, phoneNumber);
    }

    @Override
    public void logOutForGuest() {

    }

    /**
     * Method for requesting logging in from the server.
     *
     * @param username
     * @param password
     * @return RoomBookingTransfer object containing either a success message, or an exception message.
     */
    @Override
    public GuestTransfer login(String username, String password) throws InterruptedException {
        return hotelClient.login(username,password);
    }

    /**
     * Method for requesting registering in the server.
     *
     * @param username
     * @param password
     * @param fName  guest's first name
     * @param lName    guest's last name
     * @param email    guest's email
     * @param phoneNumber guest's phone number
     */
    @Override
    public GuestTransfer register(String fName, String lName, String email, int phoneNumber, String username, String password) {

        return hotelClient.register(fName,lName,email,phoneNumber,username,password);
    }

    /**
     * Method for requesting getting bookings, of a logged in guest, from the server.
     */
    @Override
    public RoomBookingTransfer getBookingsWhenLoggedIn() {
        return hotelClient.getBookingsWhenLoggedIn();
    }

    /**
     * Method for requesting making a new booking, for a logged in guest, from the server.
     */
    @Override
    public RoomBookingTransfer bookARoomWhenLoggedIn(String roomName, LocalDate startDate, LocalDate endDate) {
        return hotelClient.bookARoomWhenLoggedIn(roomName,startDate,endDate);
    }

    /**
     * Method for requesting changes of a guest with a specific username, from the server.
     *
     * @param editGuestWithUsername
     * @param username
     * @param getfName  guest's first name
     * @param getlName    guest's last name
     * @param email    guest's email
     * @param phoneNr guest's phone number
     */
    @Override
    public GuestTransfer editGuestWithUsername(String editGuestWithUsername, String username, String getfName, String getlName, String email, int phoneNr) {
        return hotelClient.editGuestWithUsername("editGuestWithUsername", username, getfName, getlName, email, phoneNr);
    }

    /**
     * Method for requesting getting information of a guest with a specific username, from the server.
     *
     * @param username
     */
    @Override
    public GuestTransfer getGuestByUsername(String username) {
        return hotelClient.getGuestByUsername(username);
    }

    @Override
    public RoomTransfer availableConferenceRooms(LocalDate startDate, LocalDate endDate) {
        return hotelClient.availableConferenceRooms(startDate, endDate);
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
