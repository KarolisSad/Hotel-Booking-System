package viewModel;

import javafx.beans.property.*;
import mediator.RoomBookingTransfer;
import mediator.RoomBookingTransferObject;
import model.Guest;

import java.time.LocalDate;
import java.util.Date;

/**
 * A class that is used to store information about the rooms.
 * This information is used in tables.
 *
 * @author Group 5
 * @version 04/18/2022
 */
public class SimpleBookingViewModel
{
  private ObjectProperty<LocalDate> startDateProperty;
  private ObjectProperty<LocalDate> endDateProperty;
  private StringProperty roomIdProperty;
  private IntegerProperty guestIdProperty;
  private IntegerProperty bookingIdProperty;
  private StringProperty bookingStateProperty;

  private ObjectProperty<Guest> guestProperty;

  /**
   * A SimpleBookingViewModel constructor initializing all instance variables.
   */
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

  /**
   * A getter method for startDateProperty.
   * @return ObjectProperty<LocalDate> object.
   */
  public ObjectProperty<LocalDate> startDateProperty()
  {
    return startDateProperty;
  }

  /**
   * A getter method for startDate.
   * @return LocalDate object.
   */
  public LocalDate getStartDate()
  {
    return startDateProperty.get();
  }

  /**
   * A getter method for endDateProperty.
   * @return ObjectProperty<LocalDate> object.
   */
  public ObjectProperty<LocalDate> endDateProperty()
  {
    return endDateProperty;
  }

  /**
   * A getter method for endDate.
   * @return LocalDate object.
   */
  public LocalDate getEndDate()
  {
    return endDateProperty.get();
  }

  /**
   * A getter method for roomIdProperty.
   * @return StringProperty object.
   */
  public StringProperty roomIdProperty()
  {
    return roomIdProperty;
  }

  /**
   * A getter method for guestIdProperty.
   * @return IntegerProperty object.
   */
  public IntegerProperty guestIdProperty()
  {
    return guestIdProperty;
  }

  /**
   * A getter method for guestProperty.
   * @return ObjectProperty<Guest> object.
   */
  public ObjectProperty<Guest> guestProperty()
  {
    return guestProperty;
  }

  /**
   * A getter method for bookingIdProperty.
   * @return IntegerProperty object.
   */
  public IntegerProperty bookingIdProperty()
  {
    return bookingIdProperty;
  }

  /**
   * A getter method for bookingStateProperty.
   * @return StringProperty object.
   */
  public StringProperty bookingStateProperty()
  {
    return bookingStateProperty;
  }
}
