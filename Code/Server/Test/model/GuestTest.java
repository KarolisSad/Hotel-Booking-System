package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest
{
  Guest testGuest = new Guest("TestFirstName", "TestLastName", "test@email.com",
      12345678, "TestUsername", "TestPassword");

  @BeforeEach void setUp()
  {
    testGuest.setfName("TestFirstName");
    testGuest.setlName("TestLastName");
    testGuest.setEmail("test@email.com");
    testGuest.setPhoneNr(12345678);
    testGuest.setUsername("TestUsername");
    testGuest.setPassword("TestPassword");
  }

  // setfName Test \\

  // Zero
  @Test void setfNameNullValue()
  {
    assertThrows(NullPointerException.class, () -> testGuest.setfName(null));
  }

  @Test void setfNameEmptyValue()
  {
    assertThrows(NullPointerException.class, () -> testGuest.setfName(""));
  }

  // One
  @Test void setfNameToOneValue()
  {
    testGuest.setfName("My first name");
    assertEquals("My first name", testGuest.getfName());
  }

  // Many
  @Test void setfName3Times()
  {
    testGuest.setfName("My first name");
    testGuest.setfName("First Name");
    testGuest.setfName("Bob");
    assertEquals("Bob", testGuest.getfName());
  }

  // Boundary
  // No boundaries to check.

  // Exception
  // Only possible exceptions already checked in Zero-case.

  // setlName test \\

  // Zero
  @Test void setlNameToNullValue()
  {
    assertThrows(NullPointerException.class, () -> testGuest.setlName(null));
  }

  @Test void setlNameToEmptyValue()
  {
    assertThrows(NullPointerException.class, () -> testGuest.setlName(""));
  }

  // One
  @Test void setlNameToOneValue()
  {
    testGuest.setlName("My last name");
    assertEquals("My last name", testGuest.getlName());
  }

  // Many
  @Test void setlNameTo3Values()
  {
    testGuest.setlName("My last name");
    testGuest.setlName("Last name");
    testGuest.setlName("Builder");
    assertEquals("Builder", testGuest.getlName());
  }

  // Boundaries
  // No boundaries to check.

  // Exception
  // Exceptions already checked in Zero-case.

  // setEmail test \\

  // Zero
  @Test void setEmailToNullValue()
  {
    assertThrows(NullPointerException.class, () -> testGuest.setEmail(null));
  }

  @Test void setEmailToEmptyValue()
  {
    assertThrows(NullPointerException.class, () -> testGuest.setEmail(""));
  }

  // One
  @Test void setEmailToOneValue()
  {
    testGuest.setEmail("mail@test.com");
    assertEquals("mail@test.com", testGuest.getEmail());
  }

  // Many
  @Test void setEmailTo3Values()
  {
    testGuest.setEmail("mail1@test.com");
    testGuest.setEmail("mail23@test.com");
    testGuest.setEmail("mail@test.com");
    assertEquals("mail@test.com", testGuest.getEmail());
  }

  // Boundary
  // No boundaries to test.

  // Exception
  @Test void setEmailWithoutAt()
  {
    assertThrows(IllegalArgumentException.class, ()-> testGuest.setEmail("myemail.com"));
  }

  @Test void setEmailWithoutDot()
  {
    assertThrows(IllegalArgumentException.class, ()-> testGuest.setEmail("myemail@test"));
  }

  // setPhoneNr test \\

  // Zero
  @Test void setPhoneNrToZero()
  {
    assertThrows(IllegalArgumentException.class, ()-> testGuest.setPhoneNr(0));
  }

  // One
  @Test void setPhoneNrToOne()
  {
    assertThrows(IllegalArgumentException.class, ()-> testGuest.setPhoneNr(1));
  }

  @Test void setPhoneNrToOneLegal()
  {
    testGuest.setPhoneNr(11223344);
    assertEquals(11223344, testGuest.getPhoneNr());
  }

  // Many

  @Test void setPhoneNr3Times()
  {
    testGuest.setPhoneNr(11223344);
    testGuest.setPhoneNr(22334455);
    testGuest.setPhoneNr(33445566);
    assertEquals(33445566, testGuest.getPhoneNr());
  }

  // Boundary
  @Test void setPhoneNrToBelowLowerBoundary()
  {
    assertThrows(IllegalArgumentException.class, ()-> testGuest.setPhoneNr(9999999));
  }

  @Test void setPhoneNrToAboveLowerBoundary()
  {
    testGuest.setPhoneNr(9999999+1);
    assertEquals(10000000, testGuest.getPhoneNr());
  }

  @Test void setPhoneNrToHighestAllowedValue()
  {
    testGuest.setPhoneNr(99999999);
    assertEquals(99999999, testGuest.getPhoneNr());
  }

  @Test void setPhoneNrToAboveHighBoundary()
  {
    assertThrows(IllegalArgumentException.class, ()-> testGuest.setPhoneNr(99999999+1));
  }


  // Exception

  //Exceptions already tested in Zero, One and Boundary cases.


  // setUserName test \\

  // Zero

  @Test void setUserNameToNull()
  {
    assertThrows(IllegalArgumentException.class, ()-> testGuest.setUsername(null));
  }

  @Test void setUserNameToEmptyString()
  {
    assertThrows(IllegalArgumentException.class, ()-> testGuest.setUsername(""));
  }

  @Test void setUserNameToStringWithNoContents()
  {
    assertThrows(IllegalArgumentException.class, ()-> testGuest.setUsername("    "));
  }

  // One

  @Test void setUserNameToOneCharString()
  {
    testGuest.setUsername("a");
    assertEquals("a", testGuest.getUsername());
  }

  // Many
  @Test void setUserNameToStringWith3Chars()
  {
    testGuest.setUsername("abc");
    assertEquals("abc", testGuest.getUsername());
  }

  @Test void setUserNameToStringWith10Chars()
  {
    testGuest.setUsername("abcdefghij");
    assertEquals("abcdefghij", testGuest.getUsername());
  }

  // Boundary
  // Only lower boundary exists -> already tested in Zero-case

  // Exception
  // Exceptions already tested in Zero case.

  // setPassword test \\

  // Zero
  @Test void setPasswordToNull()
  {
    assertThrows(IllegalArgumentException.class, ()-> testGuest.setPassword(null));
  }

  @Test void setPasswordToEmptyString()
  {
    assertThrows(IllegalArgumentException.class, ()-> testGuest.setPassword(""));
  }

  @Test void setPasswordToStringWithNoContents()
  {
    assertThrows(IllegalArgumentException.class, ()-> testGuest.setPassword("    "));
  }

  // One

  @Test void setPasswordToOneCharString()
  {
    testGuest.setPassword("a");
    assertEquals("a", testGuest.getPassword());
  }

  // Many
  @Test void setPasswordToStringWith3Chars()
  {
    testGuest.setPassword("abc");
    assertEquals("abc", testGuest.getPassword());
  }

  @Test void setPasswordToStringWith10Chars()
  {
    testGuest.setPassword("abcdefghij");
    assertEquals("abcdefghij", testGuest.getPassword());
  }

  // Boundary
  // Only lower boundary exists -> already tested in Zero-case

  // Exception
  // Exceptions already tested in Zero case.


}

