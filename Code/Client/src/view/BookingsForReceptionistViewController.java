package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewModel.BookingsForReceptionistViewModel;
import viewModel.SimpleBookingViewModel;

import java.time.LocalDate;

public class BookingsForReceptionistViewController extends ViewController
{

  @FXML private Button checkIn;
  @FXML private Button bookedButton;
  @FXML private Button inProgressButton;
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

   checkIn.setDisable(true);
    bookedButton.setDisable(true);



    bookingsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue != null)
      {
        if (newValue.bookingStateProperty().getValue().equalsIgnoreCase("Booked"))
        {
          checkIn.setDisable(false);
        }
        viewModel.setSelected(newValue);
        System.out.println(newValue.bookingStateProperty().getValue());
      }
    });

    reset();
  }

  @Override public void reset()
  {
    viewModel.reset();
  }

  public void showInProgressButton()
  {
    viewModel.updateBookingList("InProgress");
    inProgressButton.setDisable(true);
    bookedButton.setDisable(false);
    checkIn.setDisable(true);
  }

  public void showBookedButton()
  {
    viewModel.updateBookingList("booked");
    bookedButton.setDisable(true);
    inProgressButton.setDisable(false);
    checkIn.setDisable(true);
  }

  public void guestInformationButton(ActionEvent actionEvent)
  {
  }

  public void roomInformationButton(ActionEvent actionEvent)
  {
  }

  public void checkInButton()
  {
  }

}
