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

  //todo change javadoc

  /**
   * One-argument contructor
   * A constructor that initializes instance variable using the setRoomId method.
   *
   * @param roomId room number
   */
  public Room(String roomId, RoomType roomOfType, int numberOfBeds)
  {
    this.roomType =roomOfType;
    this.roomId = roomId;
    this.numberOfBeds = numberOfBeds;
    setRoomId(roomId);
    setRoomType(roomOfType);
    setNumberOfBeds(numberOfBeds);
  }

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

  public RoomType getRoomType()
  {
    return roomType;
  }


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

  @Override
  public String toString() {
    return "Room Name: "+roomId + " Type: " + roomType.toString();
  }
}
