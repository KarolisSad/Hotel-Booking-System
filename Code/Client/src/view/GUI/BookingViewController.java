package view.GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import view.ViewController;
import viewModel.BookingViewModel;
import viewModel.SimpleBookingViewModel;
import viewModel.SimpleRoomViewModel;

import java.io.IOException;
import java.time.LocalDate;

/**
 * A class creating BookingViewController object.
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class BookingViewController extends ViewController {

    public Label errorLabel;
    private BookingViewModel viewModel;

    @FXML private TableView<SimpleBookingViewModel> table;
    @FXML private TableColumn<SimpleBookingViewModel, LocalDate> endDate;
    @FXML private TableColumn<SimpleBookingViewModel, LocalDate> startDate;
    @FXML private TableColumn<SimpleBookingViewModel, String> roomID;
    @FXML private TableColumn<SimpleBookingViewModel, Integer> guestID;
    @FXML private TableColumn<SimpleBookingViewModel, String> state;
    @FXML private TableColumn<SimpleBookingViewModel, Integer> bookingID;

    /**
     * A none argument, void method initializing instance variables.
     */
    @Override
    protected void init() {
        this.viewModel = getViewModelFactory().getBookingViewModel();

        startDate.setCellValueFactory(
                cellDate -> cellDate.getValue().startDateProperty());
        endDate.setCellValueFactory(
                cellData -> cellData.getValue().startDateProperty());
        guestID.setCellValueFactory(
                cellData -> cellData.getValue().guestIdProperty().asObject());
        roomID.setCellValueFactory(
                cellData -> cellData.getValue().roomIdProperty());
        bookingID.setCellValueFactory(
                cellData -> cellData.getValue().bookingIdProperty().asObject());
        state.setCellValueFactory(
                cellData -> cellData.getValue().bookingStateProperty());
        table.setItems(viewModel.getBookings());

        //
        table.getSelectionModel().selectedItemProperty()
                .addListener((n, oldValue, newValue) -> {
                    if (newValue != null)
                    {
                        viewModel.setSelected(newValue.bookingIdProperty().get());
                    }

//                    viewModel.setSelected(newValue.getBookingID().get());
                });

        reset();
    }


    @Override
    public void reset() {
        viewModel.updateBookingList();
    }

    public void cancelTheBookingButton() {
        viewModel.cancelBooking();
    }

    /**
     * A void method opening the menu view.
     */
    public void menuButton() throws IOException {
        getViewHandler().openView("MenuForHotelManager.fxml");
    }

    public void hideCancelledBookings()
    {
        viewModel.removeCanceledBookings();
        viewModel.removeCanceledBookings();
    }

    public void showCancelledBookings()
    {
        viewModel.showCancelledBookings();
    }

}
