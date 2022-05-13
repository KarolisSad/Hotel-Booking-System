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
  private String state;



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
    this.bookingID = bookingID;
    this.state = state;
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


  /**
   * Method returning the Booking ID
   * @return The booking ID associated with this Booking.
   */
  public int getBookingID()
  {
    return bookingID;
  }


}