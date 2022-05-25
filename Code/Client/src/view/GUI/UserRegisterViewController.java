package view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import view.ViewController;
import viewModel.UserRegisterModel;

import java.io.IOException;

/**
 * A class that creates UserRegisterViewController object.
 *
 * @author Group 5
 * @version 25-05-22
 */
public class UserRegisterViewController extends ViewController
{

    private UserRegisterModel viewModel;
    @FXML private TextField password;
    @FXML private TextField username;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private TextField lName;
    @FXML private TextField fName;

    /**
     * A none argument, void method initializing instance variables.
     */
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

    /**
     * A method that meant for providing functionality to the register button.
     * When the button is pressed the program goes into Guest menu view, otherwise and error pop up.
     * @throws IOException
     */
    public void register() throws IOException {
        viewModel.register();
        getViewHandler().openView("GuestMenuView.fxml");
    }

    /**
     * A method that provides functionality to the go back button.
     * When the button is pressed the program goes back to the main menu.
     * @throws IOException
     */
    public void goBack() throws IOException {
        getViewHandler().openView("UserLoginMainView.fxml");
    }
}