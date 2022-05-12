package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewModel.BookingsForReceptionistViewModel;
import viewModel.SimpleBookingViewModel;

import java.time.LocalDate;

public class BookingsForReceptionistViewController extends ViewController
{

  @FXML private TableView<SimpleBookingViewModel> bookingsTable;
  @FXML private TableColumn<SimpleBookingViewModel, Integer> bookingIdColumn;
  @FXML private TableColumn<SimpleBookingViewModel, LocalDate> startDateColumn;
  @FXML private TableColumn<SimpleBookingViewModel, LocalDate> endDateColumn;
  @FXML private TableColumn<SimpleBookingViewModel, Integer> guestColumn;
  @FXML private TableColumn<SimpleBookingViewModel, String> roomNumberColumn;
  @FXML private TableColumn<SimpleBookingViewModel, String> stateColumn;
  @FXML private Label errorLabel;

  private BookingsForReceptionistViewModel viewModel;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getBookingsForReceptionistViewModel();

    bookingIdColumn.setCellValueFactory(cellData -> cellData.getValue().bookingIdProperty().asObject());
    startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
    endDateColumn.setCellValueFactory(cellData -> cellData.getValue().endDateProperty());
    guestColumn.setCellValueFactory(cellData -> cellData.getValue().guestIdProperty().asObject());
    roomNumberColumn.setCellValueFactory(cellData -> cellData.getValue().roomIdProperty());
    stateColumn.setCellValueFactory(cellData -> cellData.getValue().bookingStateProperty());

    errorLabel.textProperty().bindBidirectional(viewModel.getErrorLabel());

    bookingsTable.setItems(viewModel.getBookings());

    bookingsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
      viewModel.setSelected(newValue);
    });

    reset();
  }

  @Override public void reset()
  {
    viewModel.reset();
  }

  public void showInProgressButton(ActionEvent actionEvent)
  {
  }

  public void showBookedButton(ActionEvent actionEvent)
  {
  }

  public void guestInformationButton(ActionEvent actionEvent)
  {
  }

  public void roomInformationButton(ActionEvent actionEvent)
  {
  }

  public void checkInButton(ActionEvent actionEvent)
  {
  }
}
