package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewModel.BookingsForReceptionistViewModel;

public class BookingsForReceptionistViewController extends ViewController
{

  @FXML private TableView bookingsTable;
  @FXML private TableColumn bookingIdColumn;
  @FXML private TableColumn startDateColumn;
  @FXML private TableColumn endDateColumn;
  @FXML private TableColumn guestColumn;
  @FXML private TableColumn roomNumberColumn;
  @FXML private TableColumn stateColumn;
  @FXML private Label errorLabel;

  private BookingsForReceptionistViewModel viewModel;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getBookingsForReceptionistViewModel();

//    bookingList.setItems(viewModel.getFutureBookings());
  }

  @Override public void reset()
  {

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
