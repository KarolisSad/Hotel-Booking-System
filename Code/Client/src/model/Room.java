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


  /**
   * Four-argument contructor
   * A constructor that initializes instance variable using the set methods.
   *
   * @param roomId room number
   * @param roomOfType The type of room
   * @param numberOfBeds the number of beds to be assigned to the room.
   */
  public Room(String roomId, RoomType roomOfType, int numberOfBeds)
  {
    setRoomId(roomId);
    setRoomType(roomOfType);
    setNumberOfBeds(numberOfBeds);
  }

  /**
   * Method used for setting the type of the room.
   * @param roomOfType The type to be set.
   */
  public void setRoomType(RoomType roomOfType)
  {
    if (roomOfType == null)
    {
      throw new IllegalArgumentException("Room type cannot be null");
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
    if (numberOfBeds == 0)
    {
      throw new IllegalArgumentException("Number of beds should not be 0");
    }

    else
    {
      this.numberOfBeds = numberOfBeds;
    }
  }

  /**
   * Private method setting the roomId variable to the string given as argument.
   *
   * @param roomId
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
   * @return
   */
  public RoomType getRoomType()
  {
    return roomType;
  }

  /**
   * A method returning the number of beds of a room.
   * @return
   */
  public int getNumberOfBeds()
  {
    return numberOfBeds;
  }

  /**
   * A method meant for making a copy of a room object
   *
   * @return a copy of room object
   */
  public Room copy()
  {
    Room other = new Room(roomId, roomType, numberOfBeds);
    return other;
  }

  /**
   * Method returning a string representation of the room object.
   * @return A string containing all variables and their values.
   */
  @Override
  public String toString() {
    return "Room{" +
        "roomId='" + roomId + '\'' +
        ", roomType=" + roomType +
        ", numberOfBeds=" + numberOfBeds +
        '}';
  }
}
