package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import viewModel.BookingViewModel;

import java.io.IOException;
import java.util.List;


/**
 * A class creating BookingViewController object.
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class BookingViewController extends ViewController{

    @FXML
    private ListView<String> listOfBookings;
    public Label errorLabel;
    private BookingViewModel viewModel;

    /**
     * A none argument, void method initializing instance variables.
     */
    @Override
    protected void init() {
        this.viewModel = getViewModelFactory().getBookingViewModel();
        listOfBookings.setItems(viewModel.getBookings());
    }

    @Override
    public void reset() {

    }

    public void cancelTheBookingButton() {
    }

    /**
     * A void method opening the menu view.
     */
    public void menuButton() throws IOException {
        getViewHandler().openView("MenuForHotelManager.fxml");
    }
}
