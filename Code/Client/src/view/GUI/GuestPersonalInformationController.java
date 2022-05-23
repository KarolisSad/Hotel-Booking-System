package view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.ViewController;
import viewModel.GuestPersonalInformationViewModel;

import java.io.IOException;

/**
 * A class creating GuestPersonalInformationController object.
 *
 * @author Group 5
 * @version 23/05/2022
 */

public class GuestPersonalInformationController extends ViewController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Label errorLabel;
    private GuestPersonalInformationViewModel viewModel;

    /**
     * A none argument, void method initializing instance variables.
     * We bind all of FXML objects to ViewModel.
     */
    @Override protected void init()
    {
        viewModel = getViewModelFactory().getGuestPersonalInformationViewModel();
        try
        {
            emailField.textProperty().bindBidirectional(viewModel.getEmailProperty());
            firstNameField.textProperty()
                    .bindBidirectional(viewModel.getfNameProperty());
            lastNameField.textProperty()
                    .bindBidirectional(viewModel.getlNameProperty());
            usernameField.textProperty()
                    .bindBidirectional(viewModel.getUsernameProperty());
            phoneNumberField.textProperty()
                    .bindBidirectional(viewModel.getPhoneNrProperty());
            errorLabel.textProperty()
                    .bindBidirectional(viewModel.getErrorLabelProperty());
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * A method referencing to ViewModel updateGuest() method.
     */
    public void saveDetailsButton()
    {
        try
        {
            viewModel.updateGuest();
        }
        catch (Exception e)
        {
            errorLabel.setText(e.getMessage());
        }
    }

    /**
     * A method that refers to reset() method in ViewModel
     */
    @Override public void reset()
    {
        viewModel.reset();
    }


    public void menuButton() throws IOException {
        getViewHandler().openView("GuestMenuView.fxml");
        reset();
    }
}
