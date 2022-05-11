package model;

import java.time.LocalDate;

/**
 * A class that creates a room booking.
 *
 * @author Group 5
 * @version 04/05/2022
 */
public class RoomBooking
{
  private String startDate;
  private String endDate;
  private Room room;
  private Guest guest;

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
  public RoomBooking(String startDate, String endDate, Room room,
                     Guest guest)
  {
    this.startDate = startDate;
    this.endDate = endDate;
    setRoom(room);
    setGuest(guest);
  }


  /**
   * Method setting the start and end date variables to the values passed as argument.
   * @param startDate startDate
   * @param endDate endDate
   *
   * @throws IllegalArgumentException if any of the following are true:
   * - Start date is before current date.
   * - End date is the same as start date
   * - End date is before start date.
   */

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
  public String getEndDate() {
    return endDate;
  }

  /**
   * A method meant for getting a start date.
   *
   * @return startDate
   */
  public String getStartDate() {
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
   * A method is meant for getting room booking information.
   *
   * @return Organized output of room booking
   */
  @Override public String toString()
  {
    return "RoomBooking{" + "startDate=" + startDate + ", endDate=" + endDate
            + ", room=" + room + ", guest=" + guest + '}';
  }
}
