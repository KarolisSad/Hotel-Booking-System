package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.RoomBookingTransfer;
import model.Model;

public class BookingsForReceptionistViewModel
{
  private Model model;

  private ObservableList<SimpleBookingViewModel> bookings;

  private SimpleStringProperty errorLabel;

  private ObjectProperty<SimpleBookingViewModel> selectedBookingProperty;

  private boolean isCheckIn;

  public BookingsForReceptionistViewModel(Model model)
  {
    this.model = model;

    this.bookings = FXCollections.observableArrayList();
    updateBookingList("booked");

    this.selectedBookingProperty = new SimpleObjectProperty<>();

    this.errorLabel = new SimpleStringProperty("");
  }

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
            bookings.add(
                new SimpleBookingViewModel(bookingList.getRoomBookings().get(i)));
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
            bookings.add(
                new SimpleBookingViewModel(bookingList.getRoomBookings().get(i)));
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

  public boolean isCheckIn()
  {
    return isCheckIn;
  }

  public void reset()
  {
    errorLabel.setValue("");
    updateBookingList("booked");
  }

  public void setSelected(SimpleBookingViewModel selectedBooking)
  {
    selectedBookingProperty.set(selectedBooking);
    System.out.println(
        "SELECTION CHANGED: " + selectedBooking.bookingIdProperty());
  }

  public SimpleBookingViewModel getSelectedBookingProperty()
  {
    return selectedBookingProperty.get();
  }

  public SimpleStringProperty getErrorLabel()
  {
    return errorLabel;
  }

  public ObservableList<SimpleBookingViewModel> getBookings()
  {
    return bookings;
  }

  public void processBooking(SimpleBookingViewModel bookingViewModel)
  {
    model.processBooking(bookingViewModel.bookingIdProperty().get());
    updateBookingList("booked");
  }
}
