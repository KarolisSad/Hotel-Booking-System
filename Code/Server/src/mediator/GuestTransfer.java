package mediator;

import model.Guest;

import java.util.ArrayList;

public class GuestTransfer {
    private String fName;
    private String lName;
    private String email;
    private int phoneNr;
    private int bookingID;
    private String type;
    private String errorMessage;
    private ArrayList<Guest> guests;

    public GuestTransfer(String type, String fName, String lName, String email, int phoneNr){
        this.type = type;
        this.bookingID = 0;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNr = phoneNr;
    }

    public GuestTransfer(String type, int bookingID, String fName, String lName, String email, int phoneNr){
        this.type = type;
        this.bookingID = bookingID;
        this.email = email;
        this.phoneNr = phoneNr;
        this.fName = fName;
        this.lName = lName;
    }

    public GuestTransfer(String type, String errorMessage)
    {
        this.type = type;
        this.errorMessage = errorMessage;
    }

    public GuestTransfer(String type, ArrayList<Guest> guests)
    {
        this.guests = guests;
        this.type = type;
    }

    public GuestTransfer(String type)
    {
        this.type = type;
    }

    public String getFullName() {
        return fName + " " + lName;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNr() {
        return phoneNr;
    }

    public int getBookingID() {
        return bookingID;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public String getType() {
        return type;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
