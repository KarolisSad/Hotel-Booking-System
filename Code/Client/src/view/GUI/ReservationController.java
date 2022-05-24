package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import mediator.RoomTransfer;
import view.ViewController;
import viewModel.ReservationViewModel;
import viewModel.SimpleRoomViewModel;
import viewModel.ViewModelFactory;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * A class creating an ReservationController object.
 *
 * @author Group 5
 * @version 04/05/2022
 */
public class ReservationController extends ViewController
{
  @FXML private TableView<SimpleRoomViewModel> availableRoomsTable;
  @FXML private TableColumn<SimpleRoomViewModel, String> roomNumberColumn;
  @FXML private TableColumn<SimpleRoomViewModel, String> roomTypeColumn;
  @FXML private TableColumn<SimpleRoomViewModel, Integer> numberOfBedsColumn;
  @FXML private TableColumn<SimpleRoomViewModel, Integer> dailyPriceColumn;
  @FXML private TableColumn<SimpleRoomViewModel, Integer> totalPriceColumn;
  @FXML private DatePicker startDate;
  @FXML private DatePicker endDate;
  @FXML private Label errorLabel;
  private ReservationViewModel viewModel;

  /**
   * A none argument, void method initializing instance variables.
   */
  @Override protected void init()
  {

    viewModel = getViewModelFactory().getReservationViewModel();

    // Binding
    startDate.valueProperty().bindBidirectional(viewModel.getStartDatePicker());
    endDate.valueProperty().bindBidirectional(viewModel.getEndDatePicker());
    errorLabel.textProperty().bind(viewModel.getErrorLabel());


    roomNumberColumn.setCellValueFactory(cellData -> cellData.getValue()
        .roomNumberProperty());
    roomTypeColumn.setCellValueFactory(cellData -> cellData.getValue().roomTypeProperty().asString());
    numberOfBedsColumn.setCellValueFactory(cellData -> cellData.getValue().numberOfBedsProperty().asObject());
    dailyPriceColumn.setCellValueFactory(cellData -> cellData.getValue().dailyPriceProperty().asObject());

    availableRoomsTable.setItems(viewModel.getRooms());

    availableRoomsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
      viewModel.setSelected(newValue);
    });
  }

  /**
   * Method used for resetting the view.
   * This is done by calling the clear() method in the viewModel.
   */
  @Override public void reset()
  {
    viewModel.getAllAvailableRooms();
  }

  /**
   * A void method that looks for available rooms.
   */

  public void lookForAvailableRooms()
  {
    viewModel.getAllAvailableRooms();
  }

  /**
   * A void method  opening the GuestInformation view.
   */

  public void reservationButton() throws IOException
  {
    viewModel.bookARoom(viewModel.getSelected().get().roomNumberProperty().get());
    reset();
  }

  /**
   * Method called when clicking the back button in the GUI.
   * Opens the LogInView view.
   * @throws IOException
   */
  public void back() throws IOException
  {
    getViewHandler().openView("GuestMenuView.fxml");
  }



}