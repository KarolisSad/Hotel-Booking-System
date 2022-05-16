package viewModel;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import mediator.RoomBookingTransfer;
import model.Model;

import java.time.LocalDate;

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

  public void reset()
  {
    clearInput();

    // FIX
    outPutName.set("");
    outPutPhone.set("");
    outPutStartDate.set(null);
    outPutEndDate.set(null);
    outPutRoomNr.set("");
    outPutNrBeds.set("");
    errorLabel.set("");
  }

  public void clearInput()
  {
    inputPhoneNr.set("");
    inputBookingNr.set("");
  }

  public SimpleStringProperty inputPhoneNrProperty()
  {
    return inputPhoneNr;
  }

  public SimpleStringProperty inputBookingNrProperty()
  {
    return inputBookingNr;
  }

  public SimpleStringProperty outPutNameProperty()
  {
    return outPutName;
  }

  public SimpleStringProperty outPutPhoneProperty()
  {
    return outPutPhone;
  }

  public SimpleObjectProperty<LocalDate> outPutStartDateProperty()
  {
    return outPutStartDate;
  }

  public SimpleObjectProperty<LocalDate> outPutEndDateProperty()
  {
    return outPutEndDate;
  }

  public SimpleStringProperty outPutRoomNrProperty()
  {
    return outPutRoomNr;
  }

  public SimpleStringProperty outPutNrBedsProperty()
  {
    return outPutNrBeds;
  }

  public SimpleStringProperty errorLabelProperty()
  {
    return errorLabel;
  }

  public void findBooking()
  {
    if (!inputBookingNr.get().isEmpty() && !inputPhoneNr.get().isEmpty())
    {
      setInformation();
    }

    else
    {
      reset();
      throw new IllegalArgumentException("Please fill in a valid booking- and phone nr.");
    }
  }

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
      throw new IllegalArgumentException("The entered booking number should be a number.");
    }
    try
    {
      phoneNr = Integer.parseInt(inputPhoneNr.get());
    }
    catch (NumberFormatException e)
    {
      throw new IllegalArgumentException("The entered phone number should be a 8-digit number.");
    }
    RoomBookingTransfer getBookingWithGuest = model.getBookingWithGuest(bookingNr, phoneNr);

    if (!getBookingWithGuest.getType().equalsIgnoreCase("error"))
    {
      outPutName.set(getBookingWithGuest.getGuest().getfName() + " " + getBookingWithGuest.getGuest().getlName());
      outPutPhone.set(String.valueOf(getBookingWithGuest.getGuest().getPhoneNr()));
      outPutStartDate.set(getBookingWithGuest.getStartDate());
      outPutEndDate.set(getBookingWithGuest.getEndDate());
      outPutRoomNr.set(getBookingWithGuest.getRoom().getRoomId());
      outPutNrBeds.set(String.valueOf(getBookingWithGuest.getRoom().getNumberOfBeds()));
    }
    else
    {
      throw new IllegalArgumentException(getBookingWithGuest.getMessage());
    }
  }
}
