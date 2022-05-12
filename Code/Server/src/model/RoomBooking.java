package model;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * A class that creates a room booking.
 *
 * @author Group 5
 * @version 04/05/2022
 */
public class RoomBooking
{
  private LocalDate startDate;
  private LocalDate endDate;
  private Room room;
  private Guest guest;

  //todo added this
  private int bookingID;
  private RoomBookingState state;
  private HotelPersistence databaseAdapter;



  /**
   * A four-argument constructor
   * A constructor meant for initializing instance variables using set-methods.
   * It makes a copy of the guest object.
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
  }

  //TODO COMMENT
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
   * Method setting the start and end date variables to the values passed as argument.
   * @param startDate startDate
   * @param endDate endDate
   */
  public void setStartAndEndDate(LocalDate startDate, LocalDate endDate)
  {
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

  public void setState(RoomBookingState roomBookingState)
  {
    this.state = roomBookingState;
  }

  public int getBookingID()
  {
    return bookingID;
  }

  public void processBooking()
  {
    state.setState(this);

  }

  public void cancelBooking()
  {
    state.cancelBooking(this);
  }

  public String getState()
  {
    return state.getState();
  }

  /**
   * A method is meant for getting room booking information.
   *
   * @return Organized output of room booking
   */
  @Override public String toString()
  {
    return "RoomBooking{ ID: " + bookingID + "startDate=" + startDate + ", endDate=" + endDate
        + ", room=" + room + ", guest=" + guest + ", state=" + getState() +'}';
  }


  // TODO COMMENT
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