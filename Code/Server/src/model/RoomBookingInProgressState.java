package model;

public class RoomBookingInProgressState extends RoomBookingState
{
  @Override void setState(RoomBooking roomBooking)
  {
    roomBooking.setState(new RoomBookingArchivedState());
  }

  @Override String getState()
  {
    return "In progress";
  }
}
