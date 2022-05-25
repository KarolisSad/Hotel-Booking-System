package viewModel.Helpers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Guest;

/**
 * A class used to store information about guests to be used in tables.
 *
 * @author Group 5
 * @version 19/05/2022
 */

public class SimpleGuestViewModel
{
  private StringProperty userNameProperty;
  private StringProperty firstNameProperty;
  private StringProperty lastNameProperty;
  private StringProperty emailProperty;
  private IntegerProperty phoneNrProperty;

  /**
   * Constructor used for initializing all instance variables
   * @param guest The guest object to convert to a SimpleGuestViewModel object
   */
  public SimpleGuestViewModel(Guest guest)
  {
    userNameProperty = new SimpleStringProperty(guest.getUsername());
    firstNameProperty = new SimpleStringProperty(guest.getfName());
    lastNameProperty = new SimpleStringProperty(guest.getlName());
    emailProperty = new SimpleStringProperty(guest.getEmail());
    phoneNrProperty = new SimpleIntegerProperty(guest.getPhoneNr());
  }

  public StringProperty userNameProperty()
  {
    return userNameProperty;
  }

  /**
   * Method returning the firstName property
   * @return the firstName property
   */
  public StringProperty firstNameProperty()
  {
    return firstNameProperty;
  }

  /**
   * Method returning the lastName property
   * @return the lastName property
   */
  public StringProperty lastNameProperty()
  {
    return lastNameProperty;
  }

  /**
   * Method returning the email property
   * @return the email property
   */
  public StringProperty emailProperty()
  {
    return emailProperty;
  }

  /**
   * Method returning the phoneNr property
   * @return the phoneNr property
   */
  public IntegerProperty phoneNrProperty()
  {
    return phoneNrProperty;
  }
}