package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.ViewController;
import viewModel.GuestRegisterModel;

import java.io.IOException;

/**
 * Class representing the UserRegisterView window.
 *
 * @author Group 5
 * @version 23-05-2022
 */
public class GuestRegisterController extends ViewController
{

    private GuestRegisterModel viewModel;
    @FXML private TextField password;
    @FXML private TextField username;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private TextField lName;
    @FXML private TextField fName;
    @FXML private Label errorLabel;

    /**
     * Method initializing instance variables.
     * And binging TextFields and Label to viewModel properties.
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
        errorLabel.textProperty().bindBidirectional(viewModel.getErrorLabel());

        reset();
    }

    /**
     * Method calling the reset method in the viewmodel.
     */
    @Override
    public void reset() {
        viewModel.reset();
    }

    /**
     * Method calls register() method in viewModel.
     * And opens GuestMenuView window.
     * @throws IOException
     */
    public void register() throws IOException {

        if (viewModel.register())
        {
         setUsernameInGuestInfo();
            getViewHandler().openView("GuestMenuView.fxml");
            reset();
        }


    }

    /**
     * Method opens UserLoginMainView window.
     * @throws IOException
     */
    public void goBack() throws IOException {
        getViewHandler().openView("MainMenu.fxml");
        reset();
    }

    public void setUsernameInGuestInfo(){
        getViewModelFactory().getGuestMenuModel().passTheUsernameInfo(viewModel.usernameProperty().get());
    }
}