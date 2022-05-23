package model;

/**
 * Enum representing the different types of rooms.
 *
 * @author Group 5
 * @version 18-05-2022
 */
public enum RoomType
{
  SINGLE, DOUBLE, FAMILY, SUITE, CONFERENCE;

  /**
   * Override of toString method.
   * @return returns a String representation of the RoomType with the first letter being uppercase and the rest being lowercase.
   */
  public String toString()
  {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}


