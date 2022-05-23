package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.RoomBookingTransfer;
import model.Model;

public class BookingOverviewForGuestModel {

    private Model model;
    private ObservableList<SimpleBookingViewModel> bookings;

    private SimpleStringProperty errorLabel;

    private ObjectProperty<SimpleBookingViewModel> selectedBookingProperty;


    public BookingOverviewForGuestModel(Model model)
    {
        this.model = model;
        this.bookings = FXCollections.observableArrayList();
        this.errorLabel = new SimpleStringProperty("");
        this.selectedBookingProperty = new SimpleObjectProperty<>();
    }


    public ObservableList<SimpleBookingViewModel> getBookings() {
        return bookings;
    }

    public SimpleStringProperty getErrorLabel()
    {
        return errorLabel;
    }

    public SimpleBookingViewModel getSelectedBookingProperty()
    {
        return selectedBookingProperty.get();
    }

    public void setSelected(SimpleBookingViewModel selectedBooking)
    {
        selectedBookingProperty.set(selectedBooking);
    }

    public void reset() {
        updateBookings();
    }

  public void removeBooking()
    {
        model.removeBooking(getSelectedBookingProperty().bookingIdProperty().get());
        reset();
    }

    public void updateBookings() {
        bookings.clear();
        RoomBookingTransfer roomBookings = model.getBookingsWhenLoggedIn();
        if (roomBookings.getMessage() == null)
        {
            for (int i = 0; i < roomBookings.getRoomBookings().size(); i++)
            {
                bookings.add(new SimpleBookingViewModel(roomBookings.getRoomBookings().get(i),"User"));
            }
        }
    }
}
