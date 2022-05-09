package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class that is used to store room bookings as well as manipulating the list.
 *
 * @author Group 5
 * @version 04/05/2022
 */
public class RoomBookingList
{
  private ArrayList<RoomBooking> allBookings;

  /**
   * A constructor meant for initializing instance variable as a new array list
   * that stores all bookings.
   */
  public RoomBookingList()
  {
    this.allBookings = new ArrayList<>();
  }

  /**
   * A method that returns all bookings.
   *
   * @return allBookings
   */
  public ArrayList<RoomBooking> getAllBookings()
  {
    return allBookings;
  }

  /**
   * A method meant for added a new room booking to the list
   * and printing out that it was added.
   *
   * @param booking the booking that is being added
   * @throws NullPointerException if argument given is null.
   */
  public void addBooking(RoomBooking booking)
  {
    if (booking == null)
    {
     throw new NullPointerException("No booking passed as argument.");
    }

    else
    {
      allBookings.add(booking);
      System.out.println("new booking was added: " + booking);
    }

  }

  /**
   * A method that is used for calling a specific booking by index.
   *
   * @param index specified booking number
   * @return specified booking
   */
  public RoomBooking getBooking(int index)
  {
    for (int i = 0; i < allBookings.size(); i++)
    {
      if (allBookings.get(i).equals(allBookings.get(index)))
      {
        return allBookings.get(i);
      }
    }
    return null;
  }

  /**
   * A method that returns how many rooms are booked.
   *
   * @return size of AllBooking array list.
   */
  public int bookedRoomListSize()
  {
    return allBookings.size();
  }

  /**
   * A method that is meant for getting a list of rooms in the span of given dates.
   *
   * @param startDate start date
   * @param endDate   end date
   * @return rooms
   */
  public ArrayList<Room> getBookedRoomsBy(LocalDate startDate,
      LocalDate endDate)
  {
    ArrayList<Room> rooms = new ArrayList<>();
    for (int i = 0; i < allBookings.size(); i++)
    {
      if (allBookings.get(i).getStartDate().isBefore(endDate)
          && allBookings.get(i).getStartDate().isAfter(startDate)
          || allBookings.get(i).getEndDate().isBefore(endDate)
          && allBookings.get(i).getEndDate().isAfter(startDate) || (
          startDate.isBefore(allBookings.get(i).getEndDate())
              && startDate.isAfter(allBookings.get(i).getStartDate())
              || endDate.isBefore(allBookings.get(i).getEndDate())
              && endDate.isAfter(allBookings.get(i).getStartDate()))
          || startDate.equals(allBookings.get(i).getStartDate())
          || endDate.equals(allBookings.get(i).getEndDate()))
      {
        rooms.add(allBookings.get(i).getRoom());
      }
    }
    return rooms;
  }
}
