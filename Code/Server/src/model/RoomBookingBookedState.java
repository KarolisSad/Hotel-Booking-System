package model;

public class RoomBookingBookedState extends RoomBookingState
{
  @Override void setState(RoomBooking roomBooking)
  {
    roomBooking.setState(new RoomBookingInProgressState());
  }

  @Override void cancelBooking(RoomBooking roomBooking)
  {
    roomBooking.setState(new RoomBookingCancelledState());
  }

  @Override String getState()
  {
    return "Booked";
  }
}
