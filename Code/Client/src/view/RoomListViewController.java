package view;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import viewModel.RoomListViewModel;
import viewModel.SimpleRoomViewModel;
import viewModel.ViewModelFactory;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Optional;

public class RoomListViewController extends ViewController
{

  @FXML private Button editButton;
  @FXML private Button removeButton;
  @FXML private TableView<SimpleRoomViewModel> roomTable;
  @FXML private TableColumn<SimpleRoomViewModel, String> numberColumn;
  @FXML private TableColumn<SimpleRoomViewModel, String> typeColumn;
  @FXML private TableColumn<SimpleRoomViewModel, Integer> numberOfBedsColumn;
  @FXML private Label errorLabel;
  private Region root;
  private ViewHandler viewHandler;
  private RoomListViewModel viewModel;

  @Override public void init()
  {
    // Bindings
    numberColumn.setCellValueFactory(
        cellData -> cellData.getValue().roomNumberProperty());
    typeColumn.setCellValueFactory(
        cellData -> cellData.getValue().roomTypeProperty().asString());
    numberOfBedsColumn.setCellValueFactory(
        cellData -> cellData.getValue().numberOfBedsProperty().asObject());
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
   * A void method initializing instance variables.
   *
   * @param viewHandler      A ViewHandler object which will be used to set the instance variable.
   * @param viewModelFactory A ViewModelFactory object which will be used to set the instance variable.
   * @param root             A Region object which will be used to set the instance variable.
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory,
      Region root) throws RemoteException
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
    this.viewModel = viewModelFactory.getRoomListViewModel();
    init();
  }

  public Region getRoot()
  {
    return root;
  }

  @Override public void reset()
  {
    viewModel.updateRoomList();


  }

  public void addButton() throws IOException
  {
    viewModel.setAdd();
    viewHandler.openView("AddEditView.fxml");

  }

  /**
   * Method called when clicking the edit button when a room is selected.
   * If a room is not selected, button will be inactive - and thus, unable to be clicked
   *
   * After successfully editing a room, a confirmation message is shown.
   */
  public void editButton() throws IOException
  {
    viewModel.setEdit();
    viewHandler.openView("AddEditView.fxml");

    errorLabel.setTextFill(Color.GREEN);
    errorLabel.textProperty().set("Room: " + roomTable.getSelectionModel()
        .getSelectedItem().roomNumberProperty().get()  + " changed successfully");
  }

  /**
   * Method called when clicking the delete button.
   * If a room is not selected, button will be inactive - and thus, unable to be clicked.
   * if a room is selected, a pop-up will show, and if confirm is pressed the removeRoom-method from RoomListViewModel will be called.
   * After successfully removing a room, a confirmation message will show.
   */
  public void removeButton()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    //Style
    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.getStylesheets().add("");
    dialogPane.getStylesheets().add(getClass().getResource("box.css").toExternalForm());
    dialogPane.getStyleClass().add("box.css");
    //
    alert.setHeaderText(
        "Confirm deletion of room: " + roomTable.getSelectionModel()
            .getSelectedItem().roomNumberProperty().get());

    ButtonType confirm = new ButtonType("Confirm");
    ButtonType cancel = new ButtonType("Cancel",
        ButtonBar.ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(confirm, cancel);

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == confirm)
    {
      errorLabel.setTextFill(Color.GREEN);
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
}
