package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Guest;
import model.Model;

/**
 * A class providing functionality for GuestInformationViewController.
 *
 * @version 04/05/2022
 */

public class GuestInformationViewModel {

    private Model model;
    private StringProperty firstNameField;
    private StringProperty lastNameField;
    private StringProperty emailField;
    private StringProperty phoneNumberField;
    private StringProperty errorLabel;
    private TemporaryInformation temp;


    /**
     * Constructor initializing instance variables.
     *
     * @param model model interface
     * @param tempInfo shared object with ReservationViewModel to store selected room
     */
    public GuestInformationViewModel(Model model, TemporaryInformation tempInfo) {
        this.model = model;
        this.firstNameField = new SimpleStringProperty("Bob");
        this.lastNameField = new SimpleStringProperty("Builder");
        this.emailField = new SimpleStringProperty("Bob@Builder.build");
        this.phoneNumberField = new SimpleStringProperty("202020");
        this.errorLabel = new SimpleStringProperty();
        this.temp = tempInfo;
    }


    /**
     * Delegates model to create new booking from
     * TemporaryInformation objects values and
     * fields from instance variables
     */
    public void bookRoomWithGuest() {

        if (model.book(temp.getRoomID(), temp.getStartDate(), temp.getEndDate(), new Guest(
                firstNameField.getValue(), lastNameField.getValue(),
                emailField.getValue(), Integer.parseInt(phoneNumberField.getValue())))) {
            errorLabel.setValue("Room was booked!");
        } else {
            errorLabel.setValue("This room is booked during this time period!");
        }
    }

    public StringProperty getFirstNameField() {
        return firstNameField;
    }

    public StringProperty getLastNameField() {
        return lastNameField;
    }

    public StringProperty getEmailField() {
        return emailField;
    }

    public StringProperty getPhoneNumberField() {
        return phoneNumberField;
    }

    public StringProperty getErrorLabel() {
        return errorLabel;
    }
}

