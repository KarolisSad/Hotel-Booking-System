package model;

import mediator.RoomBookingTransferObject;

import java.sql.SQLException;
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
    allBookings = new ArrayList<>();
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
   * Method used for updating the bookingList.
   * @param bookingList a specific ArrayList to be added as the new list.
   */
  public void setAllBookings(ArrayList<RoomBooking> bookingList)
  {
    allBookings.clear();
    allBookings.addAll(bookingList);
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
   * A method looping through all elements in the arrayList, and returning the RoomBooking whose ID matches the argument.
   * @param id The Booking ID of the booking to be found.
   * @return The booking with the specified ID, or Null if no such Booking exists.
   */
  public RoomBooking getBookingById(int id)
  {
    for (int i = 0; i < allBookings.size(); i++)
    {
      if (allBookings.get(i).getBookingID() == id)
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
    return null;
  }

  /**
   * A method used for converting the ArrayList of RoomBookings to a new ArrayList of RoomBookingTransferObjects, as you cannot write abstract classes to jSon.
   * @return An ArrayList of RoomBookingTransferObjects identical to the RoomBookings in the RoomBookingList, but with the state stored as a string.
   */
  public ArrayList<RoomBookingTransferObject> getConvertedList()
  {
    ArrayList<RoomBookingTransferObject> allBookingsConverted = new ArrayList<>();
    for (int i = 0; i < allBookings.size(); i++)
    {
      RoomBooking b = allBookings.get(i);
      allBookingsConverted.add(
          new RoomBookingTransferObject(b.getStartDate(), b.getEndDate(),
              b.getRoom().getRoomId(), b.getGuest(),
              b.getBookingID(), b.getState()));
    }

    return allBookingsConverted;
  }

  /**
   * A method used to process a booking in the system.
   *    Eg. To change the state from Booked -> In Progress -> Archived.
   * @param id the Booking ID of the booking to process.
   */
  public void processBooking(int id)
  {
    getBookingById(id).processBooking();
  }


  //TODO this should probably be changed when we start implementing cancellation. maybe it should just remove the booking from the list (and database)
  /**
   * A method used to cancel a booking, Eg. setting it's state to cancelled.
   * @param id the Booking ID of the booking to cancel.
   */
  public void cancelBooking(int id)
  {
    getBookingById(id).cancelBooking();
  }

}

