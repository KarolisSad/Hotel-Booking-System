package view.GUI;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.ViewController;
import viewModel.GuestDetailsForReceptionistViewModel;

import java.io.IOException;

public class GuestDetailsForReceptionistController extends ViewController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField bookingIDField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Label errorLabel;

    private GuestDetailsForReceptionistViewModel viewModel;

    public GuestDetailsForReceptionistController(){
    }

    public void saveDetailsButton() {
        viewModel.updateGuest();
    }

    public void exitButton() throws IOException {
        reset();
        getViewHandler().openView("BookingsForReceptionistView.fxml");
    }

    @Override
    protected void init() {
            viewModel = getViewModelFactory().getGuestDetailsForReceptionistViewModel();
        try {
            emailField.textProperty().bindBidirectional(viewModel.getEmailProperty());
            firstNameField.textProperty().bindBidirectional(viewModel.getfNameProperty());
            lastNameField.textProperty().bindBidirectional(viewModel.getlNameProperty());
            bookingIDField.textProperty().bindBidirectional(viewModel.getBookingIDProperty());
            phoneNumberField.textProperty().bindBidirectional(viewModel.getPhoneNrProperty());
            errorLabel.textProperty().bindBidirectional(viewModel.getErrorLabelProperty());
        }catch (NullPointerException e){
            //can be null
        }

        reset();
        viewModel.dummyData();
    }



    @Override
    public void reset() {
       viewModel.reset();
    }
}
