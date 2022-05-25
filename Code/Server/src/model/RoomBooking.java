package model;

import java.sql.SQLException;

import java.time.LocalDate;

/**
 * A class that creates a room booking.
 *
 * @author Group 5
 * @version 24/05/2022
 */
public class RoomBooking
{
  private LocalDate startDate;
  private LocalDate endDate;
  private Room room;
  private Guest guest;
  private String roomID;
  private String username;

  private int bookingID;
  private RoomBookingState state;

  /**
   * A four-argument constructor
   * A constructor meant for initializing instance variables using set-methods.
   * It makes a copy of the guest object.
   * This version of the constructor is used when creating a new RoomBooking.

   *
   * @param startDate start date
   * @param endDate   end date
   * @param room      room
   * @param guest     guest
   */
  public RoomBooking(LocalDate startDate, LocalDate endDate, Room room,
      Guest guest)
  {
    setStartAndEndDate(startDate, endDate);
    setRoom(room);
    setGuest(guest);
    bookingID = 0;
    state = new RoomBookingBookedState();
  }

  /**
   * A 5 argument constructor passing booking information.
   * Used when a guest is logged in the system and requests getting information about all his bookings.
   * @param bookingID
   * @param startDate
   * @param endDate
   * @param room
   * @param state
   */
  public RoomBooking(int bookingID,LocalDate startDate, LocalDate endDate, Room room, String state)
  {
    this.bookingID = bookingID;
    this.startDate = startDate;
    this.endDate = endDate;
    this.room = room;
    this.state = getStateFromString(state);
  }

  /**
   *  A 5 argument constructor passing booking information.
   *  Used when a guest is logged in the system and makes a new booking.
   * @param startDate
   * @param endDate
   * @param roomID
   * @param username
   */
  public RoomBooking(LocalDate startDate, LocalDate endDate, String roomID, String username)
  {
    setStartAndEndDate(startDate,endDate);
    setUsername(username);
    this.roomID = roomID;
    state = new RoomBookingBookedState();
  }

  /**
   * A 6 argument constructor meant to initialize all instance variables.
   * This version of the constructor is used when retrieving a RoomBooking object from the database.
   *
   * @param startDate Start date
   * @param endDate End date
   * @param room The room
   * @param guest Guest
   * @param state The state of the booking
   * @param bookingID The booking ID of the booking.
   */
  public RoomBooking(LocalDate startDate, LocalDate endDate, Room room,
      Guest guest, String state, int bookingID)
  {
    setStartAndEndDate(startDate, endDate);
    setRoom(room);
    setGuest(guest);
    this.state = getStateFromString(state);
    this.bookingID = bookingID;

  }

  /**
   * A method setting a username in the system.
   * @throws IllegalArgumentException if the username is null.
   * @param username
   */
  public void setUsername(String username) {
    if (username == null)
    {
      throw new IllegalArgumentException("User should not be empty");
    }
    this.username = username;
  }


  /**
   * Method setting the start and end date variables to the values passed as argument.
   * @param startDate startDate
   * @param endDate endDate
   */
  public void setStartAndEndDate(LocalDate startDate, LocalDate endDate)
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
    this.startDate = startDate;
    this.endDate = endDate;
  }


  /**
   * Method setting the room variable to a copy of the room passed as an argument.
   * @param room the room to be set.
   *
   * @throws IllegalArgumentException if null is passed as argument.
   */
  public void setRoom(Room room)
  {
    if (room == null)
    {
      throw new NullPointerException("Room should not be null");
    }

    this.room = room.copy();
  }

  /**
   * Method used for setting the guest using the guest passed as an argument.
   * @param guest Guest
   * @throws IllegalArgumentException if argument is null.
   */
  public void setGuest(Guest guest)
  {
    if (guest == null)
    {
      throw new NullPointerException("Guest should not be null.");
    }

    this.guest = guest.copy();
  }


  /**
   * A method meant for getting an end date.
   *
   * @return endDate
   */
  public LocalDate getEndDate() {

    return endDate;
  }

  /**
   * A method meant for getting a start date.
   *
   * @return startDate
   */
  public LocalDate getStartDate() {

    return startDate;
  }

  /**
   * A method meant for getting a room.
   *
   * @return room
   */
  public Room getRoom()
  {
    return room;
  }

  /**
   * Method meant for getting the current guest.
   * @return guest
   */
  public Guest getGuest()
  {
    return guest;
  }

  /**
   * A method used for changing the state of the booking by using the RoomBookingState and its subclasses.
   * @param roomBookingState The state to be set.
   */
  public void setState(RoomBookingState roomBookingState)
  {
    this.state = roomBookingState;
  }

  /**
   * Method returning the Booking ID
   * @return The booking ID associated with this Booking.
   */
  public int getBookingID()
  {
    return bookingID;
  }

  /**
   * Method used to process the Booking.
   *    Eg. Changing the state in accordance with the RoomBookingState-oclass and its subclasses.
   */
  public void processBooking()
  {
    state.setState(this);
  }

  /**
   * Method used to cancel the current booking, by setting its state to Cancelled.
   *
   */
  public void cancelBooking()
  {
    state.cancelBooking(this);
  }

  /**
   * Method returning the state as a string
   * @return State
   */
  public String getState()
  {
    return state.getState();
  }

  @Override
  public String toString() {
    return "<RoomBooking> BookingID: " + bookingID + ", StartDate: " + startDate + ", EndDate: " + endDate + ", Room: " + room + ", Guest: " + guest + ", State: " + state.getState();
  }

  /**

   * A method is meant for getting room booking information.
   *
   * @return Organized output of room booking
   */


  public String getRoomID() {
    return roomID;
  }

  /**
   * Method getting the username.
   *
   * @return String object called username.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Method used to convert a String version of the RoomBookingState to an actual implementation of this.
   * This method is used when retrieving a RoomBooking from a client, as these are sent with the State being a String-value.
   * @param state The string to convert.
   * @return The RoomBookingState corresponding to the string value passed as argument.
   */
  private RoomBookingState getStateFromString(String state)
  {
    switch (state)
    {
      case "Archived": return new RoomBookingArchivedState();
      case "Booked": return new RoomBookingBookedState();
      case "Cancelled": return new RoomBookingCancelledState();
      case "In progress": return new RoomBookingInProgressState();
      default: return null;
    }
  }
}

