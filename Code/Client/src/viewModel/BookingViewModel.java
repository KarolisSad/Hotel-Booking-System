package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import mediator.RoomBookingTransfer;
import model.*;

import java.time.LocalDate;

/**
 * A class providing functionality for BookingViewController
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class BookingViewModel
{

  private Model model;
  private ObservableList<SimpleBookingViewModel> bookings;
  private int selectedID;
  private boolean hideCancelledRooms;

  /**
   * Constructor initializing instance variables.
   *
   * @param model interface
   */
  public BookingViewModel(Model model)
  {
    hideCancelledRooms = false;
    this.bookings = FXCollections.observableArrayList();
    this.model = model;

  }

  // for loop to add all Bookings :)

  /**
   * A method meant for getting all bookings
   * @return bookings
   */
  public ObservableList<SimpleBookingViewModel> getBookings()
  {
    return bookings;
  }

  /**
   * A method that is used to set a bookingId for a selected Id.
   * @param bookingID booking Id
   */
  public void setSelected(int bookingID)
  {
    selectedID = bookingID;
  }

  /**
   * A method that is used to update a list of bookings when a new booking is created
   */
  public void updateBookingList()
  {
    bookings.clear();

    RoomBookingTransfer bookingTransfer = model.getAllBookings();
    System.out.println(bookingTransfer);
    if (bookingTransfer.getMessage() == null)
    {
      for (int i = 0; i < bookingTransfer.getRoomBookings().size(); i++)
      {
        bookings.add(new SimpleBookingViewModel(
            bookingTransfer.getRoomBookings().get(i)));
      }
    }
    else
    {
      // Error label
    }
  }

  /**
   * A method that is used for removing a booking and if it's not
   * successful an error message will be presented.
   */
  public void cancelBooking()
  {
    RoomBookingTransfer roomBookingTransfer = model.cancelBooking(selectedID);
    if (roomBookingTransfer.getMessage().equals("Success"))
    {
      updateBookingList();
      if (hideCancelledRooms)
      {
        removeCanceledBookings();
        removeCanceledBookings();
      }
    }

    else
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      //Style
      DialogPane dialogPane = alert.getDialogPane();
      dialogPane.getStylesheets().add("");
      dialogPane.getStylesheets()
              .add(getClass().getResource("box.css").toExternalForm());
      dialogPane.getStyleClass().add("box.css");

      alert.setTitle("Error");
      alert.setHeaderText("Cannot cancel booking!");
      alert.setContentText(roomBookingTransfer.getMessage());

      alert.showAndWait();
    }

  }

  /**
   * A method used o remove a cancelled booking from the list of cancelled bookings
   */
  public void removeCanceledBookings()
  {
    hideCancelledRooms = true;
    for (int i = 0; i < bookings.size(); i++)
    {
      if (bookings.get(i).bookingStateProperty().get().equals("Cancelled"))
      {
        bookings.remove(i);
      }
    }
  }

  /**
   * A method used to show all the cancelled bookings.
   */
  public void showCancelledBookings()
  {
    hideCancelledRooms = false;
    updateBookingList();
  }

}
