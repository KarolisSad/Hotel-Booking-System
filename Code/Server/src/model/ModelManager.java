package model;


import mediator.RoomTransfer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class that implements the Model interface and manages the bookings.
 *
 * @author Group 5
 * @version 04/05/2022
 */

public class ModelManager implements Model
{
  private RoomBookingList allBookings;
  private RoomList roomList;
  private HotelPersistence hotelPersistence;

  /**
   * A constructor that is meant to initialize
   * the instance variables as a new array lists
   * that will store a list of all rooms and a list of booked rooms.
   */
  public ModelManager()
  {
    allBookings = new RoomBookingList();
    roomList = new RoomList();
    this.hotelPersistence = new HotelDataBase();
  }

  /**
   * A method that returns all available rooms by selected date
   *
   * @param startDate start date
   * @param endDate   end date
   * @return available rooms
   */


  /**
   * A method that checks if the room is available right before bookings after the personal information is entered
   *
   * @param roomId    room number
   * @param startDate start date
   * @param endDate   end date
   * @return true if rooms is allowed to be booked and false is room is not allowed to be booked
   */
  public boolean isBookingAllowed(String roomId, LocalDate startDate, LocalDate endDate)
  {
    ArrayList<Room> notAvailableRooms = allBookings.getBookedRoomsBy(startDate,
        endDate);

    for (int i = 0; i < notAvailableRooms.size(); i++)
    {
      if (notAvailableRooms.get(i).getRoomId().equals(roomId))
      {
        return false;
      }
    }
    return true;
  }

  /**
   * A method that is meant for booking a room.
   *
   * @param roomId    room number
   * @param startDate start date
   * @param endDate   end date
   * @param guest     guest
   * @return true if the room is booked and false if the room is not booked
   */
  @Override public void book(String roomId, LocalDate startDate,
                               LocalDate  endDate, Guest guest) throws SQLException {
    hotelPersistence.book(roomId, startDate, endDate, guest);
  }


  @Override
  public void addRoom(String roomID, String type, int numberOfBeds) throws SQLException {
     hotelPersistence.addRoom(roomID,type,numberOfBeds);
  }

  @Override
  public void removeRoom(String id) throws SQLException {
    hotelPersistence.remove(id);
  }

  @Override
  public ArrayList<Room> getAllRooms() throws SQLException {
    return hotelPersistence.getAllRooms();
  }

  @Override
  public void editRoomInfo(String roomID, String type, int nrBeds) throws SQLException {
    hotelPersistence.editRoomInfo(roomID,type,nrBeds);
  }

  @Override public RoomTransfer availableRooms(LocalDate  startDate, LocalDate endDate) throws SQLException {
    return hotelPersistence.availableRooms(startDate, endDate);
  }

}
