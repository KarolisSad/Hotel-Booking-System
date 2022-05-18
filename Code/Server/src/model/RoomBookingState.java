package model;

/**
 * An abstract class representing the different states of a RoomBooking
 *
 * @author Group 5
 * @version 12/05/2022
 */
public abstract class RoomBookingState
{
  /**
   * Method to be overwritten by relevant subclasses extending this.
   * Method implemented as empty, in order to ensure that cancelled and archived bookings cannot have their states altered further.
   *
   * @param roomBooking the RoomBooking to perform the operation on.
   */
  void setState(RoomBooking roomBooking)
  {

  }

  /**
   * Method to be overwritten in relevant subclasses extending this.
   *
   * @param roomBooking the RoomBooking to perform the operation on.
   * @throws IllegalStateException if calling this on a RoomBooking whose subclass is not overwriting.
   */
  void cancelBooking(RoomBooking roomBooking)
  {
    throw new IllegalStateException(
        "You can only cancel a booking that is not yet in progress. Current state of booking is: "
            + getState());
  }

  /**
   * Abstract method to be implemented by subclasses extending this.
   *
   * @return String representing the state of a RoomBooking.
   */
  abstract String getState();
}
