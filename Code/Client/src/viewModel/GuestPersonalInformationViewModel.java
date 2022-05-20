package viewModel;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import mediator.GuestTransfer;
import model.Guest;
import model.Model;

public class GuestPersonalInformationViewModel {

    private SimpleStringProperty email;
    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty username;
    private SimpleStringProperty phoneNr;
    private SimpleStringProperty errorLabel;
    private Model model;

    public GuestPersonalInformationViewModel(Model model) {
        this.model = model;
        this.errorLabel = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.fName = new SimpleStringProperty();
        this.lName = new SimpleStringProperty();
        this.phoneNr = new SimpleStringProperty();
        this.username = new SimpleStringProperty();
    }


    public SimpleStringProperty getEmailProperty() {
        return email;
    }

    public SimpleStringProperty getfNameProperty() {
        return fName;
    }

    public SimpleStringProperty getlNameProperty() {
        return lName;
    }

    public SimpleStringProperty getUsernameProperty() {
        return username;
    }

    public SimpleStringProperty getPhoneNrProperty() {
        return phoneNr;
    }

    public SimpleStringProperty getErrorLabelProperty() {
        return errorLabel;
    }

    public void reset() {
        email.setValue("");
        errorLabel.setValue("");
        lName.setValue("");
        fName.setValue("");
        email.setValue("");
    }

    public void updateGuest() {
        model.editGuestWithUsername("editGuestWithUsername", getUsername(), getfName(), getlName(),
                getEmail(), Integer.parseInt(phoneNr.get()));
        System.out.println("Editing personal info of: " + getUsername());
    }

    private String getEmail() {
        return email.get();
    }

    private String getlName() {
        return lName.get();
    }

    private String getfName() {
        return fName.get();
    }

    private String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.setValue(username + "");
        System.out.println("username should be set: " + username);
    }

    public void setValues() {
        System.out.println("Username in setValues: " + getUsername());
        GuestTransfer guestTransfer = model.getGuestByUsername(getUsername());
        fName.setValue(guestTransfer.getfName());
        lName.setValue(guestTransfer.getlName());
        email.setValue(guestTransfer.getEmail());
        phoneNr.setValue(guestTransfer.getPhoneNr());
        System.out.println(fName + " " + lName);
    }
}
