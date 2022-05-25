package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.RoomType;
import view.ViewController;
import viewModel.RoomDetailsForReceptionistModel;

import java.awt.*;
import java.awt.Button;
import java.io.IOException;
import java.util.Optional;

/**
 * A class creating a RoomDetailsForReceptionist Object
 *
 * @author Group 5
 * @version 13-05-2022
 */
public class RoomDetailsForReceptionistController extends ViewController
{

  @FXML private TextField bookingIDtextField;
  @FXML private TextField statusTextField;
  @FXML private TextField roomNumberField;
  @FXML private TextField nrOfBedsField;
  @FXML private TextField typeField;
  @FXML private TextField dailyPriceField;
  @FXML private DatePicker startDatePicker;
  @FXML private DatePicker endDatePicker;
  @FXML private Label errorLabel;

  private RoomDetailsForReceptionistModel viewModel;

  /**
   * No-argument void method initializing instance variables.
   */
  @Override protected void init()
  {

    viewModel = getViewModelFactory().getRoomDetailsForReceptionistModel();

    try
    {
      bookingIDtextField.textProperty().bindBidirectional(viewModel.getBookingId());
      statusTextField.textProperty().bindBidirectional(viewModel.getStatus());
      typeField.textProperty().bindBidirectional(viewModel.getType());
      nrOfBedsField.textProperty().bindBidirectional(viewModel.getNrOfBeds());
      roomNumberField.textProperty().bindBidirectional(viewModel.getRoomNumber());
      dailyPriceField.textProperty().bindBidirectional(viewModel.getDailyPrice());
      startDatePicker.valueProperty()
          .bindBidirectional(viewModel.getStartDatePicker());
      endDatePicker.valueProperty()
          .bindBidirectional(viewModel.getEndDatePicker());
      errorLabel.textProperty()
          .bindBidirectional(viewModel.getErrorLabelProperty());

    }
    catch (NullPointerException e)
    {
      viewModel.setErrorLabel("Please fill in all empty fields");
    }

    reset();
  }

  /**
   * A method that provides functionality for save details button.
   * The the button is click a confirmation window will pop up.
   * @throws IOException
   */
  public void saveDetailsButton() throws IOException
  {
    //Confirmation box
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    //Style
    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.getStylesheets().add("");
    dialogPane.getStylesheets()
        .add(getClass().getResource("box.css").toExternalForm());
    dialogPane.getStyleClass().add("box.css");
    //
    alert.setHeaderText("Are you sure you want to make changes?");

    ButtonType confirm = new ButtonType("Confirm");
    ButtonType cancel = new ButtonType("Cancel",
        ButtonBar.ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(confirm, cancel);
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == confirm)
    {
      try{
        viewModel.saveBookingChanged();
        getViewHandler().openView("BookingsForReceptionistView.fxml");
      }
      catch (IllegalStateException e)
      {
        errorLabel.textProperty().set(e.getMessage());
      }
    }
    else
    {
      System.out.println("You pressed NO");
      alert.close();
    }
  }

  /**
   * A method that provides functionality for the cancel button.
   * The the button is click a confirmation window will pop up.
   * @throws IOException
   */
  public void cancelBookingButton() throws IOException
  {
    if (statusTextField.textProperty().get().equalsIgnoreCase("booked"))
    {
      //Confirmation box
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      //Style
      DialogPane dialogPane = alert.getDialogPane();
      dialogPane.getStylesheets().add("");
      dialogPane.getStylesheets().add(getClass().getResource("box.css").toExternalForm());
      dialogPane.getStyleClass().add("box.css");
      //
      alert.setHeaderText("Are you sure you want to cancel the booking");

      ButtonType confirm = new ButtonType("Confirm");
      ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
      alert.getButtonTypes().setAll(confirm, cancel);
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == confirm)
      {

        viewModel.removeBooking();
        getViewModelFactory().getBookingsForReceptionistViewModel().reset();
        getViewHandler().openView("BookingsForReceptionistView.fxml");
        System.out.println("The booking is canceled.");
      }
      else
      {
        System.out.println("You pressed NO");
        alert.close();
      }
    }

    else {errorLabel.textProperty().set("You cannot cancel a booking in progress. Check guest out instead.");}
  }

  /**
   * A method that provides functionality for the exit button.
   * When pressed the program goes back to BookingForReceptionistView
   * @throws IOException
   */
  public void exitButton() throws IOException
  {
    getViewHandler().openView("BookingsForReceptionistView.fxml");
  }

  /**
   * Void method resetting the view.
   * This is done by setting the errorlabel to show an empty string.
   */
  @Override public void reset()
  {
    errorLabel.setText("");
  }
}
