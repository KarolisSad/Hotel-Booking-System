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
 * Class representing the UserLoginView window.
 *
 * @author Group 5
 * @version 23-05-2022
 */

public class UserLoginViewController extends ViewController { private UserLoginModel viewModel;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label error;

    /**
     * Method initializing instance variables.
     * And binging TextFields and Label to viewModel properties.
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
     *  Method referencing to viewModel method called login().
     */
    public void login()  {
        // todo try catch, put in higher ..
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
     * Method opening UserLoginMainView window.
     * @throws IOException
     */
    public void menu() throws IOException {
        getViewHandler().openView("UserLoginMainView.fxml");
    }
}