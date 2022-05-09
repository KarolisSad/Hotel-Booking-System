package viewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Room;

public class SimpleRoomViewModel
{
  private StringProperty roomNumberProperty;
  private StringProperty roomTypeProperty;
  private IntegerProperty numberOfBedsProperty;


  public SimpleRoomViewModel(Room room)
  {
    roomNumberProperty = new SimpleStringProperty(room.getRoomId());
    roomTypeProperty = new SimpleStringProperty(room.getRoomType());
    numberOfBedsProperty = new SimpleIntegerProperty(room.getNumberOfBeds());
  }


  public StringProperty roomNumberProperty()
  {
    return roomNumberProperty;
  }


  public StringProperty roomTypeProperty()
  {
    return roomTypeProperty;
  }


  public IntegerProperty numberOfBedsProperty()
  {
    return numberOfBedsProperty;
  }
}
