package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Room;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class providing functionality for ReservationViewController.
 *
 * @version 04/05/2022
 */

public class ReservationViewModel {

    private Model model;
    private ObservableList<String> availableRooms;
    private ObjectProperty<LocalDate> startDatePicker;
    private ObjectProperty<LocalDate> endDatePicker;
    private SimpleStringProperty errorLabel;
    private TemporaryInformation temp;


    /**
     * Constructor initializing instance variables.
     *
     * @param model    model interface
     * @param tempInfo shared object with GuestInformationViewModel to store selected room
     */
    public ReservationViewModel(Model model, TemporaryInformation tempInfo) {
        this.model = model;
        this.availableRooms = FXCollections.observableArrayList();
        startDatePicker = new SimpleObjectProperty<>();
        endDatePicker = new SimpleObjectProperty<>();
        this.errorLabel = new SimpleStringProperty("");
        this.temp = tempInfo;
    }

    /**
     * Gets all available rooms within selected date interval from model,
     * calls displayRoomInListView method with received data.
     */
    public void getAllAvailableRooms() {
        displayRoomsInListView(model.availableRooms(dateFromDatePicker(startDatePicker.getValue().toString()),
                dateFromDatePicker(endDatePicker.getValue().toString())));
    }

    /**
     * Clears old values from ObservableList of availableRooms,
     * puts all received values to the list of availableRooms.
     *
     * @param rooms rooms to display in ListView
     */
    public void displayRoomsInListView(ArrayList<Room> rooms) {
        availableRooms.clear();
        try {
            for (int i = 0; i < rooms.size(); i++) {
                availableRooms.add(rooms.get(i).getRoomId());
            }
        } catch (Exception e) {
            errorLabel.setValue("Choose start/end dates");
        }
    }

    /**
     * Sets new values for TemporaryInformation object for:
     * startDate value received from startDatePicker,
     * endDate value received from endDatePicker,
     * roomID value received from argument
     *
     * @param roomName selected room ID
     */
    public void reserveRoom(String roomName) {
        temp.setStartDate(dateFromDatePicker(startDatePicker.getValue().toString()));
        temp.setEndDate(dateFromDatePicker(endDatePicker.getValue().toString()));
        temp.setRoomID(roomName);
    }

    /**
     * Takes datePickers date format and creates LocalDate object
     *
     * @param datePicker date received from DatePicker
     * @return newly created LocalDate object from datePickers value
     */
    private LocalDate dateFromDatePicker(String datePicker) {
        String lines[] = datePicker.split("-");
        int year = Integer.parseInt(lines[0].trim());
        int month = Integer.parseInt(lines[1].trim());
        int day = Integer.parseInt(lines[2].trim());
        return LocalDate.of(year, month, day);
    }

    public ObservableList<String> getRooms() {
        return availableRooms;
    }

    public SimpleStringProperty getErrorLabel() {
        return errorLabel;
    }

    public ObjectProperty<LocalDate> getStartDatePicker() {
        return startDatePicker;
    }

    public ObjectProperty<LocalDate> getEndDatePicker() {
        return endDatePicker;
    }

}

