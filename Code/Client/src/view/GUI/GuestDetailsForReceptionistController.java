package view.GUI;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.ViewController;
import viewModel.GuestDetailsForReceptionistViewModel;

import java.io.IOException;

/**
 * A class creating an GuestDetailsForReceptionistController object.
 *
 * @author Group 5
 * @version 12/05/2022
 */

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

  /**
   * An empty constructor.
   */

  public GuestDetailsForReceptionistController(){
  }

  /**
   * A none argument, void method binding instance variables to
   * ViewModel variables.
   */
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
  }

  /**
   * A method that refers to updateGuest() method in ViewModel
   */

  public void saveDetailsButton() {
    try {
      viewModel.updateGuest();
    }catch(Exception e){
      errorLabel.setText(e.getMessage());
    }
  }

  /**
   * A method that opens the BookingsForReceptionistView.fxml
   */
  public void exitButton() throws IOException {
    reset();
    getViewHandler().openView("BookingsForReceptionistView.fxml");
  }

  /**
   * A method that refers to reset() method in ViewModel
   */
  @Override
  public void reset() {
    viewModel.reset();
  }
}
