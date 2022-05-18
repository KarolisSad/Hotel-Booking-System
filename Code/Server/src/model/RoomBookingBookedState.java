package model;

/**
 * A class extending RoomBookingState, and representing the Booked state of a booking.
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class RoomBookingBookedState extends RoomBookingState
{
  /**
   * Implementation of empty method in superclass, setting the state to In Progress.
   * @param roomBooking the RoomBooking to perform the operation on.
   */
  @Override void setState(RoomBooking roomBooking)
  {
    roomBooking.setState(new RoomBookingInProgressState());
  }

  /**
   * Method overwriting the same method in the super-class, and cancelling the booking by setting the state to cancelled.
   * @param roomBooking the RoomBooking to perform the operation on.
   */
  @Override void cancelBooking(RoomBooking roomBooking)
  {
    roomBooking.setState(new RoomBookingCancelledState());
  }

  /**
   * A method returning the state of the booking as a String.
   * @return Booked as a string.
   */
  @Override String getState()
  {
    return "Booked";
  }
}
