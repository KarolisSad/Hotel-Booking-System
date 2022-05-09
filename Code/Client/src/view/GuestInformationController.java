package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.GuestInformationViewModel;
import viewModel.ViewModelFactory;

import java.io.IOException;
import java.rmi.RemoteException;
/**
 * A class creating an GuestInformationController object.
 *
 * @author Group 5
 * @version 04/05/2022
 */

public class GuestInformationController extends ViewController
{
    public TextField firstNameFields;
    public TextField lastNameField;
    public TextField emailField;
    public TextField phoneNumberField;
    public Label errorLabel;
    private GuestInformationViewModel viewModel;
    private Region root;
    private ViewHandler viewHandler;

    /**
     * A none argument, void method initializing instance variables.
     */
    @Override
    public void init() {
        // Binding
        firstNameFields.textProperty().bindBidirectional(viewModel.getFirstNameField());
        lastNameField.textProperty().bindBidirectional(viewModel.getLastNameField());
        emailField.textProperty().bindBidirectional(viewModel.getEmailField());
        phoneNumberField.textProperty().bindBidirectional(viewModel.getPhoneNumberField());
        errorLabel.textProperty().bindBidirectional(viewModel.getErrorLabel());
    }

    /**
     * A void method initializing instance variables.
     *
     * @param viewHandler A ViewHandler object which will be used to set the instance variable.
     * @param viewModelFactory  A ViewModelFactory object which will be used to set the instance variable.
     * @param root        A Region object which will be used to set the instance variable.
     */
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root)
            throws RemoteException {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.viewModel = viewModelFactory.getGuestInformationViewModel();

        init();

    }


    /**
     * A getter method returning the Region object.
     *
     * @return A Region object called root.
     */
    public Region getRoot(){
        return root;
    }

    /**
     * A non argument method that calls the clear() method from viewModel.
     */
    @Override
    public void reset() {
        //viewModel.clear();
    }

    /**
     * A void method setting guest information
     * for currently running booking.
     */
    public void confirmButton() {
        try
        {
            viewModel.bookRoomWithGuest();
        }

        catch (Exception e)
        {
            errorLabel.textProperty().set(e.getMessage());
        }

    }

    /**
     * A void method opening the reservation view.
     */
    public void goBack() throws IOException {
        viewHandler.openView("ReservationView.fxml");
    }
}
