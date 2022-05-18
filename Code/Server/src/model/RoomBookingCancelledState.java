package model;

/**
 * A class extending RoomBookingState, and representing the Cancelled state of a booking.
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class RoomBookingCancelledState extends RoomBookingState
{
  /**
   * A method returning the state of the booking as a String.
   * @return Cancelled as a string.
   */
  @Override String getState()
  {
    return "Cancelled";
  }
}
