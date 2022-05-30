package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.ViewController;
import viewModel.HotelManagerBookingViewModel;
import viewModel.Helpers.SimpleBookingViewModel;

import java.io.IOException;
import java.time.LocalDate;

/**
 * A class creating HotelManagerBookingViewController object.
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class HotelManagerBookingViewController extends ViewController
{
  @FXML
    private Button cancelButton;

    private HotelManagerBookingViewModel viewModel;

  @FXML private TableView<SimpleBookingViewModel> table;
  @FXML private TableColumn<SimpleBookingViewModel, LocalDate> endDate;
  @FXML private TableColumn<SimpleBookingViewModel, LocalDate> startDate;
  @FXML private TableColumn<SimpleBookingViewModel, String> roomID;
  @FXML private TableColumn<SimpleBookingViewModel, Integer> guestPhoneNr;
  @FXML private TableColumn<SimpleBookingViewModel, String> state;
  @FXML private TableColumn<SimpleBookingViewModel, Integer> bookingID;

  /**
   * A none argument, void method initializing instance variables.
   */
  @Override protected void init()
  {
    this.viewModel = getViewModelFactory().getBookingViewModel();

    startDate.setCellValueFactory(
        cellDate -> cellDate.getValue().startDateProperty());
    endDate.setCellValueFactory(
        cellData -> cellData.getValue().endDateProperty());
    guestPhoneNr.setCellValueFactory(
        cellData -> cellData.getValue().guestIdProperty().asObject());
    roomID.setCellValueFactory(
        cellData -> cellData.getValue().roomIdProperty());
    bookingID.setCellValueFactory(
        cellData -> cellData.getValue().bookingIdProperty().asObject());
    state.setCellValueFactory(
        cellData -> cellData.getValue().bookingStateProperty());
    table.setItems(viewModel.getBookings());


    table.getSelectionModel().selectedItemProperty()
        .addListener((n, oldValue, newValue) -> {
          if (newValue != null)
          {
            cancelButton.setDisable(true);
            viewModel.setSelected(newValue.bookingIdProperty().get());
            if (newValue.bookingStateProperty().get().equals("Booked"))
            {
              cancelButton.setDisable(false);
            }
          }
        });

    reset();
  }

  /**
   * Override of abstract method reset.
   * Updates the list of bookings shown in the table.
   */
  @Override public void reset()
  {
    viewModel.updateBookingList();
    cancelButton.setDisable(true);
  }

  /**
   * Method called when pressing the cancel booking button with a booking selected.
   * Calls the cancel booking method in the viewModel.
   */
  public void cancelTheBookingButton()
  {
    viewModel.cancelBooking();
    reset();
  }

  /**
   * A void method opening the menu view.
   */
  public void menuButton() throws IOException
  {
    getViewHandler().openView("HotelManagerMenu.fxml");
  }

  /**
   * Method used for hiding cancelled bookings from the list.
   * Calls the removeCanceledBookings method from the viewModel.
   */
  public void hideCancelledBookings()
  {
    viewModel.removeCanceledBookings();
    viewModel.removeCanceledBookings();
    viewModel.removeCanceledBookings();
    viewModel.removeCanceledBookings();
  }

  /**
   * Method used for showing cancelled bookings in the list.
   * Calls the showCancelledBookings method in the viewModel.
   */
  public void showCancelledBookings()
  {
    viewModel.showCancelledBookings();

  }

}
