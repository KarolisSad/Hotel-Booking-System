package viewModel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import mediator.RoomTransfer;
import model.Model;
import viewModel.Helpers.SimpleRoomViewModel;
import viewModel.Helpers.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A class meant for creating functionality for HotelManagerRoomListViewController
 *
 * @author Group 5
 * @version 18-05-22
 */
public class HotelManagerRoomListViewModel implements PropertyChangeListener
{
  private Model model;
  private ViewState state;

  private ObservableList<SimpleRoomViewModel> allRooms;

  private ObjectProperty<SimpleRoomViewModel> selectedRoomProperty;
  private SimpleStringProperty errorLabel;

  /**
   * Constructor initializing instance variables
   * @param model the model interface
   * @param state the ViewState object
   */
  public HotelManagerRoomListViewModel(Model model, ViewState state)
  {
    this.model = model;

    this.state = state;

    this.allRooms = FXCollections.observableArrayList();
    updateRoomList();

    this.errorLabel = new SimpleStringProperty("");
    this.selectedRoomProperty = new SimpleObjectProperty<>();
  }

  /**
   * Method for updating the list of rooms shown.
   * It clears the current list, and then gets all rooms from the model and adds them.
   */
  public void updateRoomList()
  {
    allRooms.clear();

    RoomTransfer roomTransfer = model.getAllRooms();
    if (roomTransfer.getMessage() == null)
    {
      for (int i = 0; i < roomTransfer.getRoomList().size(); i++)
      {
        allRooms.add(
            new SimpleRoomViewModel(roomTransfer.getRoomList().get(i)));
      }
    }
    else
    {
      errorLabel.setValue(roomTransfer.getMessage());
    }

  }

  /**
   * Method used for removing a room, by calling the corresponding method from the model.
   * After method has completed, the list of rooms is updated to reflect the changes.
   * If an exception is caught during this process, the error-label will be updated accordingly.
   *
   * @param roomId the id of the room to be deleted.
   */
  public void removeRoom(String roomId)
  {

    RoomTransfer roomTransfer = model.removeRoom(roomId);
    if (roomTransfer.getMessage() != null)
    {
      errorLabel.setValue(roomTransfer.getMessage());
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information");
      alert.setHeaderText(null);
      alert.setContentText("Room: " + roomId + " deleted successfully");

      alert.showAndWait();
      updateRoomList();
    }

  }

  /**
   * Method for getting the errorlabel.
   *
   * @return errorlabel
   */
  public SimpleStringProperty getErrorLabel()
  {
    return errorLabel;
  }

  /**
   * Method for getting the ObservableList containing all Rooms listed by ID's
   *
   * @return allRoomsByID.
   */
  public ObservableList<SimpleRoomViewModel> getAllRooms()
  {
    return allRooms;
  }

  /**
   * Method for setting the selectedRoomProperty
   *
   * @param roomVm The SimpleRoomViewModel to be set as selected, or null if nothing is selected.
   */
  public void setSelected(SimpleRoomViewModel roomVm)
  {
    selectedRoomProperty.set(roomVm);
  }

  /**
   * Method used for getting the selectedRoomProperty
   *
   * @return selectedRoomProperty
   */
  public ObjectProperty<SimpleRoomViewModel> getSelectedProperty()
  {
    return selectedRoomProperty;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "RoomRemove":
          removeRoom((String) evt.getNewValue());
          break;
      }
    });
  }

  /**
   * A method meant to set initial room values when the editing is suppose to be done.
   */
  public void setEdit()
  {
    state.setNumber(selectedRoomProperty.get().roomNumberProperty().get());
    state.setAdd(false);
  }

  /**
   * A method meant to call setAdd method on the state.
   */
  public void setAdd()
  {
    state.setAdd(true);
  }

  /**
   * A method meant to reset the error label to an empty string.
   */
  public void reset()
  {
    errorLabel.setValue("");
  }
}


