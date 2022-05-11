package model;

public abstract class RoomBookingState
{
  void setState(RoomBooking roomBooking)
  {

  }
  void cancelBooking(RoomBooking roomBooking)
  {
    throw new IllegalStateException("You can only cancel a booking that is not yet in progress. Current state of booking is: " + getState());
  }
  abstract String getState();
}
