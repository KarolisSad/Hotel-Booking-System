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
 * @version 04/05/2022
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
    public RoomTransfer addRoom(String roomId, RoomType type, int nrBeds) {
        return hotelClient.addRoom(roomId, type, nrBeds);
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
     * @param status    status of the booking (In progress or Booked)
     * @return RoomBookingTransfer object
     */
    @Override
    public RoomBookingTransfer editBooking(int bookingId,
                                           LocalDate startDate, LocalDate endDate, String roomid, String status) {
        return hotelClient.editBooking(bookingId, startDate, endDate, roomid, status);
    }

    @Override
    public RoomBookingTransfer removeBooking(int bookingId, int guestID) {
        return hotelClient.removeBooking(bookingId, guestID);
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
    public RoomTransfer editRoomInfo(String roomId, RoomType type, int nrBeds) {
        return hotelClient.editRoomInfo(roomId, type, nrBeds);
    }

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

    @Override
    public RoomBookingTransfer cancelBooking(int bookingNumber) {
        return null;
    }

    @Override
    public GuestTransfer getAllGuests() {
        return hotelClient.getAllGuests();
    }

    @Override
    public RoomBookingTransfer getABooking(int bookingID) {
        return hotelClient.getABooking(bookingID);
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
