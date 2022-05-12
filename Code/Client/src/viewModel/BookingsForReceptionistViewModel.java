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

    this.selectedBookingProperty = new SimpleObjectProperty<>();

    this.errorLabel = new SimpleStringProperty("");
  }


  // todo get
  public void updateBookingList()
  {
    bookings.clear();

    RoomBookingTransfer bookingList = model.getAllBookings();

    for (int i = 0; i < bookingList.getRoomBookings().size();i++)
    {
      bookings.add(new SimpleBookingViewModel(bookingList.getRoomBookings().get(i)));
    }

  }

  public void reset()
  {
    //todo do something
  }


}
