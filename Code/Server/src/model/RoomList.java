package model;

import java.util.ArrayList;

/**
 * A class meant for creating, storing and manipulating a list of all rooms.
 *
 * @author Group 5
 * @version 04/05/2022
 */
public class RoomList
{
  private ArrayList<Room> roomList;

  /**
   * A constructor where we initialize an instance variable as a new arrayList.
   */
  RoomList()
  {
    this.roomList = new ArrayList<>();
    createDummyData();
  }

  public void createDummyData()
  {
    addRoom(new Room("1.01","Family",5));
    addRoom(new Room("1.02", "Regular",2));
    addRoom(new Room("1.03","Lux", 3));
  }

  /**
   * A method meant for calling a list of all rooms.
   *
   * @return roomList
   */
  public ArrayList<Room> getRoomList()
  {
    return new ArrayList<>(roomList);
  }

  //  public void removeRoomById(String id)
  //  {
  //    for (int i = 0; i<roomList.size(); i++)
  //    {
  //      if (roomList.get(i).getRoomId().equals(id))
  //      {
  //        roomList.remove(i);
  //        return;
  //      }
  //    }
  //  }

  /**
   * A method that is meant for added a new room to the room list.
   *
   * @param room room that needs to be added
   * @throws NullPointerException if argument given is null.
   * @throws IllegalArgumentException if room is already in list.
   */
  public void addRoom(Room room)
  {
    if (room == null)
    {
      throw new NullPointerException(
          "Cannot insert a null value room.");
    }

    else if (roomList.contains(room))
    {
      throw new IllegalArgumentException(
          "Cannot have multiple instances of the same room.");
    }

    else
    {
      roomList.add(room);
    }
  }

  /**
   * A method that is used to call a room from a room list by room id.
   *
   * @param roomID room name
   * @return room
   */
  public Room getRoom(String roomID)
  {
    for (int i = 0; i < roomList.size(); i++)
    {
      if (roomList.get(i).getRoomId().equals(roomID))
      {
        return roomList.get(i);
      }
    }
    return null;
  }

  /**
   * A method calling room from a room list by the room index
   *
   * @param index room index
   * @return room
   */
  public Room getRoomByNr(int index)
  {
    return roomList.get(index);
  }

  /**
   * A method calculates how many rooms are in the room list.
   *
   * @return size of the roomList
   */
  public int getRoomListSize()
  {
    return roomList.size();
  }

  @Override
  public String toString() {
    return "RoomList{" +
            "roomList=" + roomList +
            '}';
  }
}
