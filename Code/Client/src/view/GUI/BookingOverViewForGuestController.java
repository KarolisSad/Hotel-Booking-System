package view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.ViewController;
import viewModel.BookingOverviewForGuestModel;
import viewModel.GuestMenuModel;
import viewModel.SimpleBookingViewModel;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class BookingOverViewForGuestController  extends ViewController
{
    @FXML private Label errorLabel;
    @FXML private TableView<SimpleBookingViewModel> bookingsTable;
    @FXML private TableColumn<SimpleBookingViewModel, Integer> bookingIdColumn;
    @FXML private TableColumn<SimpleBookingViewModel, LocalDate> startDateColumn;
    @FXML private TableColumn<SimpleBookingViewModel, LocalDate> endDateColumn;
    @FXML private TableColumn<SimpleBookingViewModel, String> roomNumberColumn;
    @FXML private TableColumn <SimpleBookingViewModel,String> stateColumn;
    private BookingOverviewForGuestModel viewModel;

    /**
     * Implementation of abstract init method from ViewController-class.
     * Intentionally left empty, as this view only contains buttons opening other views.
     */
    @Override protected void init()
    {
        this.viewModel = getViewModelFactory().getBookingOverviewForGuestModel();

        bookingIdColumn.setCellValueFactory(
                cellData -> cellData.getValue().bookingIdProperty().asObject());
        startDateColumn.setCellValueFactory(
                cellData -> cellData.getValue().startDateProperty());
        endDateColumn.setCellValueFactory(
                cellData -> cellData.getValue().endDateProperty());
        roomNumberColumn.setCellValueFactory(
                cellData -> cellData.getValue().roomIdProperty());
        stateColumn.setCellValueFactory(
                cellData -> cellData.getValue().bookingStateProperty());

        bookingsTable.setItems(viewModel.getBookings());

        bookingsTable.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {
                    viewModel.setSelected(newValue);
                });

        viewModel.reset();
    }

    @Override
    public void reset() {

    }

    public void cancelBookingButton() throws IOException
    {
            //Confirmation box
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //Style
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add("");
            dialogPane.getStylesheets().add(getClass().getResource("box.css").toExternalForm());
            dialogPane.getStyleClass().add("box.css");
            //
            alert.setHeaderText("Are you sure you want to cancel the booking");

            ButtonType confirm = new ButtonType("Confirm");
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(confirm, cancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == confirm)
            {
                viewModel.removeBooking();
                System.out.println("The booking is canceled.");
            }
            else
            {
                System.out.println("You pressed NO");
                alert.close();
            }
        }


    public void roomDetails() throws IOException {
        getViewHandler().openView("RoomOverviewForGuest.fxml");
        getViewModelFactory().getRoomOverviewForGuestModel()
                .setRoomBookingDetails(viewModel.getSelectedBookingProperty());

        getViewHandler().openView("RoomOverviewForGuest.fxml");
    }

    public void mainMenuButtonClicked() throws IOException {
        getViewHandler().openView("GuestMenuView.fxml");
    }
}
