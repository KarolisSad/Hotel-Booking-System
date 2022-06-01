package viewModel;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import mediator.GuestTransfer;
import model.Guest;
import model.Model;

/**
 * A class providing functionality for GuestPersonalInformationController.
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class GuestPersonalInformationViewModel {

    private SimpleStringProperty email;
    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty username;
    private SimpleStringProperty phoneNr;
    private SimpleStringProperty errorLabel;
    private Model model;

    /**
     * Constructor initializing instance variables.
     * @param model model
     */
    public GuestPersonalInformationViewModel(Model model) {
        this.model = model;
        this.errorLabel = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.fName = new SimpleStringProperty();
        this.lName = new SimpleStringProperty();
        this.phoneNr = new SimpleStringProperty();
        this.username = new SimpleStringProperty();
    }

    /**
     * A getter method that returns guest's email as SimpleStringProperty.
     *
     * @return SimpleStringProperty called email.
     */
    public SimpleStringProperty getEmailProperty() {
        return email;
    }

    /**
     * A getter method that returns guest's first name as SimpleStringProperty.
     *
     * @return SimpleStringProperty called fName.
     */
    public SimpleStringProperty getfNameProperty() {
        return fName;
    }

    /**
     * A getter method that returns guest's last name as SimpleStringProperty.
     *
     * @return SimpleStringProperty called lName.
     */
    public SimpleStringProperty getlNameProperty() {
        return lName;
    }

    /**
     * A getter method that returns guest's username as SimpleStringProperty.
     *
     * @return SimpleStringProperty called username.
     */
    public SimpleStringProperty getUsernameProperty() {
        return username;
    }

    /**
     * A getter method that returns guest's phone number as SimpleStringProperty.
     *
     * @return SimpleStringProperty called phoneNr.
     */
    public SimpleStringProperty getPhoneNrProperty() {
        return phoneNr;
    }

    /**
     * A getter method that returns error message as SimpleStringProperty.
     *
     * @return SimpleStringProperty called errorLabel.
     */
    public SimpleStringProperty getErrorLabelProperty() {
        return errorLabel;
    }

    /**
     * Method that sets all values to an empty String.
     */
    public void reset() {
        email.setValue("");
        errorLabel.setValue("");
        lName.setValue("");
        fName.setValue("");
        email.setValue("");
    }

    /**
     * Method calling editGuestWithUsername() from the model.
     */
    public void updateGuest() {

        GuestTransfer guestTransfer = model.editGuestWithUsername("editGuestWithUsername", getUsername(), getfName(), getlName(),
                getEmail(), Integer.parseInt(phoneNr.get()));
        if (guestTransfer.getType().equals("error"))
        {
            errorLabel.setValue("Please fill in all text fields.");
        }
        else {
            errorLabel.setValue("");
        }
    }

    /**
     * A getter method that returns guest's email as String.
     *
     * @return String called email.
     */
    private String getEmail() {
        return email.get();
    }

    /**
     * A getter method that returns guest's last name as String.
     *
     * @return String called lName.
     */
    private String getlName() {
        return lName.get();
    }

    /**
     * A getter method that returns guest's first name as String.
     *
     * @return String called fName.
     */
    private String getfName() {
        return fName.get();
    }

    /**
     * A getter method that returns guest's username as String.
     *
     * @return String called username.
     */
    private String getUsername() {
        return username.get();
    }

    /**
     * A setter method that sets guest's username.
     * @param username the value to set the username to.
     */
    public void setUsername(String username) {
        this.username.setValue(username + "");
    }

    /**
     * A setter method that sets guest's first name, last name
     * email and phone number.
     */
    public void setValues() {
        GuestTransfer guestTransfer = model.getGuestByUsername(getUsername());
        fName.setValue(guestTransfer.getfName());
        lName.setValue(guestTransfer.getlName());
        email.setValue(guestTransfer.getEmail());
        phoneNr.setValue(guestTransfer.getPhoneNr());
    }
}
