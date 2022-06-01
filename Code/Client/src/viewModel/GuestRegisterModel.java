package viewModel;

import javafx.beans.property.SimpleStringProperty;
import model.Model;

/**
 * A class that is used to provide functionality to UserRegisterController.
 *
 * @author Group 5
 * @version 23/18/2022
 */
public class GuestRegisterModel
{

    public SimpleStringProperty errorLabel;
    private Model model;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty email;
    private SimpleStringProperty phoneNR;

    /**
     * Constructor initializing all instance variables.
     * @param model model
     */
    public GuestRegisterModel(Model model) {
        this.model = model;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        fName = new SimpleStringProperty();
        lName = new SimpleStringProperty();
        email = new SimpleStringProperty();
        phoneNR = new SimpleStringProperty();
        errorLabel = new SimpleStringProperty("");
    }

    /**
     * A method calling register() method from model.
     * @return true if logins succeeds
     */
    public boolean register()
    {
        try {
        if (!model.register(fName.get(), lName.get(), email.get(), Integer.parseInt(phoneNR.get()),
                username.get(), password.get()).getType().equals("Success"))
        {
            errorLabel.setValue("error");
            return false;
        }

        return true;
        }
        catch (Exception e)
        {
            errorLabel.setValue("error");
            return false;
        }
    }

    /**
     * A getter for username.
     * @return SimpleStringProperty called username.
     */
    public SimpleStringProperty usernameProperty() {
        return username;
    }

    /**
     * A getter for password.
     * @return SimpleStringProperty called password.
     */
    public SimpleStringProperty passwordProperty() {
        return password;
    }

    /**
     * A getter for first name.
     * @return SimpleStringProperty called fName.
     */
    public SimpleStringProperty fNameProperty() {
        return fName;
    }

    /**
     * A getter for last name.
     * @return SimpleStringProperty called lName.
     */
    public SimpleStringProperty getlName() {
        return lName;
    }

    /**
     * A getter for email.
     * @return SimpleStringProperty called email.
     */
    public SimpleStringProperty getEmail() {
        return email;
    }

    /**
     * A getter for phone number.
     * @return SimpleStringProperty called phoneNR.
     */
    public SimpleStringProperty getPhoneNR() {
        return phoneNR;
    }

    public SimpleStringProperty getErrorLabel() {
        return errorLabel;
    }

    /**
     * Method used for setting the instance variables to empty strings.
     */
    public void reset()
    {
        username.set("");
        password.set("");
        fName.set("");
        lName.set("");
        email.set("");
        phoneNR.set("");
        errorLabel.set("");
    }
}