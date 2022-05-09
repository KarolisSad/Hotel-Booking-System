package viewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;

public class AddEditViewModel
{
  private StringProperty roomId;
  private RoomType type;
  private ArrayList<RoomType> types;
  private IntegerProperty nrOfBeds;
  private ViewState viewState;
  private Model model;
  private StringProperty errorProperty;

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

  public void reset()
  {
    errorProperty.set("");

    if (viewState.isAdd())
    {
      roomId.set("");
      nrOfBeds.set(0);
      type = null;
    }
    else
    {
      for (int i = 0; i < model.getAllRooms().size(); i++)
      {
        if (model.getAllRooms().get(i).getRoomId() == roomId.get())
        {
          model.getAllRooms().get(i).setRoomType(type);
          model.getAllRooms().get(i).setNumberOfBeds(nrOfBeds.get());
        }
      }
    }

  }

  public String getRoomId()
  {
    return roomId.get();
  }

  public StringProperty getRoomIdProperty()
  {
    return roomId;
  }

  public ViewState getViewState()
  {
    return viewState;
  }

  public int getNumberOfBeds()
  {
    return nrOfBeds.get();
  }

  public IntegerProperty numberOfBedsProperty()
  {
    return nrOfBeds;
  }

  public void addRoom()
  {
    model.addRoom(roomId.get(), type, nrOfBeds.get());
    System.out.println(model.getAllRooms());

    reset();
  }

  public void editRoomInfo()
  {
    model.editRoomInfo(roomId.get(), type.toString(), nrOfBeds.get());
  }


  public void setType(RoomType type)
  {
    this.type = type;
  }

  public String getErrorProperty()
  {
    return errorProperty.get();
  }




  public StringProperty errorPropertyProperty()
  {
    return errorProperty;
  }
}