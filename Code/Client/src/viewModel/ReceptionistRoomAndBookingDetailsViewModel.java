package viewModel;

import javafx.beans.property.*;
import mediator.RoomBookingTransfer;
import model.Model;
import model.Room;
import model.RoomType;
import viewModel.Helpers.SimpleBookingViewModel;
import viewModel.Helpers.TemporaryInformation;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class meant for creating functionality for RooDetailsForReceptionistController
 *
 * @author Group 5
 * @version 13-05-2022
 */
public class ReceptionistRoomAndBookingDetailsViewModel
{
  private Model model;

  private StringProperty type; // String type

  private StringProperty bookingId;
  private StringProperty status;

  private StringProperty roomNumber;
  private StringProperty nrOfBeds;

  private StringProperty dailyPrice;

  private ArrayList<RoomType> types;

  private ObjectProperty<LocalDate> startDatePicker;
  private ObjectProperty<LocalDate> endDatePicker;

  private SimpleStringProperty errorLabel;


  /**
   * Constructor initializing instance variables
   *
   * @param model model interface
   */
  public ReceptionistRoomAndBookingDetailsViewModel(Model model)
  {
    this.model = model;

    bookingId = new SimpleStringProperty();
    status = new SimpleStringProperty();
    roomNumber = new SimpleStringProperty();
    type = new SimpleStringProperty();
    dailyPrice = new SimpleStringProperty();
    startDatePicker = new SimpleObjectProperty<>();
    endDatePicker = new SimpleObjectProperty<>();
    nrOfBeds = new SimpleStringProperty();
    errorLabel = new SimpleStringProperty("");
  }

  /**
   * A getter returning the start date of the date picker
   *
   * @return startDatePicket
   */
  public ObjectProperty<LocalDate> getStartDatePicker()
  {
    return startDatePicker;
  }

  /**
   * A getter returning the end date of the date picker
   *
   * @return endDatePicker
   */
  public ObjectProperty<LocalDate> getEndDatePicker()
  {
    return endDatePicker;
  }

  /**
   * A getter returning the error label encapsulated in String property object
   *
   * @return errorLabel
   */
  public StringProperty getErrorLabelProperty()
  {
    return errorLabel;
  }

  /**
   * A setter to give a value to a error label
   *
   * @param errorLabel
   */
  public void setErrorLabel(String errorLabel)
  {
    this.errorLabel.set(errorLabel);
  }

  /**
   * A getter that returns a booking ID.
   *
   * @return bookingId
   */
  public StringProperty getBookingId()
  {
    return bookingId;
  }

  /**
   * A getter that return number of beds
   *
   * @return nrOfBeds
   */
  public StringProperty getNrOfBeds()
  {
    return nrOfBeds;
  }

  /**
   * A getter that return the status of the booking
   *
   * @return status
   */
  public StringProperty getStatus()
  {
    return status;
  }

  /**
   * getter that returns the selected room number
   *
   * @return roomNumbers
   */
  public StringProperty getRoomNumber()
  {
    return roomNumber;
  }

  /**
   * Getter for room type
   * @return type
   */
  public StringProperty getType()
  {
    return type;
  }

  /**
   * A method that return void and calls the editBooking method from the model in order to edit the chosen booking.
   */
  public void saveBookingChanged()
  {
    RoomBookingTransfer roomBookingTransfer =
        model.editBooking(Integer.parseInt(bookingId.get()), startDatePicker.get(),
            endDatePicker.get(), roomNumber.get());

    if (roomBookingTransfer.getMessage() == null)
    {
      errorLabel.setValue("");
    }
    else
    {
      throw new IllegalStateException(roomBookingTransfer.getMessage());
    }
  }

  /**
   * A method that calls a removeBooking method from the model in order to remove a chosen room.
   */
  public void removeBooking()
  {
    model.removeBooking(Integer.parseInt(bookingId.get()));
  }

  /**
   * A setter method used to set the room booking details in the room booking details window for receptionist
   * @param selectedBooking selected booking.
   */
  public void setRoomBookingDetails(SimpleBookingViewModel selectedBooking)
  {
    Room room = model.getRoom(selectedBooking.roomIdProperty().get()).getRoom();
    roomNumber.setValue(room.getRoomId());
    nrOfBeds.setValue(String.valueOf(room.getNumberOfBeds()));
    type.setValue(room.getRoomType().toString());
    dailyPrice.setValue(String.valueOf(room.getPrice()));
    bookingId.setValue(
        String.valueOf(selectedBooking.bookingIdProperty().get()));
    status.setValue(selectedBooking.bookingStateProperty().get());
    startDatePicker.setValue(selectedBooking.getStartDate());
    endDatePicker.setValue(selectedBooking.getEndDate());
  }

  public StringProperty getDailyPrice()
  {
    return dailyPrice;
  }
}
