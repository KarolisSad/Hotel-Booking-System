package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.time.LocalDate;


/**
 * A class providing functionality for BookingViewController
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class BookingViewModel {

    private Model model;
    private ObservableList<SimpleBookingViewModel> bookings;
    private int selectedID;

    /**
     * Constructor initializing instance variables.
     *
     * @param model interface
     */
    public BookingViewModel(Model model)
    {
        this.bookings = FXCollections.observableArrayList();
        this.model = model;

    }

    // for loop to add all Bookings :)

    public ObservableList<SimpleBookingViewModel> getBookings() {
        return bookings;
    }

    public void setSelected(int bookingID) {
        selectedID = bookingID;
    }

    public void updateBookingList() {
        bookings.clear();

        LocalDate start = LocalDate.now().plusDays(1);
        LocalDate end = LocalDate.now().plusDays(11);
        Guest guest = new Guest("John", "nan", "fds@.",11111111);
        Room room = new Room("111", RoomType.FAMILY,4);

        RoomBooking roomBooking = new RoomBooking(start,end,room,guest,"booked",5);
        RoomBooking roomBooking1 = new RoomBooking(start,end,room,guest,"open",10);
        bookings.add(new SimpleBookingViewModel(roomBooking));
        bookings.add(new SimpleBookingViewModel(roomBooking1));
        // update form server
    }

    public void cancelBooking()
    {
//        model.cancelBooking(selectedID);
        System.out.println(selectedID + " ID");
    }

}
