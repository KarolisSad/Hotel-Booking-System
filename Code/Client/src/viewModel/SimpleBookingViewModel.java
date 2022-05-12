package viewModel;

import javafx.beans.property.*;
import mediator.RoomBookingTransfer;
import mediator.RoomBookingTransferObject;

import java.time.LocalDate;
import java.util.Date;

public class SimpleBookingViewModel
{
  private ObjectProperty<LocalDate> startDateProperty;
  private ObjectProperty<LocalDate> endDateProperty;
  private StringProperty roomIdProperty;
  private IntegerProperty guestIdProperty;
  private IntegerProperty bookingIdProperty;
  private StringProperty bookingStateProperty;

  public SimpleBookingViewModel(RoomBookingTransferObject roomBookingTransfer)
  {
    startDateProperty = new SimpleObjectProperty<>(
        roomBookingTransfer.getStartDate());
    endDateProperty = new SimpleObjectProperty<>(
        roomBookingTransfer.getEndDate());
    roomIdProperty = new SimpleStringProperty(roomBookingTransfer.getRoomID());
    guestIdProperty = new SimpleIntegerProperty(
        roomBookingTransfer.getGuestID());
    bookingIdProperty = new SimpleIntegerProperty(
        roomBookingTransfer.getBookingID());
    bookingStateProperty = new SimpleStringProperty(
        roomBookingTransfer.getBookingState());
  }

  public ObjectProperty<LocalDate> startDateProperty()
  {
    return startDateProperty;
  }

  public LocalDate getStartDate()
  {
    return startDateProperty.get();
  }

  public ObjectProperty<LocalDate> endDateProperty()
  {
    return endDateProperty;
  }

  public LocalDate getEndDate()
  {
    return endDateProperty.get();
  }

  public StringProperty roomIdProperty()
  {
    return roomIdProperty;
  }

  public IntegerProperty guestIdProperty()
  {
    return guestIdProperty;
  }

  public IntegerProperty bookingIdProperty()
  {
    return bookingIdProperty;
  }

  public StringProperty bookingStateProperty()
  {
    return bookingStateProperty;
  }
}
