package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.RoomBookingTransfer;
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

    public ObservableList<SimpleBookingViewModel> getBookings() {
        return bookings;
    }

    public void setSelected(int bookingID) {
        selectedID = bookingID;
    }

    public void updateBookingList() {
        bookings.clear();

//        LocalDate start = LocalDate.now().plusDays(1);
//        LocalDate end = LocalDate.now().plusDays(11);
//        Guest guest = new Guest("John", "nan", "fds@.",11111111);
//        Room room = new Room("111", RoomType.FAMILY,4);
//        RoomBooking roomBooking = new RoomBooking(start,end,room,guest,"booked",5);
//        RoomBooking roomBooking1 = new RoomBooking(start,end,room,guest,"open",10);
//        bookings.add(new SimpleBookingViewModel(roomBooking));
//        bookings.add(new SimpleBookingViewModel(roomBooking1));
        // update form server

        RoomBookingTransfer bookingTransfer = model.getAllBookings();
        if (bookingTransfer.getMessage() == null)
        {
            for (int i = 0 ; i < bookingTransfer.getRoomBookings().size(); i ++)
            {
                bookings.add(new SimpleBookingViewModel(bookingTransfer.getRoomBookings().get(i)));
            }
        }
        else {
            // error label
        }
    }

    public void cancelBooking()
    {
        RoomBookingTransfer roomBookingTransfer = model.cancelBooking(selectedID);
        updateBookingList();
        if(hideCancelledRooms)
        {
            removeCanceledBookings();
            removeCanceledBookings();
        }

    }

    public void removeCanceledBookings()
    {
        hideCancelledRooms = true;
        for (int i = 0; i<bookings.size(); i++)
        {
            if (bookings.get(i).bookingStateProperty().get().equals("Cancelled"))
            {
                bookings.remove(i);
            }
        }
    }

    public void showCancelledBookings()
    {
        hideCancelledRooms = false;
        updateBookingList();
    }

}
