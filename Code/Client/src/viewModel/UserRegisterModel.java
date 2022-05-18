package viewModel;

import javafx.beans.property.SimpleStringProperty;
import model.LoginInformation;
import model.Model;

public class UserRegisterModel {

    private Model model;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty email;
    private SimpleStringProperty phoneNR;

    public UserRegisterModel(Model model) {
        this.model = model;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        fName = new SimpleStringProperty();
        lName = new SimpleStringProperty();
        email = new SimpleStringProperty();
        phoneNR = new SimpleStringProperty();
    }

    public void addUserWithUsername()
    {
        try {
        LoginInformation register = new LoginInformation(username.get(),password.get());
        if (model.addGuest(fName.get(),lName.get(),email.get(),phoneNR.get(),register).getErrorMessage() != null)
        {
            //errorLabel.set(..)
        }
        // NEXT WINDOW
        }
        catch (Exception e)
        {
            //errorLabel.set(..)
        }
    }


    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public SimpleStringProperty fNameProperty() {
        return fName;
    }

    public SimpleStringProperty getlName() {
        return lName;
    }


    public SimpleStringProperty getEmail() {
        return email;
    }

    public SimpleStringProperty getPhoneNR() {
        return phoneNR;
    }

}