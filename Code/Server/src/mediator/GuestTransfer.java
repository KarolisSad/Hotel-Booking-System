package mediator;

import model.Guest;

import java.util.ArrayList;

/**
 * Purpose of this object is to store different values when sending to a server
 *
 * @version 2022-05-18
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

  /**
   * 5 argument constructor used for transferring guest information and a booking identification.
   * @param type The type of transfer
   * @param fName First name of the guest
   * @param lName Last name of the guest
   * @param email Guest's email
   * @param phoneNr Guest's phone number
   */
  public GuestTransfer(String type, String fName, String lName, String email, int phoneNr){
    this.type = type;
    this.bookingID = 0;
    this.fName = fName;
    this.lName = lName;
    this.email = email;
    this.phoneNr = phoneNr;
  }

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
   * 2 argument constructor used for transferring error messages.
   * @param type The type of the message.
   * @param errorMessage The error message.
   */
  public GuestTransfer(String type, String errorMessage)
  {
    this.type = type;
    this.errorMessage = errorMessage;
  }

  /**
   * 2 argument constructor used for transferring an ArrayList of guests.
   * @param type The type of the message.
   * @param guests The ArrayList of guests..
   */
  public GuestTransfer(String type, ArrayList<Guest> guests)
  {
    this.guests = guests;
    this.type = type;
  }

  /**
   * 1 argument constructor used for transferring type information.
   * @param type The type of transfer
   */
  public GuestTransfer(String type)
  {
    this.type = type;
  }

  /**
   * Method used for returning the full name of a guest.
   * @return The first and last name of the guest as a String.
   */
  public String getFullName() {
    return fName + " " + lName;
  }

  /**
   * Method used for returning the first name of a guest
   * @return the first name of the guest as a String
   */
  public String getfName() {
    return fName;
  }


  /**
   * Method used for returning the last name of a guest
   * @return the last name of the guest as a String
   */
  public String getlName() {
    return lName;
  }

  /**
   * Method used for returning the email of a guest
   * @return the email of the guest as a String
   */
  public String getEmail() {
    return email;
  }

  /**
   * Method used for returning the phone nr of a guest
   * @return the phone nr of the guest as a String
   */
  public int getPhoneNr() {
    return phoneNr;
  }

  /**
   * Method used for returning the booking ID
   * @return the booking ID as a string.
   */
  public int getBookingID() {
    return bookingID;
  }

  /**
   * Method used for returning the the ArrayList of guests.
   * @return ArrayList of Guests.
   */
  public ArrayList<Guest> getGuests() {
    return guests;
  }

  /**
   * Method used for returning the type variable
   * @return the type as a String.
   */
  public String getType() {
    return type;
  }

  /**
   * Method used for returning the error message
   * @return the error message as a string.
   */
  public String getErrorMessage() {
    return errorMessage;
  }
}
