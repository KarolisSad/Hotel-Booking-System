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

  public TextField bookingIDtextField;
  public TextField statusTextField;
  public ComboBox typeDropdown;
  public TextField nrOfBedsField;
  public ComboBox typesDropdown;
  public DatePicker startDatePicker;
  public DatePicker endDatePicker;
  public Label errorLabel;
  private RoomDetailsForReceptionistModel viewModel;

  /**
   * No-argument void method initializing instance variables.
   */
  @Override protected void init()
  {

    viewModel = getViewModelFactory().getRoomDetailsForReceptionistModel();

    try
    {
      bookingIDtextField.textProperty()
          .bindBidirectional(viewModel.getBookingId());
      statusTextField.textProperty().bindBidirectional(viewModel.getStatus());
      typesDropdown.setItems(viewModel.getAvailableRoomNumbers());
      nrOfBedsField.textProperty().bindBidirectional(viewModel.getNrOfBeds());
      startDatePicker.valueProperty()
          .bindBidirectional(viewModel.getStartDatePicker());
      endDatePicker.valueProperty()
          .bindBidirectional(viewModel.getEndDatePicker());

      typeDropdown.getItems().removeAll(typeDropdown.getItems());
      typeDropdown.getItems().add(RoomType.FAMILY);
      typeDropdown.getItems().add(RoomType.DOUBLE);
      typeDropdown.getItems().add(RoomType.SINGLE);
      typeDropdown.getItems().add(RoomType.SUITE);

      errorLabel.textProperty()
          .bindBidirectional(viewModel.getErrorLabelProperty());

    }
    catch (NullPointerException e)
    {
      viewModel.setErrorLabel("Please fill in all empty fields");
    }
  }

  /**
   * A method that provides functionality for save details button.
   * The the button is click a confirmation window will pop up.
   *
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
      viewModel.saveBookingChanged();
      System.out.println("You confirmed.");
      getViewHandler().openView("BookingForReceptionistView.fxml");
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
   *
   * @throws IOException
   */
  public void cancelBookingButton() throws IOException
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
    alert.setHeaderText("Are you sure you want to cancel the booking");

    ButtonType confirm = new ButtonType("Confirm");
    ButtonType cancel = new ButtonType("Cancel",
        ButtonBar.ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(confirm, cancel);
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == confirm)
    {
      viewModel.removeBooking();
      System.out.println("The booking is canceled.");
      getViewHandler().openView("BookingForReceptionistView.fxml");
    }
    else
    {
      System.out.println("You pressed NO");
      alert.close();
    }
  }

  /**
   * A method that provides functionality for the exit button.
   * When pressed the program goes back to BookingForReceptionistView
   *
   * @throws IOException
   */
  public void exitButton() throws IOException
  {
    getViewHandler().openView("BookingForReceptionistView.fxml");
  }

  @Override public void reset()
  {
  }
}
