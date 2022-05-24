package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import mediator.RoomTransfer;
import view.ViewController;
import viewModel.ConferenceAvailableRoomViewModel;
import viewModel.ReservationViewModel;
import viewModel.ViewModelFactory;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * A class creating an ReservationController object.
 *
 * @author Group 5
 * @version 04/05/2022
 */
public class ConferenceAvailableRoomViewController extends ViewController
{
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private Label errorLabel;
    @FXML private ListView<String> availableRoom;
    private ConferenceAvailableRoomViewModel viewModel;

    /**
     * A none argument, void method initializing instance variables.
     */
    @Override protected void init()
    {

        viewModel = getViewModelFactory().getConferenceAvailableRoomViewModel();

        // Binding
        availableRoom.setItems(viewModel.getRooms());
        startDate.valueProperty().bindBidirectional(viewModel.getStartDatePicker());
        endDate.valueProperty().bindBidirectional(viewModel.getEndDatePicker());
        errorLabel.textProperty().bind(viewModel.getErrorLabel());
    }

    /**
     * Method used for resetting the view.
     * This is done by calling the clear() method in the viewModel.
     */
    @Override public void reset()
    {
//        viewModel.clear();
    }

    /**
     * A void method that looks for available rooms.
     */

    public void lookForAvailableRooms()
    {
        viewModel.getAllAvailableRooms();
    }

    /**
     * A void method  opening the GuestInformation view.
     */

    public void reservationButton() throws IOException
    {
        String selectedRoomFromListView = availableRoom.getSelectionModel()
                .getSelectedItem();
        viewModel.bookARoom(selectedRoomFromListView);
        reset();
    }

    /**
     * Method called when clicking the back button in the GUI.
     * Opens the LogInView view.
     * @throws IOException
     */
    public void back() throws IOException
    {
        getViewHandler().openView("GuestMenuView.fxml");
    }



}