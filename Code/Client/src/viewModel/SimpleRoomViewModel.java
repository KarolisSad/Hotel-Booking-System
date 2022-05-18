package viewModel;

import javafx.beans.property.*;
import model.Room;
import model.RoomType;

/**
 * A class that is used to store information about the rooms.
 * This information is used in tables.
 *
 * @author Group 5
 * @version 04/18/2022
 */
public class SimpleRoomViewModel
{
  private StringProperty roomNumberProperty;
  private ObjectProperty<RoomType> roomTypeProperty;
  private IntegerProperty numberOfBedsProperty;

  /**
   * A SimpleRoomViewModel constructor initializing all instance variables.
   */
  public SimpleRoomViewModel(Room room)
  {
    roomNumberProperty = new SimpleStringProperty(room.getRoomId());
    roomTypeProperty = new SimpleObjectProperty<>(room.getRoomType());
    numberOfBedsProperty = new SimpleIntegerProperty(room.getNumberOfBeds());
  }

  /**
   * A getter method for roomNumberProperty.
   * @return StringProperty object.
   */
  public StringProperty roomNumberProperty()
  {
    return roomNumberProperty;
  }

  /**
   * A getter method for roomTypeProperty.
   * @return ObjectProperty<RoomType> object.
   */
  public ObjectProperty<RoomType> roomTypeProperty()
  {
    return roomTypeProperty;
  }

  /**
   * A getter method for numberOfBedsProperty.
   * @return IntegerProperty object.
   */
  public IntegerProperty numberOfBedsProperty()
  {
    return numberOfBedsProperty;
  }
}
