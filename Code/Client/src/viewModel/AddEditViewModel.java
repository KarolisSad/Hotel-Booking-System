package viewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import mediator.RoomTransfer;
import model.Model;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;

/**
 * A class providing functionality for AddEditViewController.
 *
 * @version 09/05/2022
 */
public class AddEditViewModel
{
  private StringProperty roomId;
  private RoomType type;
  private ArrayList<RoomType> types;
  private IntegerProperty nrOfBeds;
  private ViewState viewState;
  private Model model;
  private StringProperty errorProperty;

  /**
   * Constructor initializing instance variables.
   *
   * @param model model interface
   * @param state shared object with a ReservationViewModel
   *              that checks if the view needs to be filled with information
   *              of the selected view or empty.
   */
  public AddEditViewModel(Model model, ViewState state)
  {
    this.model = model;
    this.viewState = state;

    roomId = new SimpleStringProperty();
    nrOfBeds = new SimpleIntegerProperty();
    type = null;
    types = new ArrayList<>();

    //List.of() makes a collection out of the simple Array.
    types.addAll(List.of(RoomType.values()));
    errorProperty = new SimpleStringProperty("");

    reset();
  }

  /**
   * A non argument method that that checks if the view needs to be filled with information
   * of the selected view or empty and creates the view accurately.
   */
  public void reset()
  {
    errorProperty.set("");

    if (viewState.isAdd())
    {
      roomId.set("");
      nrOfBeds.set(0);
      setType(null);
    }
    else
    {
      roomId.setValue(viewState.getNumber());

      RoomTransfer roomTransfer = model.getAllRooms();

      for (int i = 0; i < roomTransfer.getRoomList().size(); i++)
      {
        if (roomTransfer.getRoomList().get(i).getRoomId().equals(roomId.get()))
        {
          nrOfBeds.set(roomTransfer.getRoomList().get(i).getNumberOfBeds());
          setType(roomTransfer.getRoomList().get(i).getRoomType());
        }
      }
    }

  }

  /**
   * A getter method that returns a String object
   * taken from a StringProperty.
   *
   * @return a String object
   */
  public String getRoomId()
  {
    return roomId.get();
  }

  /**
   * A getter method that returns a StringProperty object
   *
   * @return a StringProperty object called roomId
   */
  public StringProperty getRoomIdProperty()
  {
    return roomId;
  }

  /**
   * A getter method that returns a ViewState object
   *
   * @return a ViewState object called viewState
   */
  public ViewState getViewState()
  {
    return viewState;
  }

  /**
   * A getter method that returns a Integer object
   *
   * @return an Integer object
   */
  public int getNumberOfBeds()
  {
    return nrOfBeds.get();
  }

  /**
   * A getter method that returns a IntegerProperty object
   *
   * @return a IntegerProperty object called nrOfBeds
   */
  public IntegerProperty numberOfBedsProperty()
  {
    return nrOfBeds;
  }

  /**
   * A non argument method calls an addRoom(String roomId, RoomTypes type, int nrBeds)
   * from the model
   */

  public void addRoom()
  {
    RoomTransfer roomTransfer = model.addRoom(roomId.get(), type,
        nrOfBeds.get());
    if (roomTransfer.getMessage() == null)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information");
      alert.setHeaderText(null);
      alert.setContentText("Room successfully added!");

      alert.showAndWait();    }
    else
    {
      errorProperty.set(roomTransfer.getMessage());
    }
    reset();
  }

  /**
   * A non argument method calls an editRoomInfo(String roomId, RoomTypes type, int nrBeds)
   * from the model
   */
  public void editRoomInfo()
  {
    RoomTransfer roomTransfer = model.editRoomInfo(roomId.get(), type,
        nrOfBeds.get());
    if (roomTransfer.getMessage() == null)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information");
      alert.setHeaderText(null);
      alert.setContentText("Room edited successfully!");

      alert.showAndWait();    }
    else
    {
      errorProperty.set(roomTransfer.getMessage());
    }
  }

  /**
   * A non argument method setting a RoomTypes instance valuable called type.
   */
  public void setType(RoomType type)
  {
    this.type = type;
  }

  /**
   * A getter method that returns a String object
   *
   * @return a String object
   */
  public String getErrorProperty()
  {
    return errorProperty.get();
  }

  /**
   * A getter method that returns a StringProperty object
   *
   * @return a StringProperty object called nrOfBeds
   */
  public StringProperty errorPropertyProperty()
  {
    return errorProperty;
  }

  /**
   * A getter method for getting the type of the selected room.
   *
   * @return the type of the selected room, or null if no room is selected.
   */
  public RoomType getType()
  {
    return type;
  }
}