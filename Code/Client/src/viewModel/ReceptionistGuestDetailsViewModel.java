package viewModel;

import javafx.beans.property.*;
import javafx.util.converter.IntegerStringConverter;
import model.Guest;
import model.Model;

/**
 * A class providing functionality for ReceptionistGuestDetailsViewController.
 *
 * @version 16/05/2022
 */

public class ReceptionistGuestDetailsViewModel
{

  private Model model;
  private SimpleStringProperty email;
  private SimpleStringProperty bookingID;
  private SimpleStringProperty fName; //ref first name of the guest
  private SimpleStringProperty lName; //ref last name of the guest
  private SimpleStringProperty phoneNr;
  private SimpleStringProperty errorLabel;

  /**
   * Constructor initializing instance variables.
   *
   * @param model model interface
   */
  public ReceptionistGuestDetailsViewModel(Model model)
  {
    this.model = model;

    this.email = new SimpleStringProperty();
    this.bookingID = new SimpleStringProperty();
    this.fName = new SimpleStringProperty();
    this.lName = new SimpleStringProperty();
    this.phoneNr = new SimpleStringProperty();
    this.errorLabel = new SimpleStringProperty();
  }

  /**
   * A getter method that returns guest's email as SimpleStringProperty.
   *
   * @return SimpleStringProperty called email.
   */

  public SimpleStringProperty getEmailProperty()
  {
    return email;
  }

  /**
   * A getter method that returns guest's email as String.
   *
   * @return String called email.
   */
  public String getEmail()
  {
    return email.get();
  }

  /**
   * A getter method that returns guest's bookingID as SimpleStringProperty.
   *
   * @return SimpleStringProperty called bookingID.
   */
  public SimpleStringProperty getBookingIDProperty()
  {
    return bookingID;
  }

  /**
   * A getter method that returns guest's bookingID as integer.
   *
   * @return integer called bookingID.
   */
  public int getBookingID()
  {
    IntegerStringConverter integerStringConverter = new IntegerStringConverter();
    return integerStringConverter.fromString(bookingID.get());
  }

  /**
   * A getter method that returns guest's first name as SimpleStringProperty.
   *
   * @return SimpleStringProperty called fName.
   */
  public SimpleStringProperty getfNameProperty()
  {
    return fName;
  }

  /**
   * A getter method that returns guest's first name as String.
   *
   * @return String called fName.
   */
  public String getfName()
  {
    return fName.get();
  }

  /**
   * A getter method that returns guest's last name as SimpleStringProperty.
   *
   * @return SimpleStringProperty called lName.
   */
  public SimpleStringProperty getlNameProperty()
  {
    return lName;
  }

  /**
   * A getter method that returns guest's last name as String.
   *
   * @return String called lName.
   */
  public String getlName()
  {
    return lName.get();
  }

  /**
   * A getter method that returns guest's phone number as SimpleStringProperty.
   *
   * @return SimpleStringProperty called phoneNr.
   */
  public SimpleStringProperty getPhoneNrProperty()
  {
    return phoneNr;
  }

  /**
   * A getter method that returns guest's phone number as integer.
   *
   * @return int called phoneNr.
   */
  public int getPhoneNr()
  {
    return convertToInteger(phoneNr);
  }

  /**
   * A non argument method that refers to
   * editGuest(String type, int bookingID, String fName, String lName, String email, int phoneNr)
   * method in the model.
   */
  public void updateGuest()
  {
    model.editGuest("editGuest", getBookingID(), getfName(), getlName(),
        getEmail(), Integer.parseInt(phoneNr.get()));

    //System.out.println("In the viewModel update guest argumentd:\n" + getBookingID() + " " + getEmail() + " " +  getfName() + " " +  getlName() + " " +  getPhoneNr());
  }

  /**
   * A method that converts StringProperty value to an integer value.
   *
   * @param property StringProperty that needs to be converted.
   * @return integer value called integer.
   */
  private int convertToInteger(StringProperty property)
  {
    IntegerStringConverter converter = new IntegerStringConverter();
    int integer = converter.fromString(property.get());
    return integer;
  }

  /**
   * A getter method that returns error's message as StringProperty.
   *
   * @return StringProperty called errorLabel.
   */
  public StringProperty getErrorLabelProperty()
  {
    return errorLabel;
  }

  /**
   * A getter method that makes the errorLabel in the constructor display error's message.
   *
   * @return String called errorLabel.
   */
  public void setErrorLabel(String errorLabel)
  {
    this.errorLabel.set(errorLabel);
  }

  /**
   * A non argument method that sets bookingID, email,
   * fName, lName, phoneNr values to an empty String.
   */
  public void reset()
  {
    bookingID.setValue("");
    email.setValue("");
    fName.setValue("");
    lName.setValue("");
    phoneNr.setValue("");
  }

  /**
   * A non argument method that sets bookingID, email,
   * fName, lName, phoneNr values to a values chosen in BookingsForReceptionistController
   */
  public void setGuest(IntegerProperty bookingIdProperty,
      ObjectProperty<Guest> guestProperty)
  {
    bookingID.setValue("" + bookingIdProperty.get());
    email.setValue("" + guestProperty.get().getEmail());
    fName.setValue("" + guestProperty.get().getfName());
    lName.setValue("" + guestProperty.get().getlName());
    phoneNr.setValue("" + guestProperty.get().getPhoneNr());
  }
}

