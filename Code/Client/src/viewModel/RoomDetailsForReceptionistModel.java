package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.RoomTransfer;
import model.Model;
import model.RoomList;
import model.RoomType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A class for crearting functionality for RooDetailsForReceptionistController
 *
 * @author Group 5
 * @version 13-05-2022
 */
public class RoomDetailsForReceptionistModel
{
  private Model model;

  private RoomType type; // String type

  private StringProperty bookingId;
  private StringProperty status;

  private ObservableList<String> roomNumbers;
  private IntegerProperty nrOfBeds;

  private ArrayList<RoomType> types;

  private ObjectProperty<LocalDate> startDatePicker;
  private ObjectProperty<LocalDate> endDatePicker;

  private SimpleStringProperty errorLabel;

  private TemporaryInformation temp;


  /**
   * Constructor initializing instance variables
   * @param model model interface
   */
  public RoomDetailsForReceptionistModel(Model model, TemporaryInformation temp)
  {
    this.model = model;

    bookingId = new SimpleStringProperty(temp.getSelectedBooking());
    status = new SimpleStringProperty(temp.getSelectedBooking());
    roomNumbers = FXCollections.observableArrayList();
    types.addAll(List.of(RoomType.values()));
    startDatePicker = new SimpleObjectProperty<>(temp.getSelectedBooking());
    endDatePicker = new SimpleObjectProperty<>(temp.getSelectedBooking());
    nrOfBeds = new SimpleIntegerProperty(temp.getSelectedBooking());
    errorLabel = new SimpleStringProperty("");
    this.temp = temp;
  }

  /**
   * A getter returning the start date of the date picker
   * @return startDatePicket
   */
  public ObjectProperty<LocalDate> getStartDatePicker()
  {
    return startDatePicker;
  }

  /**
   * A getter returning the end date of the date picker
   * @return endDatePicker
   */
  public ObjectProperty<LocalDate> getEndDatePicker()
  {
    return endDatePicker;
  }

  /**
   * A getter returning the error label encapsulated in String property object
   * @return errorLabel
   */
  public StringProperty getErrorLabelProperty()
  {
    return errorLabel;
  }

  /**
   * A setter to give a value to a error label
   * @param errorLabel
   */
  public void setErrorLabel(String errorLabel)
  {
    this.errorLabel.set(errorLabel);
  }

  /**
   * A getter that returns a booking ID.
   * @return bookingId
   */
  public StringProperty getBookingId()
  {
    return bookingId;
  }

  /**
   * A getter that return number of beds
   * @return nrOfBeds
   */
  public IntegerProperty getNrOfBeds()
  {
    return nrOfBeds;
  }

  /**
   * A getter that return the status of the booking
   * @return status
   */
  public StringProperty getStatus()
  {
    return status;
  }

  /**
   * getter that return available room numbers for chosen dates
   * @return roomNumbers
   */
  public ObservableList getAvailableRoomNumbers()
  {
    model.availableRooms(temp.getSelectedBooking().getStartDate(), temp.getSelectedBooking().getEndDate);
  }

  public void saveBookingChanged()
  {
    //todo
    model.editBooking();
  }

  public void removeBooking()
  {
    //todo
    model.removeBooking();
  };
}
