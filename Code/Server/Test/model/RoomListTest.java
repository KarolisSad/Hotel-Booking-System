package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomListTest
{
  RoomList testRoomListEmpty;
  RoomList testRoomListOneElement;
  RoomList testRoomList3Elements;
  RoomList testRoomList7Elements;
  Room room1 = new Room("1.01", RoomType.SINGLE, 1);
  Room room2 = new Room("1.02", RoomType.DOUBLE, 2);
  Room room3 = new Room("1.03", RoomType.FAMILY, 3);
  Room room4 = new Room("1.04", RoomType.SUITE, 2);
  Room room5 = new Room("1.05", RoomType.SINGLE, 1);
  Room room6 = new Room("1.06", RoomType.SINGLE, 1);
  Room room7 = new Room("1.07", RoomType.SINGLE, 1);
  Room room8 = new Room("Room8", RoomType.FAMILY, 3);
  Room room9 = new Room("Room9", RoomType.FAMILY, 3);
  Room room10 = new Room("Room10", RoomType.FAMILY, 3);
  Room room11 = new Room("Room11", RoomType.FAMILY, 3);
  Room room12 = new Room("Room12", RoomType.FAMILY, 3);

  @BeforeEach void setUp()
  {
    testRoomListEmpty = new RoomList();
    while (testRoomListEmpty.getRoomListSize()>0)
    {
      testRoomListEmpty.getRoomList().remove(0);
    }
    testRoomListOneElement = new RoomList();
    while (testRoomListOneElement.getRoomListSize()>0)
    {
      testRoomListOneElement.getRoomList().remove(0);
    }
    testRoomListOneElement.addRoom(room1);
    testRoomList3Elements = new RoomList();
    while (testRoomList3Elements.getRoomListSize()>0)
    {
      testRoomList3Elements.getRoomList().remove(0);
    }
    testRoomList3Elements.addRoom(room1);
    testRoomList3Elements.addRoom(room2);
    testRoomList3Elements.addRoom(room3);
    testRoomList7Elements = new RoomList();
    while (testRoomList7Elements.getRoomListSize()>0)
    {
      testRoomList7Elements.getRoomList().remove(0);
    }
    testRoomList7Elements.addRoom(room1);
    testRoomList7Elements.addRoom(room2);
    testRoomList7Elements.addRoom(room3);
    testRoomList7Elements.addRoom(room4);
    testRoomList7Elements.addRoom(room5);
    testRoomList7Elements.addRoom(room6);
    testRoomList7Elements.addRoom(room7);


  }

  // addRoom test \\
  // Zero
  @Test void addRoomToEmptyList()
  {
    testRoomListEmpty.addRoom(room1);
    assertEquals(room1, testRoomListEmpty.getRoom(room1.getRoomId()));
  }

  @Test void addNullValueToList()
  {
    assertThrows(NullPointerException.class, ()->testRoomListEmpty.addRoom(null));
  }

  // One
  @Test void addOneRoomToListWithOneRoom()
  {
    testRoomListOneElement.addRoom(room2);
    assertEquals(2, testRoomListOneElement.getRoomListSize());
  }

  @Test void addOneRoomToListWith3Rooms()
  {
    testRoomList3Elements.addRoom(room4);
    assertEquals(room4, testRoomList3Elements.getRoomByNr(3));
  }

  // Many
  @Test void add3RoomsToEmptyList()
  {
    testRoomListEmpty.addRoom(room1);
    testRoomListEmpty.addRoom(room2);
    testRoomListEmpty.addRoom(room3);
    assertEquals(3, testRoomListEmpty.getRoomListSize());
  }

  @Test void add5RoomsToEmptyList()
  {
    testRoomListEmpty.addRoom(room1);
    testRoomListEmpty.addRoom(room2);
    testRoomListEmpty.addRoom(room3);
    testRoomListEmpty.addRoom(room4);
    testRoomListEmpty.addRoom(room5);
    assertEquals(5, testRoomListEmpty.getRoomListSize());
  }

  @Test void add3RoomsToListContaining3()
  {
    testRoomList3Elements.addRoom(room4);
    testRoomList3Elements.addRoom(room5);
    testRoomList3Elements.addRoom(room6);
    assertEquals(6, testRoomList3Elements.getRoomListSize());
  }

  @Test void add5RoomsToListContaining3()
  {
    testRoomList3Elements.addRoom(room4);
    testRoomList3Elements.addRoom(room5);
    testRoomList3Elements.addRoom(room6);
    testRoomList3Elements.addRoom(room7);
    testRoomList3Elements.addRoom(room8);
    assertEquals(8, testRoomList3Elements.getRoomListSize());
  }

  @Test void add3RoomsToListContaining7()
  {
    testRoomList7Elements.addRoom(room8);
    testRoomList7Elements.addRoom(room9);
    testRoomList7Elements.addRoom(room10);
    assertEquals(10, testRoomList7Elements.getRoomListSize());
  }

  @Test void add5RoomsToListContaining7()
  {
    testRoomList7Elements.addRoom(room8);
    testRoomList7Elements.addRoom(room9);
    testRoomList7Elements.addRoom(room10);
    testRoomList7Elements.addRoom(room11);
    testRoomList7Elements.addRoom(room12);
    assertEquals(12, testRoomList7Elements.getRoomListSize());
  }

  // Boundaries
  // No boundaries to test

  // Exceptions
  @Test void addRoomAlreadyExisting()
  {
    assertThrows(IllegalArgumentException.class, ()-> testRoomList3Elements.addRoom(room1));
  }
}