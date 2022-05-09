package viewModel;

import javafx.beans.property.*;
import model.Room;
import model.RoomType;

public class SimpleRoomViewModel
{
  private StringProperty roomNumberProperty;
  private ObjectProperty<RoomType> roomTypeProperty;
  private IntegerProperty numberOfBedsProperty;


  public SimpleRoomViewModel(Room room)
  {
    roomNumberProperty = new SimpleStringProperty(room.getRoomId());
    roomTypeProperty = new SimpleObjectProperty<>(room.getRoomType());
    numberOfBedsProperty = new SimpleIntegerProperty(room.getNumberOfBeds());
  }


  public StringProperty roomNumberProperty()
  {
    return roomNumberProperty;
  }


  public ObjectProperty<RoomType> roomTypeProperty()
  {
    return roomTypeProperty;
  }


  public IntegerProperty numberOfBedsProperty()
  {
    return numberOfBedsProperty;
  }
}
