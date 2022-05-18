package model;

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


