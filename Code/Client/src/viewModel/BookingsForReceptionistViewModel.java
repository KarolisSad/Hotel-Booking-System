package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.RoomBookingTransfer;
import model.Model;

/**
 * A class providing functionality for AddEditViewController.
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class BookingsForReceptionistViewModel
{
  private Model model;

  private ObservableList<SimpleBookingViewModel> bookings;

  private SimpleStringProperty errorLabel;

  private ObjectProperty<SimpleBookingViewModel> selectedBookingProperty;

  private boolean isCheckIn;

  /**
   * Constructor initializing instance variables.
   *
   * @param model model interface
   */
  public BookingsForReceptionistViewModel(Model model)
  {
    this.model = model;

    this.bookings = FXCollections.observableArrayList();
    updateBookingList("booked");

    this.selectedBookingProperty = new SimpleObjectProperty<>();

    this.errorLabel = new SimpleStringProperty("");
  }

  /**
   * Method for updating the list of bookings shown.
   * It clears the current list, and then gets all Bookings from the model that fit the type given as argument and adds them.
   *
   * @param type The type of bookings to get for the table, can be either booked or in progress.
   */
  public void updateBookingList(String type)
  {

    bookings.clear();
    switch (type)
    {
      case "booked":
      {
        RoomBookingTransfer bookingList = model.getBookedBookings();
        isCheckIn = true;
        if (bookingList.getMessage() == null)
        {
          for (int i = 0; i < bookingList.getRoomBookings().size(); i++)
          {
            bookings.add(new SimpleBookingViewModel(
                bookingList.getRoomBookings().get(i)));
          }
        }
        else
        {
          errorLabel.setValue(bookingList.getMessage());
        }

        break;
      }

      case "InProgress":
      {
        isCheckIn = false;
        RoomBookingTransfer bookingList = model.getInProgressBookings();

        if (bookingList.getMessage() == null)
        {
          for (int i = 0; i < bookingList.getRoomBookings().size(); i++)
          {
            bookings.add(new SimpleBookingViewModel(
                bookingList.getRoomBookings().get(i)));
          }
        }
        else
        {
          errorLabel.setValue(bookingList.getMessage());
        }

        break;
      }
    }
  }

  /**
   * Simple method checking if the table currently is showing booked or in progress bookings.
   *
   * @return true if the table currently shows booked bookings, false if In progress.
   */
  public boolean isCheckIn()
  {
    return isCheckIn;
  }

  /**
   * Method emptying the error-label, and setting the booking list to show booked.
   */
  public void reset()
  {
    errorLabel.setValue("");
    updateBookingList("booked");
    try
    {
      setSelected(null);
    }
    catch (NullPointerException e)
    {
      // Intentionally empty - Exception should not be handled
    }
  }

  /**
   * Method for setting the selectedRoomProperty
   *
   * @param selectedBooking The SimpleBookingViewModel to be set as selected, or null if nothing is selected.
   */
  public void setSelected(SimpleBookingViewModel selectedBooking)
  {
    selectedBookingProperty.set(selectedBooking);
  }

  /**
   * Method used for getting the selectedBookingProperty
   *
   * @return selectedBookingProperty
   */
  public SimpleBookingViewModel getSelectedBookingProperty()
  {
    return selectedBookingProperty.get();
  }

  /**
   * Method for getting the errorlabel.
   *
   * @return errorlabel
   */
  public SimpleStringProperty getErrorLabel()
  {
    return errorLabel;
  }

  /**
   * Method for getting the Observable list containing bookings.
   *
   * @return bookings.
   */
  public ObservableList<SimpleBookingViewModel> getBookings()
  {
    return bookings;
  }

  /**
   * Method calling the processBooking in the model, and updating the BookingList.
   *
   * @param bookingViewModel The booking to be updated.
   */
  public void processBooking(SimpleBookingViewModel bookingViewModel)
  {
    model.processBooking(bookingViewModel.bookingIdProperty().get());
    updateBookingList("booked");
  }
}
