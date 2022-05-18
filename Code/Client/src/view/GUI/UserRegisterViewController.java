package view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import view.ViewController;
import viewModel.UserRegisterModel;

import java.io.IOException;

public class UserRegisterViewController extends ViewController
{

    private UserRegisterModel viewModel;
    @FXML private TextField password;
    @FXML private TextField username;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private TextField lName;
    @FXML private TextField fName;

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getUserRegisterModel();
        password.textProperty().bindBidirectional(viewModel.passwordProperty());
        username.textProperty().bindBidirectional(viewModel.usernameProperty());
        phone.textProperty().bindBidirectional(viewModel.getPhoneNR());
        email.textProperty().bindBidirectional(viewModel.getEmail());
        lName.textProperty().bindBidirectional(viewModel.getlName());
        fName.textProperty().bindBidirectional(viewModel.fNameProperty());
    }

    @Override
    public void reset() {

    }

    public void register() throws IOException {
        viewModel.addUserWithUsername();
        getViewHandler().openView("ReservationView.fxml");
    }

    public void goBack() {
        //getViewHandler().openView("ReservationView.fxml");
    }
}