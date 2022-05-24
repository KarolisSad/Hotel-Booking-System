package viewModel;

import javafx.beans.property.*;
import model.Room;
import model.RoomType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
  private IntegerProperty dailyPriceProperty;
  private ObjectProperty<LocalDate> startDate;
  private ObjectProperty<LocalDate> endDate;
  private LongProperty totalCosts;

  /**
   * A SimpleRoomViewModel constructor initializing all instance variables.
   */
  public SimpleRoomViewModel(Room room)
  {
    roomNumberProperty = new SimpleStringProperty(room.getRoomId());
    roomTypeProperty = new SimpleObjectProperty<>(room.getRoomType());
    numberOfBedsProperty = new SimpleIntegerProperty(room.getNumberOfBeds());
    dailyPriceProperty = new SimpleIntegerProperty(room.getPrice());
    startDate = new SimpleObjectProperty<>();
    endDate = new SimpleObjectProperty<>();
  }

  public SimpleRoomViewModel(Room room, LocalDate startDate, LocalDate endDate)
  {
    roomNumberProperty = new SimpleStringProperty(room.getRoomId());
    roomTypeProperty = new SimpleObjectProperty<>(room.getRoomType());
    numberOfBedsProperty = new SimpleIntegerProperty(room.getNumberOfBeds());
    dailyPriceProperty = new SimpleIntegerProperty(room.getPrice());
    this.startDate = new SimpleObjectProperty<>(startDate);
    this.endDate = new SimpleObjectProperty<>(endDate);
    totalCosts = new SimpleLongProperty(startDate.until(endDate, ChronoUnit.DAYS) * dailyPriceProperty.get());
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

  public IntegerProperty dailyPriceProperty()
  {
    return dailyPriceProperty;
  }

  public LongProperty totalPriceProperty()
  {
    return totalCosts;
  }
}
