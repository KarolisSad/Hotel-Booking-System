package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.ViewController;
import viewModel.ShowBookingViewModel;

import java.io.IOException;

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

  @Override public void reset()
  {
    outputStartDateField.textProperty().unbind();
    outputEndDateField.textProperty().unbind();
    outputStartDateField.setText("");
    outputEndDateField.setText("");
    viewModel.reset();
  }

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

  @FXML private void clearButtonClicked()
  {
    reset();
  }

  @FXML private void mainMenuButtonClicked() throws IOException
  {
    reset();
    getViewHandler().openView("LogInView.fxml");
  }
}
