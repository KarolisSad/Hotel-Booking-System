package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;


/**
 * A class providing functionality for BookingViewController
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class BookingViewModel {

    private Model model;
    private ObservableList<String> bookings;

    /**
     * Constructor initializing instance variables.
     *
     * @param model interface
     */
    public BookingViewModel(Model model)
    {
        this.bookings = FXCollections.observableArrayList();
        this.model = model;
        bookings.add("test");
    }

    // for loop to add all Bookings :)

    public ObservableList<String> getBookings() {
        return bookings;
    }
}
