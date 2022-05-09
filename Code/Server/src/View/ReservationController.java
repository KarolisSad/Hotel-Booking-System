package View;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
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
public class ReservationController extends ViewController{
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private Label errorLabel;
    @FXML private ListView<String> availableRoom;
    private ReservationViewModel viewModel;
    private Region root;
    private ViewHandler viewHandler;

    /**
     * A none argument, void method initializing instance variables.
     */
    @Override
    public void init() {
        // Binding
        availableRoom.setItems(viewModel.getRooms());
        startDate.valueProperty().bindBidirectional(viewModel.getStartDatePicker());
        endDate.valueProperty().bindBidirectional(viewModel.getEndDatePicker());
        errorLabel.textProperty().bind(viewModel.getErrorLabel());
    }

    /**
     * A void method initializing instance variables.
     *
     * @param viewHandler A ViewHandler object which will be used to set the instance variable.
     * @param viewModelFactory  A ViewModelFactory object which will be used to set the instance variable.
     * @param root        A Region object which will be used to set the instance variable.
     */


    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root)
            throws RemoteException
    {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.viewModel = viewModelFactory.getReservationViewModel();
        init();
    }



    /**
     * A getter method returning the Region object.
     *
     * @return A Region object called root.
     */
    public Region getRoot(){
        return root;
    }

    //todo
    @Override
    public void reset() {
        //viewModel.clear();
    }

    /**
     * A void method that looks for available rooms.
     */

    public void lookForAvailableRooms() {
        viewModel.getAllAvailableRooms();
    }

    /**
     * A void method closing opening the GuestInformation view.
     */

    public void reservationButton() throws IOException {
        String selectedRoomFromListView = availableRoom.getSelectionModel().getSelectedItem();
        viewModel.reserveRoom(selectedRoomFromListView);
        viewHandler.openView("GuestInformationView.fxml");
    }

}
