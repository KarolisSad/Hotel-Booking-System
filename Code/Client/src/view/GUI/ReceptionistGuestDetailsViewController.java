package view.GUI;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.ViewController;
import viewModel.ReceptionistGuestDetailsViewModel;

import java.io.IOException;

/**
 * A class creating an ReceptionistGuestDetailsViewController object.
 *
 * @author Group 5
 * @version 12/05/2022
 */

public class ReceptionistGuestDetailsViewController extends ViewController
{
  @FXML private TextField emailField;
  @FXML private TextField firstNameField;
  @FXML private TextField bookingIDField;
  @FXML private TextField lastNameField;
  @FXML private TextField phoneNumberField;
  @FXML private Label errorLabel;

  private ReceptionistGuestDetailsViewModel viewModel;


  /**
   * A none argument, void method binding instance variables to
   * ViewModel variables.
   */
  @Override protected void init()
  {
    viewModel = getViewModelFactory().getGuestDetailsForReceptionistViewModel();
    try {
      emailField.textProperty().bindBidirectional(viewModel.getEmailProperty());
      firstNameField.textProperty()
              .bindBidirectional(viewModel.getfNameProperty());
      lastNameField.textProperty()
              .bindBidirectional(viewModel.getlNameProperty());
      bookingIDField.textProperty()
              .bindBidirectional(viewModel.getBookingIDProperty());
      phoneNumberField.textProperty()
              .bindBidirectional(viewModel.getPhoneNrProperty());
      errorLabel.textProperty()
              .bindBidirectional(viewModel.getErrorLabelProperty());
    }
    catch (NullPointerException e)
    {
      //can be null
    }

    reset();
  }

  /**
   * A method that refers to updateGuest() method in ViewModel
   */

  public void saveDetailsButton()
  {
      viewModel.updateGuest();
  }

  /**
   * A method that opens the ReceptionistBookingView.fxml
   */
  public void exitButton() throws IOException
  {
    getViewHandler().openView("ReceptionistBookingView.fxml");
    reset();
  }

  /**
   * A method that refers to reset() method in ViewModel
   */
  @Override public void reset()
  {
    errorLabel.setText("");
    viewModel.reset();
  }
}
