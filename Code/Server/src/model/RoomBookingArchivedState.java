package model;

/**
 * A class extending RoomBookingState, and representing the Archived state of a booking.
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class RoomBookingArchivedState extends RoomBookingState
{

  /**
   * A method returning the state of the booking as a String.
   * @return Archived as a string.
   */
  @Override String getState()
  {
    return "Archived";
  }
}
