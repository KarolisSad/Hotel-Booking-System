package viewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
   * @param model    model interface
   * @param state    shared object with a ReservationViewModel
   *                 that checks if the view needs to be filled with information
   *                 of the selected view or empty.
   */
  public AddEditViewModel(Model model, ViewState state)
  {
    this.model = model;
    this.viewState = state;

    roomId = new SimpleStringProperty();
    nrOfBeds = new SimpleIntegerProperty();
    type = null;
    types = new ArrayList<>();

    System.out.println(viewState.isAdd());
    if (!viewState.isAdd())
    {
      System.out.println("DJDJ");
      roomId.setValue(viewState.getNumber());
    }
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
      type = null;
    }
    else
    {
      System.out.println("NOT ADD");
      roomId.setValue(viewState.getNumber());
      for (int i = 0; i < model.getAllRooms().size(); i++)
      {
        if (model.getAllRooms().get(i).getRoomId().equals(roomId.get()))
        {
          System.out.println("WUHU!");
          nrOfBeds.set(model.getAllRooms().get(i).getNumberOfBeds());
          setType(model.getAllRooms().get(i).getRoomType());
          System.out.println("TYPE: " + type);


          System.out.println("ROOM ID: " + roomId.get());
          System.out.println("ROOM TYPE: " + type);
          System.out.println("NUMBER BEDS: " + getNumberOfBeds());
        }
      }
    }

  }

  /**
   * A getter method that returns a String object
   * taken from a StringProperty.
   * @return a String object
   */
  public String getRoomId()
  {
    return roomId.get();
  }

  /**
   * A getter method that returns a StringProperty object
   * @return a StringProperty object called roomId
   */
  public StringProperty getRoomIdProperty()
  {
    return roomId;
  }

  /**
   * A getter method that returns a ViewState object
   * @return a ViewState object called viewState
   */
  public ViewState getViewState()
  {
    return viewState;
  }

  /**
   * A getter method that returns a Integer object
   * @return an Integer object
   */
  public int getNumberOfBeds()
  {
    return nrOfBeds.get();
  }

  /**
   * A getter method that returns a IntegerProperty object
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
    model.addRoom(roomId.get(), type, nrOfBeds.get());
    System.out.println(model.getAllRooms());

    reset();
  }

  /**
   * A non argument method calls an editRoomInfo(String roomId, RoomTypes type, int nrBeds)
   * from the model
   */
  public void editRoomInfo()
  {
    System.out.println(roomId.get());
    System.out.println(type);
    System.out.println(nrOfBeds);
    model.editRoomInfo(roomId.get(), type, nrOfBeds.get());
    reset();
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
   * @return a String object
   */
  public String getErrorProperty()
  {
    return errorProperty.get();
  }

  /**
   * A getter method that returns a StringProperty object
   * @return a StringProperty object called nrOfBeds
   */
  public StringProperty errorPropertyProperty()
  {
    return errorProperty;
  }
}