package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest
{
  Room testRoom = new Room("TestRoom", RoomType.SINGLE, 1);

  @BeforeEach void setUp()
  {
    testRoom.setRoomId("TestRoom");
  }

  // setRoomId test \\

  // Zero
  @Test void setRoomIdToNull()
  {
    assertThrows(IllegalArgumentException.class, ()-> testRoom.setRoomId(null));
  }

  @Test void setRoomIdToEmptyString()
  {
    assertThrows(IllegalArgumentException.class, ()-> testRoom.setRoomId(""));
  }

  @Test void setRoomIdToEmptyStringWithSpaces()
  {
    assertThrows(IllegalArgumentException.class, ()-> testRoom.setRoomId("  "));
  }

  // One
  @Test void setRoomIdToSingleCharValue()
  {
    testRoom.setRoomId("A");
    assertEquals("A", testRoom.getRoomId());
  }

  // Many
  @Test void setRoomIdToStringValue()
  {
    testRoom.setRoomId("This is my test room");
    assertEquals("This is my test room", testRoom.getRoomId());
  }

  @Test void setRoomId3Times()
  {
    testRoom.setRoomId("Room 1");
    testRoom.setRoomId("1.01");
    testRoom.setRoomId("This is my test room");
    assertEquals("This is my test room", testRoom.getRoomId());
  }

  // Boundary
  // Only boundary to test already tested in zero-case.

  // Exception
  // Exception already tested in Zero-case.
}