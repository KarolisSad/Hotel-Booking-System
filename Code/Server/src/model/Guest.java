package model;

/**
 * A class creating a Guest object
 *
 * @author Group 5
 * @version 04/05/2022
 */
public class Guest
{
  private String fName;
  private String lName;
  private String email;
  private int phoneNr;

  /**
   * Constructor to initialize instance variables using set methods.
   * @param fName first name of the guest
   * @param lName last name of the guest
   * @param email email address of the guest
   * @param phoneNr phone number of the guest
   */
  public Guest(String fName, String lName, String email, int phoneNr)
  {
    setfName(fName);
    setlName(lName);
    setEmail(email);
    setPhoneNr(phoneNr);
  }

  /**
   * Method setting the fName variable the String passed as an argument.
   * @throws NullPointerException if argument is null or an empty String.
   * @param fName first name of the guest
   */
  public void setfName(String fName)
  {
    if (fName == null || fName.isBlank())
    {
      throw new NullPointerException(
          "First name should not be empty. Please enter a valid first name.");
    }

    this.fName = fName;
  }

  /**
   * Method setting the lName variable the String passed as an argument.
   * @throws NullPointerException if argument is null or an empty String.
   * @param lName last name of the guest
   */
  public void setlName(String lName)
  {
    if (lName == null || lName.isBlank())
    {
      throw new NullPointerException(
          "Last name should not be empty. Please enter a valid last name.");
    }
    this.lName = lName;
  }

  /**
   * Method setting the email variable to the String passed as an argument.
   * @throws NullPointerException if argument is null or an empty String.
   * @throws IllegalArgumentException if argument does not contain @ or .
   * @param email email name of the guest
   */
  public void setEmail(String email)
  {
    if (email == null || email.isBlank())
    {
      throw new NullPointerException("E-mail not entered. Please enter a valid e-mail.");
    }
    else if (!email.contains(".") || !email.contains("@"))
    {
      throw new IllegalArgumentException(
          "Entered e-mail not valid. Please enter a valid e-mail containing @ and .");
    }

    this.email = email;
  }

  /**
   * Method setting the phoneNr variable to the int passed as an argument.
   * @throws IllegalArgumentException if argument is not 8 digits.
   * @param phoneNr phone number of the guest
   */
  public void setPhoneNr(int phoneNr)
  {
    if (phoneNr > 9999999 && phoneNr <= 99999999)
    {
      this.phoneNr = phoneNr;
    }

    else
    {
      throw new IllegalArgumentException(
          "Phone number should be 8 digits. Please enter a valid phone number.");
    }
  }

  /**
   * A method that returns first name of the guest.
   *
   * @return fName
   */
  public String getfName()
  {
    return fName;
  }

  /**
   * A method that returns last name of the guest.
   *
   * @return lName
   */
  public String getlName()
  {
    return lName;
  }

  /**
   * A method that returns email address of the guest.
   *
   * @return email
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * A method that returns phone number of the guest.
   *
   * @return phoneNr
   */
  public int getPhoneNr()
  {
    return phoneNr;
  }

  /**
   * A method that return the guest information in an organized way.
   *
   * @return Guest information
   */
  @Override public String toString()
  {
    return "Guest: " + '\'' + "First Name: '" + fName + '\'' + "Last Name: " + lName + '\''
        + "Email address: " + email + '\'' + "Phone Nr: " + phoneNr;
  }

  /**
   * Amethod meant to take a copy of an object.
   *
   * @return a copy of Guest object.
   */
  public Guest copy()
  {
    Guest other = new Guest(fName, lName, email, phoneNr);
    return other;
  }
}
