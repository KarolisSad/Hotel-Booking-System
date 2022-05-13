package viewModel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.RoomTransfer;
import model.Model;
import model.Room;
import model.RoomType;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RoomListViewModel implements PropertyChangeListener
{
  private Model model;
  private ViewState state;

  private ObservableList<SimpleRoomViewModel> allRooms;

  private ObjectProperty<SimpleRoomViewModel> selectedRoomProperty;
  private SimpleStringProperty errorLabel;

  public RoomListViewModel(Model model, ViewState state)
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
    System.out.println(roomTransfer.toString());
    if (roomTransfer.getMessage() == null)
    {
      for (int i = 0; i < roomTransfer.getRoomList().size(); i++)
      {
        allRooms.add(new SimpleRoomViewModel(roomTransfer.getRoomList().get(i)));
      }
    }
    else {
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
        errorLabel.setValue("Room: " + roomId + " deleted successfully");
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

  public void setEdit()
  {
    state.setNumber(selectedRoomProperty.get().roomNumberProperty().get());
    state.setAdd(false);
  }

  public void setAdd()
  {
    state.setAdd(true);
  }
}


