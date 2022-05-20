package viewModel;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import mediator.RoomBookingTransfer;
import model.Model;

import java.time.LocalDate;

/**
 * A class that provides functionality to ShowBookingViewController
 *
 * @author Group 5
 * @version 18-05-22
 */
public class ShowBookingViewModel
{
  private Model model;
  private SimpleStringProperty inputPhoneNr;
  private SimpleStringProperty inputBookingNr;
  private SimpleStringProperty outPutName;
  private SimpleStringProperty outPutPhone;
  private SimpleObjectProperty<LocalDate> outPutStartDate;
  private SimpleObjectProperty<LocalDate> outPutEndDate;
  private SimpleStringProperty outPutRoomNr;
  private SimpleStringProperty outPutNrBeds;
  private SimpleStringProperty errorLabel;

  /**
   * Constructor initializing instance variables
   * @param model model interface
   */
  public ShowBookingViewModel(Model model)
  {
    this.model = model;

    inputPhoneNr = new SimpleStringProperty("");
    inputBookingNr = new SimpleStringProperty("");
    outPutName = new SimpleStringProperty();
    outPutPhone = new SimpleStringProperty();
    outPutStartDate = new SimpleObjectProperty<>();
    outPutEndDate = new SimpleObjectProperty<>();
    outPutRoomNr = new SimpleStringProperty();
    outPutNrBeds = new SimpleStringProperty();
    errorLabel = new SimpleStringProperty("");
  }

  /**
   * A method used to reset all the field to an empty Strings.
   */
  public void reset()
  {
    clearInput();

    outPutName.set("");
    outPutPhone.set("");
    outPutStartDate.set(null);
    outPutEndDate.set(null);
    outPutRoomNr.set("");
    outPutNrBeds.set("");
    errorLabel.set("");
  }

  /**
   * A method to used to clear inputs for phone number and bookingNr.
   */
  public void clearInput()
  {
    inputPhoneNr.set("");
    inputBookingNr.set("");
  }

  /**
   * A method that calls inputPhoneNr that is encapsulated into SimpleStringProperty
   * @return inputPhoneNr
   */
  public SimpleStringProperty inputPhoneNrProperty()
  {
    return inputPhoneNr;
  }

  /**
   * A method that calls inputBookingNr that is encapsulated into SimpleStringProperty
   * @return inputBookingNr
   */
  public SimpleStringProperty inputBookingNrProperty()
  {
    return inputBookingNr;
  }

  /**
   * A method that calls guest full name that is encapsulated into SimpleStringProperty
   * @return outPutName
   */
  public SimpleStringProperty outPutNameProperty()
  {
    return outPutName;
  }

  /**
   * A method that calls guest phone number that is encapsulated into SimpleStringProperty
   * @return outPutPhone
   */
  public SimpleStringProperty outPutPhoneProperty()
  {
    return outPutPhone;
  }

  /**
   * A method that calls start date of the booking that is encapsulated into SimpleObjectProperty
   * @return outPutStartDate
   */
  public SimpleObjectProperty<LocalDate> outPutStartDateProperty()
  {
    return outPutStartDate;
  }

  /**
   * A method that calls end date of the booking that is encapsulated into SimpleObjectProperty
   * @return outPutEndDate
   */
  public SimpleObjectProperty<LocalDate> outPutEndDateProperty()
  {
    return outPutEndDate;
  }

  /**
   * A method that calls room number that is encapsulated into SimpleStringProperty
   * @return outPutRoomNr
   */
  public SimpleStringProperty outPutRoomNrProperty()
  {
    return outPutRoomNr;
  }

  /**
   * A method that calls number of beds that is encapsulated into SimpleStringProperty
   * @return outPutNrBeds
   */
  public SimpleStringProperty outPutNrBedsProperty()
  {
    return outPutNrBeds;
  }

  /**
   * A method that calls errorLabel that is encapsulated into errorLabelProperty
   * @return errorLabel
   */
  public SimpleStringProperty errorLabelProperty()
  {
    return errorLabel;
  }

  /**
   * A method is used to find the booking according to the input fields given in the GUI.
   * If the input fields are empty it will clear the field and output an error message
   */
  public void findBooking()
  {
    if (!inputBookingNr.get().isEmpty() && !inputPhoneNr.get().isEmpty())
    {
      setInformation();
    }

    else
    {
      reset();
      throw new IllegalArgumentException(
          "Please fill in a valid booking- and phone nr.");
    }
  }

  /**
   * A method used to set information of the given booking nr and phone number.
   * If the given booking nr or phone number formats are incorrect an error message will appear.
   */
  private void setInformation()
  {
    int bookingNr = -1;
    int phoneNr = -1;
    try
    {
      bookingNr = Integer.parseInt(inputBookingNr.get());
    }
    catch (NumberFormatException e)
    {
      throw new IllegalArgumentException(
          "The entered booking number should be a number.");
    }
    try
    {
      phoneNr = Integer.parseInt(inputPhoneNr.get());
    }
    catch (NumberFormatException e)
    {
      throw new IllegalArgumentException(
          "The entered phone number should be a 8-digit number.");
    }
    RoomBookingTransfer getBookingWithGuest = model.getBookingWithGuest(
        bookingNr, phoneNr);

    if (!getBookingWithGuest.getType().equalsIgnoreCase("error"))
    {
      outPutName.set(getBookingWithGuest.getGuest().getfName() + " "
          + getBookingWithGuest.getGuest().getlName());
      outPutPhone.set(
          String.valueOf(getBookingWithGuest.getGuest().getPhoneNr()));
      outPutStartDate.set(getBookingWithGuest.getStartDate());
      outPutEndDate.set(getBookingWithGuest.getEndDate());
      outPutRoomNr.set(getBookingWithGuest.getRoom().getRoomId());
      outPutNrBeds.set(
          String.valueOf(getBookingWithGuest.getRoom().getNumberOfBeds()));
    }
    else
    {
      throw new IllegalArgumentException(getBookingWithGuest.getMessage());
    }
  }
}
