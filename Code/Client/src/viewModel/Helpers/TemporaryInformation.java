package viewModel.Helpers;

import java.time.LocalDate;

/**
 * A class that is used to store temporary information from the client.
 *
 * @author Group 5
 * @version 04/18/2022
 */
public class TemporaryInformation
{
  private LocalDate startDate;
  private LocalDate endDate;
  private String roomID;
  private String roomType;
  private int numberOfBeds;

  /**
   * A TemporaryInformation constructor initializing all instance variables.
   */
  public TemporaryInformation()
  {
    this.startDate = null;
    this.endDate = null;
    this.roomID = null;
    this.roomType = null;
    this.numberOfBeds = -1;
  }

  /**
   * A setter for endDate.
   */
  public void setEndDate(LocalDate endDate)
  {
    this.endDate = endDate;
  }

  /**
   * A setter for roomID.
   */
  public void setRoomID(String roomID)
  {
    this.roomID = roomID;
  }

  /**
   * A setter for startDate.
   */
  public void setStartDate(LocalDate startDate)
  {
    this.startDate = startDate;
  }

  /**
   * A getter for roomType.
   * @return String object.
   */
  public String getRoomType()
  {
    return roomType;
  }

  /**
   * A setter for roomType.
   */
  public void setRoomType(String roomType)
  {
    this.roomType = roomType;
  }

  /**
   * A getter for numberOfBeds.
   * @return integer object.
   */
  public int getNumberOfBeds()
  {
    return numberOfBeds;
  }

  /**
   * A setter for numberOfBeds.
   */
  public void setNumberOfBeds(int numberOfRooms)
  {
    this.numberOfBeds = numberOfRooms;
  }

  /**
   * A getter for startDate.
   * @return LocalDate object.
   */
  public LocalDate getStartDate()
  {
    return startDate;
  }

  /**
   * A getter for endDate.
   * @return LocalDate object.
   */
  public LocalDate getEndDate()
  {
    return endDate;
  }

  /**
   * A getter for roomID.
   * @return String object.
   */
  public String getRoomID()
  {
    return roomID;
  }

}
