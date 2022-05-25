package view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mediator.GuestTransfer;
import org.w3c.dom.Text;
import view.ViewController;
import viewModel.UserLoginModel;

import java.io.IOException;

/**
 * A class that creates a UserLoginViewController object.
 *
 * @author Group 5
 * @version 25-05-22
 */
public class UserLoginViewController extends ViewController {

    private UserLoginModel viewModel;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label error;

    /**
     * A none argument, void method initializing instance variables.
     */
    @Override
    protected void init() {
        viewModel = getViewModelFactory().getUserLoginModel();
        username.textProperty().bindBidirectional(viewModel.getUsername());
        password.textProperty().bindBidirectional(viewModel.getPassword());
        error.textProperty().bindBidirectional(viewModel.getError());
    }

    @Override
    public void reset() {

    }

    /**
     * A method meant for providing functionality to the login button.
     * When the button is pressed the system checks if the given user log in
     * exists and if so it opens a guest menu window, otherwise it will output an error message.
     */
    public void login()  {
        try {
            GuestTransfer guestTransfer = viewModel.login();
            if (!(guestTransfer.getType().equals("Success")))
            {
                error.setText("User wasn't found.");
            }
            else {

                getViewHandler().openView("GuestMenuView.fxml");
                getViewModelFactory().getGuestMenuModel().passTheUsernameInfo(username.getText());
            }
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method that provides functionality to the menu button.
     * When the button is pressed the program will open a Log in window for existing
     * users to access their accounts.
     * @throws IOException
     */
    public void menu() throws IOException {
        getViewHandler().openView("UserLoginMainView.fxml");
    }
}