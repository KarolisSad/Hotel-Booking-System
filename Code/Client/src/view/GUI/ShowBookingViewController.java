package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.ViewController;
import viewModel.ShowBookingViewModel;

import java.io.IOException;

/**
 * Class representing the ShowBookingView window.
 *
 * @author Group 5
 * @version 18-05-2022
 */
public class ShowBookingViewController extends ViewController
{
  @FXML private TextField inputPhoneField;
  @FXML private TextField inputBookingNrField;
  @FXML private TextField outPutNameField;
  @FXML private TextField outPutPhoneField;
  @FXML private TextField outputStartDateField;
  @FXML private TextField outputEndDateField;
  @FXML private TextField outputRoomNrField;
  @FXML private TextField outputBedsField;
  @FXML private Label errorLabel;

  @FXML private Button clearButton;
  private ShowBookingViewModel viewModel;

  /**
   * Override of abstract init method from ViewController class.
   * Used to initialize all instance variables.
   * The two input fields have bi-directional bindings to the corresponding properties in the viewModel, as the user is expected to make changes to these.
   * The 4 output fields have regular bindings to the corresponding properties from the viewModel, as these are going to be populated with information gotten through the model.
   */
  @Override protected void init()
  {
    viewModel = getViewModelFactory().getShowBookingViewModel();

    // Input Binding:
    inputPhoneField.textProperty()
        .bindBidirectional(viewModel.inputPhoneNrProperty());

    inputBookingNrField.textProperty()
        .bindBidirectional(viewModel.inputBookingNrProperty());

    // Output Binding:
    outPutNameField.textProperty().bind(viewModel.outPutNameProperty());
    outPutPhoneField.textProperty().bind(viewModel.outPutPhoneProperty());

    outputRoomNrField.textProperty().bind(viewModel.outPutRoomNrProperty());
    outputBedsField.textProperty().bind(viewModel.outPutNrBedsProperty());

    // ErrorLabel binding
    errorLabel.textProperty().bindBidirectional(viewModel.errorLabelProperty());
  }

  /**
   * Method overriding the reset() method from the ViewController class.
   * Unbinds the two date fields and sets the text shown in them to an empty string.
   * Calls the reset method in the viewModel.
   */
  @Override public void reset()
  {
    outputStartDateField.textProperty().unbind();
    outputEndDateField.textProperty().unbind();
    outputStartDateField.setText("");
    outputEndDateField.setText("");
    viewModel.reset();
  }

  /**
   * Method called when pressing the find booking button.
   * Calls the findBooking() method in the viewModel, and binds the two datefields to the corresponding properties.
   * If an exception is thrown, the error label is updated to show the error message.
   */
  @FXML private void findBookingButtonClick()
  {
    try
    {
      viewModel.findBooking();
      outputStartDateField.textProperty()
          .bind(viewModel.outPutStartDateProperty().asString());
      outputEndDateField.textProperty()
          .bind(viewModel.outPutEndDateProperty().asString());
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
    }

  }

  /**
   * Method called when the clear button is pressed.
   * Calls the reset method.
   */
  @FXML private void clearButtonClicked()
  {
    reset();
  }

  /**
   * Method called when pressing the main menu button.
   * Calls the reset method, and then opens the LogInView window.
   * @throws IOException
   */
  @FXML private void mainMenuButtonClicked() throws IOException
  {
    reset();
    getViewHandler().openView("LogInView.fxml");
  }
}
