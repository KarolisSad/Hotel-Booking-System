package model;

public class RoomBookingCancelledState extends RoomBookingState
{
  @Override String getState()
  {
    return "Cancelled";
  }
}
