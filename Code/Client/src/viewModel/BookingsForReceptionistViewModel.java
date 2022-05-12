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

  public BookingsForReceptionistViewModel(Model model)
  {
    this.model = model;

    this.bookings = FXCollections.observableArrayList();
    updateBookingList();

    this.selectedBookingProperty = new SimpleObjectProperty<>();

    this.errorLabel = new SimpleStringProperty("");
  }

  public void updateBookingList()
  {
    bookings.clear();

    RoomBookingTransfer bookingList = model.getAllBookings();

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

  }

  public void reset()
  {
   errorLabel.setValue("");
   updateBookingList();
  }

  public void setSelected(SimpleBookingViewModel selectedBooking)
  {
    selectedBookingProperty.set(selectedBooking);
    System.out.println("SELECTION CHANGED: " + selectedBooking.bookingIdProperty());
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
}
