package model;

public enum RoomType
{
  SINGLE, DOUBLE, FAMILY, SUITE;

  public String toString()
  {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }
}


