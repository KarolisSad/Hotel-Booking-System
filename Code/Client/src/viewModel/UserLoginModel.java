package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.GuestTransfer;
import model.Model;

public class UserLoginModel {

    private StringProperty username;
    private StringProperty password;
    private StringProperty error;
    private Model model;

    public UserLoginModel(Model model) {
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        error = new SimpleStringProperty();
        this.model = model;
    }

    public StringProperty getPassword() {
        return password;
    }

    public StringProperty getError() {
        return error;
    }

    public StringProperty getUsername() {
        return username;
    }

    public GuestTransfer login() throws InterruptedException {
        GuestTransfer guestTransfer = model.login(username.get(),password.get());
        return guestTransfer;
    }
}