package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import view.ViewController;
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
  @FXML private TextField firstNameFields;
  @FXML private TextField lastNameField;
  @FXML private TextField emailField;
  @FXML private TextField phoneNumberField;
  @FXML private Label errorLabel;
  private GuestInformationViewModel viewModel;

  /**
   * A none argument, void method initializing instance variables.
   */
  @Override protected void init()
  {

    viewModel = getViewModelFactory().getGuestInformationViewModel();
    // Binding
    firstNameFields.textProperty()
        .bindBidirectional(viewModel.getFirstNameField());
    lastNameField.textProperty()
        .bindBidirectional(viewModel.getLastNameField());
    emailField.textProperty().bindBidirectional(viewModel.getEmailField());
    phoneNumberField.textProperty()
        .bindBidirectional(viewModel.getPhoneNumberField());
    errorLabel.textProperty().bindBidirectional(viewModel.getErrorLabel());
  }

  /**
   * A non argument method that calls the clear() method from viewModel.
   */
  @Override public void reset()
  {
    //viewModel.clear();
  }



  /**
   * A void method setting guest information
   * for currently running booking.
   */
    /*
  public void confirmButton()
  {
    try
    {
      getViewModelFactory().getGuestInformationViewModel().bookRoomWithGuest();
    }

    catch (Exception e)
    {
      errorLabel.textProperty().set(e.getMessage());
    }

  }


     */

  /**
   * A void method opening the reservation view.
   */
  public void goBack() throws IOException
  {
    getViewHandler().openView("ReservationView.fxml");
  }
}