import model.Model;
import model.ModelManager;
import model.RoomBookingList;

import java.sql.SQLException;

public class ChrTest
{
  public static void main(String[] args) throws SQLException
  {
    Model model = new ModelManager();

    RoomBookingList all = model.getAllBookings("");

    System.out.println("ALL");
    for (int i = 0; i < all.bookedRoomListSize(); i++)
    {
      System.out.println(all.getBooking(i));
    }

    RoomBookingList booked = model.getAllBookings("booked");

    System.out.println("\nBOOKED");
    for (int i = 0; i < booked.bookedRoomListSize(); i++)
    {
      System.out.println(booked.getBooking(i));
    }

    RoomBookingList cancelled = model.getAllBookings("cancelled");

    System.out.println("\nCANCELLED");
    for (int i = 0; i < cancelled.bookedRoomListSize(); i++)
    {
      System.out.println(cancelled.getBooking(i));
    }
  }
}
