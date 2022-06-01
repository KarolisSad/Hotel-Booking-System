package mediator;

import model.Guest;
import model.Room;
import model.RoomType;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Purpose of this object is to store different values when sending to a server
 * @version 2022-05-08
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
  private int dailyPrice;


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
   * 1 argument constructor used for transferring a type of the transfer.
   * @param type The type of transfer
   */
  public RoomTransfer(String type)
  {
    this.type = type;
  }

  /**
   *5 argument constructor used for transferring a type of RoomTransfer.
   * @param type The type of transfer
   * @param roomID The room id
   * @param dailyPrice the price
   * @param numberOfBeds the number of beds
   * @param roomType the type of room.
   */
  public RoomTransfer(String type, String roomID, RoomType roomType, int numberOfBeds, int dailyPrice)
  {
    this.type = type;
    this.roomId = roomID;
    this.roomType = roomType;
    this.nrBeds = numberOfBeds;
    this.dailyPrice = dailyPrice;
  }

  /**
   * A getter for a room object
   * @return Room
   */
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
   * A getter for a type of the roomtransfer.
   * @return String object called type.
   */
  public String getType()
  {
    return type;
  }

  /**
   * A getter for a list of rooms.
   * @return an ArrayList of Room objects called roomList.
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
