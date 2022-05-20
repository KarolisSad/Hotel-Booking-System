package viewModel;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import model.Model;

public class GuestPersonalInformationViewModel{

    private SimpleStringProperty email;
    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty username;
    private SimpleStringProperty phoneNr;
    private SimpleStringProperty errorLabel;
    private Model model;

    public GuestPersonalInformationViewModel(Model model){
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
        username.set("");
    }

    public void updateGuest() {
        //model.editGuest("editGuest", getBookingID(), getfName(), getlName(),
         //       getEmail(), Integer.parseInt(phoneNr.get()));
    }
}
