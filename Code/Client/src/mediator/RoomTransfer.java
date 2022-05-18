package mediator;

import model.Guest;
import model.Room;
import model.RoomType;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Purpose of this object is to store different values when sending to a server
 * 2020-05-08
 *
 * @author Group5
 */

public class RoomTransfer
{

  private String roomId;
  private RoomType roomType;
  private int nrBeds;
  private String type;
  private ArrayList<Room> roomList;
  private LocalDate startDate;
  private LocalDate endDate;
  private Guest guest;
  private String message;
  private Room room;

  /**
   * Purpose of this object is to store different values when sending to a server
   *
   * 2020-05-08
   * @author Group5
   */
  public RoomTransfer(String type, String roomId, RoomType roomType, int nrBeds,
      ArrayList<Room> roomList)
  {
    this.roomId = roomId;
    this.roomType = roomType;
    this.nrBeds = nrBeds;
    this.type = type;
    this.roomList = roomList;
  }

  /**
   * 5 argument constructor used for transferring a type of the transfer and room identification
   * and booking information.
   * @param type The type of transfer
   * @param roomId The room identifiction
   * @param startDate The beginning date of the booking
   * @param endDate The ending date of the booking
   * @param guest The guest object
   */
  //Book
  public RoomTransfer(String type, String roomId, LocalDate startDate,
      LocalDate endDate, Guest guest)
  {
    this.type = type;
    this.roomId = roomId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.guest = guest;
  }

  /**
   * 3 argument constructor used for transferring a type of the transfer and start date and end date from
   * booking information.
   * @param type The type of transfer
   * @param startDate The beginning date of the booking
   * @param endDate The ending date of the booking
   */
  public RoomTransfer(String type, LocalDate startDate, LocalDate endDate)
  {
    this.type = type;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  /**
   * 2 argument constructor used for transferring a type of the transfer
   *  and message.
   * @param type The type of transfer
   * @param message The beginning date of the booking
   */
  public RoomTransfer(String type, String message)
  {
    this.type = type;
    this.message = message;
  }

  /**
   * 4 argument constructor used for transferring a type of the transfer and room information.
   * @param type The type of transfer
   * @param roomId The room identifiction
   * @param roomType The type of the room
   * @param nrBeds Number of beds in the room
   */
  RoomTransfer(String type, String roomId, RoomType roomType, int nrBeds)
  {
    this.roomType = roomType;
    this.type = type;
    this.roomId = roomId;
    this.nrBeds = nrBeds;
  }

  /**
   * 1 argument constructor used for transferring a type of the transfer.
   * @param type The type of transfer
   */
  public RoomTransfer(String type)
  {
    this.type = type;
  }

  public Room getRoom()
  {
    return room;
  }

  /**
   * A getter for a guest.
   * @return Guest object called guest.
   */
  public Guest getGuest()
  {
    return guest;
  }

  /**
   * A getter for an end date of a booking.
   * @return LocalDate object called endDate.
   */
  public LocalDate getEndDate()
  {
    return endDate;
  }

  /**
   * A getter for a start date of a booking.
   * @return LocalDate object called startDate.
   */
  public LocalDate getStartDate()
  {
    return startDate;
  }

  /**
   * A getter for a type of the room.
   * @return RoomType object called roomType.
   */
  public RoomType getRoomType()
  {
    return roomType;
  }

  /**
   * A getter for a room identification.
   * @return String object called roomId.
   */
  public String getRoomId()
  {
    return roomId;
  }

  /**
   * A getter for a type of the room.
   * @return String object called type.
   */
  public String getType()
  {
    return type;
  }

  /**
   * A getter for number of beds in the room.
   * @return int object called nrBeds.
   */
  public int getNrBeds()
  {
    return nrBeds;
  }

  /**
   * A getter for a list of rooms.
   * @return ArrayList<Room> object called roomList.
   */
  public ArrayList<Room> getRoomList()
  {
    return roomList;
  }

  /**
   * A getter for a message.
   * @return String object called message.
   */
  public String getMessage()
  {
    return message;
  }

  /**
   * A getter for information about the RoomTransfer.
   * @return String object.
   */
  @Override public String toString()
  {
    return "RoomTransfer{" + "roomId='" + roomId + '\'' + ", roomType="
        + roomType + ", nrBeds=" + nrBeds + ", type='" + type + '\''
        + ", roomList=" + roomList + ", startDate=" + startDate + ", endDate="
        + endDate + ", guest=" + guest + ", message='" + message + '\'' + '}';
  }
}
