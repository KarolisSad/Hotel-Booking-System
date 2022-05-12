package viewModel;

import javafx.beans.property.*;
import model.Guest;
import model.Room;
import model.RoomBooking;
import java.time.LocalDate;

public class SimpleBookingViewModel {

    private SimpleObjectProperty<LocalDate> startDate;
    private SimpleObjectProperty<LocalDate> endDate;
    private SimpleObjectProperty<Guest> guestID;
    private SimpleObjectProperty<Room> roomID;
    private SimpleIntegerProperty bookingID;
    private SimpleStringProperty state;

    public SimpleBookingViewModel(RoomBooking roomBooking)
    {
        startDate = new SimpleObjectProperty<>(roomBooking.getStartDate());
        endDate = new SimpleObjectProperty<>(roomBooking.getEndDate());
        guestID = new SimpleObjectProperty<>(roomBooking.getGuest());
        roomID = new SimpleObjectProperty<>(roomBooking.getRoom());
        bookingID = new SimpleIntegerProperty(roomBooking.getBookingID());
        state = new SimpleStringProperty(roomBooking.getState());
    }

    public ObjectProperty<LocalDate> getStartDate()
    {
        return startDate;
    }

    public ObjectProperty<LocalDate> getEndDate()
    {
        return endDate;
    }

    public ObjectProperty<Guest> getGuestID()
    {
        return guestID;
    }
    public ObjectProperty<Room> getRoomID()
    {
        return roomID;
    }
    public SimpleIntegerProperty getBookingID()
    {
        return bookingID;
    }
    public SimpleStringProperty getState()
    {
        return state;
    }

}
