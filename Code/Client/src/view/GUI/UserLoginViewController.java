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

public class UserLoginViewController extends ViewController
{
    private UserLoginModel viewModel;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label error;

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

    public void login() {

        // todo try catch, put in higher ..
        try {
            GuestTransfer guestTransfer = viewModel.login();

            if (guestTransfer.getType().equals("Error"))
            {
                error.setText("User wasn't found.");
            }
            else {

            getViewHandler().openView("ReservationView.fxml");
            }
        }
catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void menu() throws IOException {
        getViewHandler().openView("UserLoginMainView.fxml");
    }
}