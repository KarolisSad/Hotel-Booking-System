package model;
/**
 * A class that is used to store list of room types
 * as enum.
 *
 * @author Group 5
 * @version 04/18/2022
 */
public enum RoomType
{
  SINGLE, DOUBLE, FAMILY, SUITE;

  /**
   * A method getting a name of the enum room type.
   * @return String object.
   */
  public String toString()
  {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}


