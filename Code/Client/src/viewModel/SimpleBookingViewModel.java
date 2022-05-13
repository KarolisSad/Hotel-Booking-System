package viewModel;

import javafx.beans.property.*;
import mediator.RoomBookingTransfer;
import mediator.RoomBookingTransferObject;
import model.Guest;

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

  private ObjectProperty<Guest> guestProperty;

  public SimpleBookingViewModel(RoomBookingTransferObject roomBookingTransfer)
  {
    startDateProperty = new SimpleObjectProperty<>(
        roomBookingTransfer.getStartDate());
    endDateProperty = new SimpleObjectProperty<>(
        roomBookingTransfer.getEndDate());
    roomIdProperty = new SimpleStringProperty(roomBookingTransfer.getRoomID());
    guestProperty = new SimpleObjectProperty<>(roomBookingTransfer.getGuest());
    guestIdProperty = new SimpleIntegerProperty(
        guestProperty.get().getPhoneNr());
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

  public ObjectProperty<Guest> guestProperty()
  {
    return guestProperty;
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
