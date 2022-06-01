package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import view.ViewController;
import viewModel.ReceptionistBookingViewModel;
import viewModel.Helpers.SimpleBookingViewModel;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * A class creating an ReceptionistBookingViewController object.
 *
 * @author Group 5
 * @version 23/05/2022
 */
public class ReceptionistBookingViewController extends ViewController
{

  @FXML
  private Button guestInfoButton;
  @FXML
  private Button roomInfoButton;
  @FXML private Button checkIn;
  @FXML private Button bookedButton;
  @FXML private Button inProgressButton;
  @FXML private TableView<SimpleBookingViewModel> bookingsTable;
  @FXML private TableColumn<SimpleBookingViewModel, Integer> bookingIdColumn;
  @FXML private TableColumn<SimpleBookingViewModel, LocalDate> startDateColumn;
  @FXML private TableColumn<SimpleBookingViewModel, LocalDate> endDateColumn;
  @FXML private TableColumn<SimpleBookingViewModel, String> guestColumn;
  @FXML private TableColumn<SimpleBookingViewModel, String> roomNumberColumn;
  @FXML private TableColumn<SimpleBookingViewModel, String> stateColumn;
  @FXML private Label errorLabel;

  private ReceptionistBookingViewModel viewModel;

  /**
   * A none argument, void method binding instance variables to
   * ViewModel variables.
   */
  @Override protected void init()
  {
    viewModel = getViewModelFactory().getBookingsForReceptionistViewModel();

    bookingIdColumn.setCellValueFactory(
        cellData -> cellData.getValue().bookingIdProperty().asObject());
    startDateColumn.setCellValueFactory(
        cellData -> cellData.getValue().startDateProperty());
    endDateColumn.setCellValueFactory(
        cellData -> cellData.getValue().endDateProperty());
    guestColumn.setCellValueFactory(
        cellData -> cellData.getValue().guestNameProperty());
    roomNumberColumn.setCellValueFactory(
        cellData -> cellData.getValue().roomIdProperty());
    stateColumn.setCellValueFactory(
        cellData -> cellData.getValue().bookingStateProperty());

    errorLabel.textProperty().bindBidirectional(viewModel.getErrorLabel());

    bookingsTable.setItems(viewModel.getBookings());

    checkIn.setDisable(true);
    bookedButton.setDisable(true);

    bookingsTable.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldValue, newValue) -> {
          if (newValue != null)
          {
            if (newValue.bookingStateProperty().getValue()
                .equalsIgnoreCase("Booked") || newValue.bookingStateProperty()
                .getValue().equalsIgnoreCase("in progress"))
            {
              checkIn.setDisable(false);
            }
            viewModel.setSelected(newValue);
            guestInfoButton.setDisable(false);
            roomInfoButton.setDisable(false);
          }
        });

    reset();
  }

  /**
   * Method calling the reset method in the ViewModel
   */
  @Override public void reset()
  {
    inProgressButton.setDisable(false);
    bookedButton.setDisable(true);
    checkIn.setDisable(true);
    guestInfoButton.setDisable(true);
    roomInfoButton.setDisable(true);
    viewModel.reset();

colorAccordingToDays();

  }

  /**
   * Method called when pressing the showInProgress button in the GUI.
   * Updates the booking list to show all booking In progress, disables the showInProgressButton, and changes the text on the check-in button to Check out.
   */
  public void showInProgressButton()
  {
    viewModel.updateBookingList("InProgress");
    inProgressButton.setDisable(true);
    bookedButton.setDisable(false);
    checkIn.setDisable(true);
    checkIn.setText("Check out");
  }

  /**
   * Method called when pressing the showBooked button in the GUI.
   * Updates the booking list to show all booking In progress, disables the showBooked button, and changes the text on the check-in button to Check in.
   */
  public void showBookedButton()
  {
    reset();
    viewModel.updateBookingList("booked");
    bookedButton.setDisable(true);
    inProgressButton.setDisable(false);
    checkIn.setDisable(true);
    checkIn.setText("Check in");
  }

  /**
   * Method called when pressing the guest information button in the GUI.
   * Opens a window containing the guest information for the selected booking,
   * which will allow the receptionist to make changes to the personal information of the guest.
   *
   * @throws IOException
   */
  public void guestInformationButton() throws IOException
  {
    getViewHandler().openView("ReceptionistGuestDetailsView.fxml");
    getViewModelFactory().getGuestDetailsForReceptionistViewModel()
        .setGuest(viewModel.getSelectedBookingProperty().bookingIdProperty(),
            viewModel.getSelectedBookingProperty().guestProperty());
  }

  /**
   * Method called when pressing the room information button in the GUI.
   * Opens a window containing the booking information for the selected booking,
   * which will allow the receptionist to make changes to the booking or cancel it.
   *
   * @throws IOException
   */
  public void roomInformationButton() throws IOException
  {
    getViewHandler().openView("ReceptionistRoomAndBookingDetailsView.fxml");
    getViewModelFactory().getRoomDetailsForReceptionistModel()
        .setRoomBookingDetails(viewModel.getSelectedBookingProperty());
  }

  /**
   * Method called when clicking the check in (or check out) button with a booking selected.
   * creates a pop up window containing information about the costumer, and gives the ability to either confirm or go back.
   * If confirm is choosen, the processBooking method in the viewModel is called.
   */
  public void checkInButton()
  {
    Alert popUp = new Alert(Alert.AlertType.CONFIRMATION);
    //Style
    DialogPane dialogPane = popUp.getDialogPane();
    dialogPane.getStylesheets().add("");
    dialogPane.getStylesheets()
        .add(getClass().getResource("box.css").toExternalForm());
    dialogPane.getStyleClass().add("box.css");

    if (viewModel.isCheckIn())
    {
      popUp.setHeaderText("Confirm Check in: booking nr: "
          + viewModel.getSelectedBookingProperty().bookingIdProperty().get());
      popUp.setContentText(
          "Guest:\n" + "Name: " + viewModel.getSelectedBookingProperty()
              .guestProperty().get().getfName() + " "
              + viewModel.getSelectedBookingProperty().guestProperty().get()
              .getlName() + "\nEmail: " + viewModel.getSelectedBookingProperty()
              .guestProperty().get().getEmail() + "\nPhone: "
              + viewModel.getSelectedBookingProperty().guestProperty().get()
              .getPhoneNr() + "\nRoom: "
              + viewModel.getSelectedBookingProperty().roomIdProperty()
              .getValue() + "\nStart date: "
              + viewModel.getSelectedBookingProperty().getStartDate()
              + "\nEnd date: " + viewModel.getSelectedBookingProperty()
              .getEndDate());

      ButtonType checkIn = new ButtonType("Check in");
      ButtonType cancel = new ButtonType("Back",
          ButtonBar.ButtonData.CANCEL_CLOSE);
      popUp.getButtonTypes().setAll(checkIn, cancel);

      Optional<ButtonType> result = popUp.showAndWait();
      if (result.get() == checkIn)
      {
        if (viewModel.getSelectedBookingProperty().getStartDate()
            .isEqual(LocalDate.now()))
        {
          viewModel.processBooking(viewModel.getSelectedBookingProperty());
          reset();
        }
        else
        {
          errorLabel.setText("You can't check in if the start date is not today");
        }
      }
      else
      {
        popUp.close();
      }
    }
    else
    {
      popUp.setHeaderText("Confirm Check out: Booking nr: "
          + viewModel.getSelectedBookingProperty().bookingIdProperty().get());
      popUp.setContentText(
          "Guest:\n" + "Name: " + viewModel.getSelectedBookingProperty()
              .guestProperty().get().getfName() + " "
              + viewModel.getSelectedBookingProperty().guestProperty().get()
              .getlName() + "\nEmail: " + viewModel.getSelectedBookingProperty()
              .guestProperty().get().getEmail() + "\nPhone: "
              + viewModel.getSelectedBookingProperty().guestProperty().get()
              .getPhoneNr() + "\nRoom: "
              + viewModel.getSelectedBookingProperty().roomIdProperty()
              .getValue() + "\nStart date: "
              + viewModel.getSelectedBookingProperty().getStartDate()
              + "\nEnd date: " + viewModel.getSelectedBookingProperty()
              .getEndDate());

      ButtonType checkIn = new ButtonType("Check out");
      ButtonType cancel = new ButtonType("Back",
          ButtonBar.ButtonData.CANCEL_CLOSE);
      popUp.getButtonTypes().setAll(checkIn, cancel);

      Optional<ButtonType> result = popUp.showAndWait();
      if (result.get() == checkIn)
      {
        if (viewModel.getSelectedBookingProperty().getEndDate()
            .isEqual(LocalDate.now()))
        {
          viewModel.processBooking(viewModel.getSelectedBookingProperty());
          reset();
        }
        else
        {
          errorLabel.setText("You can't check out if the end date is not today");
        }
      }
      else
      {
        popUp.close();
      }
    }
  }

  /**
   * Method used when the Menu button is clicked in the window.
   * It opens UserLoginMainView.
   */
  public void mainMenuButtonClicked()
  {
    try
    {
      getViewHandler().openView("MainMenu.fxml");
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }


  /**
   * Method used to color the rows in the table according the dates and states of the bookings.
   * If the start date is todays date, and the booking is in the booked state, the row will turn green,
   * and if the end date is today and the booking is in progress, the row will turn red.
   */
  private void colorAccordingToDays()
  {
    bookingsTable.setRowFactory(booking -> new TableRow<>() {
      @Override protected void updateItem(
          SimpleBookingViewModel booking, boolean empty)
      {
        super.updateItem(booking, empty);
        if (booking == null)
        {
          setStyle("");
        }
        else if (booking.bookingStateProperty().get().equals("Booked") && booking.getStartDate().isEqual(LocalDate.now()))
        {
          setStyle("-fx-background-color: green;");
        }
        else if (booking.bookingStateProperty().get().equals("In progress") && booking.getEndDate().isEqual(LocalDate.now()))
        {
          setStyle("-fx-background-color: red;");
        }
        else
        {
          setStyle("");
        }
      }
    });
  }
}
