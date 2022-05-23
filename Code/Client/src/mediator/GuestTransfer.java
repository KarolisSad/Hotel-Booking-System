package mediator;

import model.Guest;

import java.util.ArrayList;

/**
 * Purpose of this object is to store different values when sending to a server
 *
 * @version 2022-05-23
 * @author Group5
 */
public class GuestTransfer {
  private String fName;
  private String lName;
  private String email;
  private int phoneNr;
  private int bookingID;
  private String type;
  private String errorMessage;
  private ArrayList<Guest> guests;
  private String password;
  private String username;

  /**
   * 6 argument constructor used for transferring guest information and a booking identification.
   * @param type The type of transfer
   * @param bookingID The booking identification
   * @param fName First name of the guest
   * @param lName Last name of the guest
   * @param email Guest's email
   * @param phoneNr Guest's phone number
   */
  public GuestTransfer(String type, int bookingID, String fName, String lName, String email, int phoneNr){
    this.type = type;
    this.bookingID = bookingID;
    this.email = email;
    this.phoneNr = phoneNr;
    this.fName = fName;
    this.lName = lName;
  }

  /**
   * 3 argument constructor used for transferring guest username and password.
   * @param type The type of transfer
   * @param username The guest username
   * @param password The guest password
   */
  public GuestTransfer(String type, String username, String password)
  {
    this.type = type;
    this.username = username;
    this.password = password;
  }

  /**
   * 1 argument constructor used for transferring type of GuestTransfer.
   * Used for errors.
   * @param type The type of transfer
   */
  public GuestTransfer(String type)
  {
    this.type = type;
  }

  /**
   * 7 argument constructor used for transferring all information about the guest.
   * @param type The type of transfer.
   * @param fName First name of the guest
   * @param lName Last name of the guest
   * @param email Guest's email
   * @param phoneNumber Guest's phone number
   * @param username The guest username
   * @param password The guest password
   */
  public GuestTransfer(String type, String fName, String lName, String email, int phoneNumber, String username, String password)
  {
    this.type = type;
    this.fName = fName;
    this.lName = lName;
    this.email = email;
    this. phoneNr = phoneNumber;
    this.username = username;
    this.password = password;
  }

  /**
   * 6 argument constructor used for transferring type information, used to edit guest details.
   * @param editGuestWithUsername The type of transfer.
   * @param getfName First name of the guest
   * @param getlName Last name of the guest
   * @param email Guest's email
   * @param phoneNr Guest's phone number
   * @param username The guest username
   */
    public GuestTransfer(String editGuestWithUsername, String username, String getfName, String getlName, String email, int phoneNr) {
    this.type = editGuestWithUsername;
    this.username = username;
    this.fName = getfName;
    this.lName = getlName;
    this.email = email;
    this.phoneNr = phoneNr;
    }

  /**
   * 2 argument constructor used for transferring type of GuestTransfer.
   * Used for getting the guest by its username.
   * @param getGuestByUsername The type of transfer
   * @param username The guest's username
   */
  public GuestTransfer(String getGuestByUsername, String username) {
    this.type = getGuestByUsername;
    this.username = username;
  }

  /**
   * A getter method that returns ArrayList of Guest.
   * @return ArrayList of Guest called guests.
   */
  public ArrayList<Guest> getGuests() {
    return guests;
  }

  /**
   * A getter method that returns a type of the GuestTransfer.
   * @return String called type.
   */
  public String getType() {
    return type;
  }

  /**
   * A getter method that returns a String of all the information from the GuestTransfer.
   * @return String object.
   */
  @Override
  public String toString() {
    return "GuestTransfer{" +
            "fName='" + fName + '\'' +
            ", lName='" + lName + '\'' +
            ", email='" + email + '\'' +
            ", phoneNr=" + phoneNr +
            ", bookingID=" + bookingID +
            ", type='" + type + '\'' +
            ", guests=" + guests +
            '}';
  }
  /**
   * Method used for returning the error message
   * @return the error message as a string.
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * Method used for returning the first name.
   * @return the fName as a string.
   */
  public String getfName() {
    return fName;
  }

  /**
   * Method used for returning the last name.
   * @return the lName as a string.
   */
  public String getlName() {
    return lName;
  }

  /**
   * Method used for returning the email.
   * @return the email as a string.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Method used for returning the phone number.
   * @return the phone number as a string.
   */
  public String getPhoneNr() {
    return String.valueOf(phoneNr);
  }
}
