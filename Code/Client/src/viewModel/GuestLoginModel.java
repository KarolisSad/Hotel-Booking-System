package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.GuestTransfer;
import model.Model;

/**
 * A class that is used to provide functionality to UserLoginView.
 *
 * @author Group 5
 * @version 23/18/2022
 */
public class GuestLoginModel
{

    private StringProperty username;
    private StringProperty password;
    private StringProperty error;
    private Model model;

    /**
     * Constructor initializing all instance variables.
     * @param model model
     */
    public GuestLoginModel(Model model) {
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        error = new SimpleStringProperty();
        this.model = model;
    }

    /**
     * A getter for password.
     * @return StringProperty called password.
     */
    public StringProperty getPassword() {
        return password;
    }

    /**
     * A getter for error message.
     * @return StringProperty called error.
     */
    public StringProperty getError() {
        return error;
    }

    /**
     * A getter for username.
     * @return StringProperty called username.
     */
    public StringProperty getUsername() {
        return username;
    }

    /**
     * A method calling login() method from the model.
     * @return GuestTransfer object called guestTransfer.
     * @throws InterruptedException if the wait associated with login is interrupted
     */
    public GuestTransfer login() throws InterruptedException {
        GuestTransfer guestTransfer = model.login(username.get(),password.get());
        return guestTransfer;
    }

    /**
     * Method used for setting the instance variables to empty strings.
     */
    public void reset()
    {
        username.set("");
        password.set("");
        error.set("");
    }
}