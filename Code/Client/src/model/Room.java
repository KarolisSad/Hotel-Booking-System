package model;

/**
 * A class that creates room object
 *
 * @author Group 5
 * @version 04/05/2022
 */
public class Room
{
  private String roomId;
  private RoomType roomType;
  private int numberOfBeds;
  private int price;


  /**
   * Four-argument contructor
   * A constructor that initializes instance variable using the set methods.
   *
   * @param roomId room number
   * @param roomOfType The type of room
   * @param numberOfBeds the number of beds to be assigned to the room.
   * @param dailyPrice the daily price of the room
   */
  public Room(String roomId, RoomType roomOfType, int numberOfBeds, int dailyPrice)
  {
    setRoomId(roomId);
    setRoomType(roomOfType);
    setNumberOfBeds(numberOfBeds);
    setPrice(dailyPrice);
  }

  /**
   * Method used for setting the type of the room.
   * @param roomOfType The type to be set.
   */
  public void setRoomType(RoomType roomOfType)
  {
    if (roomOfType == null)
    {
      throw new IllegalArgumentException("Please select a room type.");
    }

    else
    {
      this.roomType = roomOfType;
    }
  }

  /**
   * Method used to set the numberOfBeds variable.
   * @param numberOfBeds The number of beds to be assigned.
   */
  public void setNumberOfBeds(int numberOfBeds)
  {
    if (numberOfBeds <= 0)
    {
      throw new IllegalArgumentException("Capacity should not be less that 1.");
    }

    else
    {
      this.numberOfBeds = numberOfBeds;
    }
  }

  /**
   * Method setting the roomId variable to the string given as argument.
   *
   * @param roomId the room id to set
   * @throws IllegalArgumentException if argument is null or an empty String.
   */
  public void setRoomId(String roomId)
  {
    if (roomId == null || roomId.isBlank())
    {
      throw new IllegalArgumentException(
          "Room ID should not be empty. Please add a valid Room ID.");
    }

    this.roomId = roomId;
  }

  /**
   * Method used for setting the price of a room.
   *
   * @param price The price of the room
   * @throws IllegalArgumentException if the price passed as arguments is smaller than 1.
   */
  public void setPrice(int price)
  {
    if (price <= 0)
    {
      throw new IllegalArgumentException("The price for a room should not be less than zero.");
    }
    this.price = price;
  }

  /**
   * A method meant for calling room ID (room number)
   *
   * @return roomId
   */
  public String getRoomId()
  {
    return roomId;
  }

  /**
   * A method returning the RoomType of a room.
   * @return roomtype
   */
  public RoomType getRoomType()
  {
    return roomType;
  }

  /**
   * A method returning the number of beds of a room.
   * @return number of beds
   */
  public int getNumberOfBeds()
  {
    return numberOfBeds;
  }

  /**
   * Method meant for getting the daily price
   * @return price
   */
  public int getPrice()
  {
    return price;
  }

  /**
   * A method meant for making a copy of a room object
   *
   * @return a copy of room object
   */
  public Room copy()
  {
    Room other = new Room(roomId, roomType, numberOfBeds, price);
    return other;
  }

  /**
   * Method returning a string representation of the room object.
   * @return A string containing all variables and their values.
   */
  @Override public String toString()
  {

    return "Room number: " + roomId + ", Type: " + roomType.toString()
        + ", Number of beds: " + numberOfBeds + ", Price: " + price;
  }
}
