package model;

/**
 * A class extending RoomBookingState, and representing the In Progress state of a booking.
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class RoomBookingInProgressState extends RoomBookingState
{
  /**
   * Implementation of empty method in superclass, setting the state to archived.
   * @param roomBooking the RoomBooking to perform the operation on.
   */
  @Override void setState(RoomBooking roomBooking)
  {
    roomBooking.setState(new RoomBookingArchivedState());
  }

  /**
   * A method returning the state of the booking as a String.
   * @return In progress as a string.
   */
  @Override String getState()
  {
    return "In progress";
  }
}
