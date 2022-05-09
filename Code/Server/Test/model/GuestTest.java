package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest
{
  Guest testGuest = new Guest("TestFirstName", "TestLastName", "test@email.com",
      12345678);

  @BeforeEach void setUp()
  {
    testGuest.setfName("TestFirstName");
    testGuest.setlName("TestLastName");
    testGuest.setEmail("test@email.com");
    testGuest.setPhoneNr(12345678);
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

  }

  // Many

  // Boundary

  // Exception

  @Test void setPhoneNr()
  {
  }
}