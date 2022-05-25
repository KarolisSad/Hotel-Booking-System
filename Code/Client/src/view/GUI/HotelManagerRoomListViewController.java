package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.ViewController;
import viewModel.HotelManagerRoomListViewModel;
import viewModel.Helpers.SimpleRoomViewModel;

import java.io.IOException;
import java.util.Optional;

/**
 * Class representation of the RoomList view
 *
 * @author Group 5
 * @version 18-05-2022
 */
public class HotelManagerRoomListViewController extends ViewController
{
  @FXML private Button editButton;
  @FXML private Button removeButton;
  @FXML private TableView<SimpleRoomViewModel> roomTable;
  @FXML private TableColumn<SimpleRoomViewModel, String> numberColumn;
  @FXML private TableColumn<SimpleRoomViewModel, String> typeColumn;
  @FXML private TableColumn<SimpleRoomViewModel, Integer> numberOfBedsColumn;
  @FXML private TableColumn<SimpleRoomViewModel, Integer> dailyPriceColumn;
  @FXML private Label errorLabel;
  private HotelManagerRoomListViewModel viewModel;

  /**
   * Implementation of abstract init method.
   * Takes no arguments and initializes all instance variables.
   */
  @Override protected void init()
  {
    viewModel = getViewModelFactory().getRoomListViewModel();
    // Bindings
    numberColumn.setCellValueFactory(
        cellData -> cellData.getValue().roomNumberProperty());
    typeColumn.setCellValueFactory(
        cellData -> cellData.getValue().roomTypeProperty().asString());
    numberOfBedsColumn.setCellValueFactory(
        cellData -> cellData.getValue().numberOfBedsProperty().asObject());
    dailyPriceColumn.setCellValueFactory(cellData -> cellData.getValue().dailyPriceProperty().asObject());
    errorLabel.textProperty().bindBidirectional(viewModel.getErrorLabel());

    roomTable.setItems(viewModel.getAllRooms());

    editButton.setDisable(true);
    editButton.setTooltip(new Tooltip(
        "Click this button to edit the room selected in the table above"));
    removeButton.setDisable(true);
    removeButton.setTooltip(new Tooltip(
        "Click this button to remove the room selected from the table above."));

    roomTable.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldValue, newValue) -> {
          viewModel.setSelected(newValue);
          editButton.setDisable(newValue == null);
          removeButton.setDisable(newValue == null);
        });

    reset();
  }

  /**
   * Reset method calling the updateRoomList() method from viewModel.
   */
  @Override public void reset()
  {
    viewModel.updateRoomList();
  }

  /**
   * Method called when the add button is pressed.
   * Calls the setAdd() method in the viewModel, and opens the AddEditView window.
   * @throws IOException
   */
  public void addButton() throws IOException
  {
    viewModel.setAdd();
    getViewHandler().openView("HotelManagerAddEditRoomView.fxml");
  }

  /**
   * Method called when clicking the edit button when a room is selected.
   * If a room is not selected, button will be inactive - and thus, unable to be clicked
   * After successfully editing a room, a confirmation message is shown.
   */
  public void editButton() throws IOException
  {
    viewModel.setEdit();
    getViewHandler().openView("HotelManagerAddEditRoomView.fxml");

  }

  /**
   * Method called when clicking the delete button.
   * If a room is not selected, button will be inactive - and thus, unable to be clicked.
   * if a room is selected, a pop-up will show, and if confirm is pressed the removeRoom-method from HotelManagerRoomListViewModel will be called.
   * After successfully removing a room, a confirmation message will show.
   */
  public void removeButton()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText(
        "Confirm deletion of room: " + roomTable.getSelectionModel()
            .getSelectedItem().roomNumberProperty().get());

    ButtonType confirm = new ButtonType("Confirm");
    ButtonType cancel = new ButtonType("Cancel",
        ButtonBar.ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(confirm, cancel);
    // Style
    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.getStylesheets().add("");
    dialogPane.getStylesheets()
        .add(getClass().getResource("box.css").toExternalForm());
    dialogPane.getStyleClass().add("box.css");
    //

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == confirm)
    {
      viewModel.removeRoom(
          roomTable.getSelectionModel().getSelectedItem().roomNumberProperty()
              .get());
      viewModel.updateRoomList();
    }
    else
    {
      alert.close();
    }

  }

  /**
   * Method called when the back button is pressed.
   * Opens the LogInView window.
   * @throws IOException
   */
  public void back() throws IOException
  {
    getViewHandler().openView("HotelManagerMenu.fxml");
  }
}