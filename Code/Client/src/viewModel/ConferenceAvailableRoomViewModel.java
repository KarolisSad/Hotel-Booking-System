package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import mediator.RoomBookingTransfer;
import mediator.RoomTransfer;
import model.Model;
import model.Room;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class providing functionality for ReservationViewController.
 *
 * @version 04/05/2022
 */

public class ConferenceAvailableRoomViewModel
{

    private Model model;
    private ObservableList<SimpleRoomViewModel> availableRooms;
    private ObjectProperty<SimpleRoomViewModel> selectedRoomProperty;
    private ObjectProperty<LocalDate> startDatePicker;
    private ObjectProperty<LocalDate> endDatePicker;
    private SimpleStringProperty errorLabel;
    private TemporaryInformation temp;

    /**
     * Constructor initializing instance variables.
     *
     * @param model    model interface
     */
    public ConferenceAvailableRoomViewModel(Model model)
    {
        this.model = model;
        this.availableRooms = FXCollections.observableArrayList();
        startDatePicker = new SimpleObjectProperty<>(LocalDate.now());
        endDatePicker = new SimpleObjectProperty<>(LocalDate.now().plusDays(2));
        selectedRoomProperty = new SimpleObjectProperty<>();
        this.errorLabel = new SimpleStringProperty("");
    }

    /**
     * Gets all available rooms within selected date interval from model,
     * calls displayRoomInListView method with received data.
     */
    public void getAllAvailableRooms()
    {
        RoomTransfer roomTransfer = model.availableConferenceRooms(
                dateFromDatePicker(startDatePicker.getValue().toString()),
                dateFromDatePicker(endDatePicker.getValue().toString()));

        if (roomTransfer.getMessage() == null)
        {
            displayRoomsInListView(roomTransfer.getRoomList());
        }
        else
        {
            availableRooms.clear();
            errorLabel.setValue(roomTransfer.getMessage());
        }
    }

    /**
     * Clears old values from ObservableList of availableRooms,
     * puts all received values to the list of availableRooms.
     *
     * @param rooms rooms to display in ListView
     */
    public void displayRoomsInListView(ArrayList<Room> rooms)
    {
        availableRooms.clear();
        try
        {
            for (Room room : rooms)
            {
                availableRooms.add(new SimpleRoomViewModel(room, getStartDatePicker().get(), getEndDatePicker().get()));
            }
            errorLabel.set("");
        }
        catch (Exception e)
        {
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
    //OLD
    //  public void reserveRoom(String roomName)
    //  {
    //    temp.setStartDate(
    //        dateFromDatePicker(startDatePicker.getValue().toString()));
    //    temp.setEndDate(dateFromDatePicker(endDatePicker.getValue().toString()));
    //    temp.setRoomID(roomName);
    //  }
    // NEW
    public void bookARoom(String roomName)
    {
        RoomBookingTransfer roomBookingTransfer = model.bookARoomWhenLoggedIn(
                roomName, dateFromDatePicker(startDatePicker.getValue().toString()),
                dateFromDatePicker(endDatePicker.getValue().toString()));

        if (!(roomBookingTransfer.getMessage().equals("null")))
        {
            errorLabel.setValue(
                    "Room wasn't added.." + roomBookingTransfer.getMessage());
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //Style
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add("");
            dialogPane.getStylesheets()
                    .add(getClass().getResource("box.css").toExternalForm());
            dialogPane.getStyleClass().add("box.css");

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Room successfully booked!");

            alert.showAndWait();
        }
    }

    /**
     * Takes datePickers date format and creates LocalDate object
     *
     * @param datePicker date received from DatePicker
     * @return newly created LocalDate object from datePickers value
     */
    private LocalDate dateFromDatePicker(String datePicker)
    {
        String lines[] = datePicker.split("-");
        int year = Integer.parseInt(lines[0].trim());
        int month = Integer.parseInt(lines[1].trim());
        int day = Integer.parseInt(lines[2].trim());
        return LocalDate.of(year, month, day);
    }

    /**
     * A method meant to call available rooms in the system
     *
     * @return availableRooms as an Observable list
     */
    public ObservableList<SimpleRoomViewModel> getRooms()
    {
        return availableRooms;
    }

    /**
     * A method used for returning the selected SimpleRoomViewModel property.
     * @return the selectedRoomProperty
     */
    public ObjectProperty<SimpleRoomViewModel> getSelected()
    {
        return selectedRoomProperty;
    }

    /**
     * Method used for setting the SimpleRoomViewModel property.
     * @param roomViewModel The object to set the variable to.
     */
    public void setSelected(SimpleRoomViewModel roomViewModel)
    {
        selectedRoomProperty.set(roomViewModel);
    }

    /**
     * A method meant for getting an error label
     *
     * @return errorLabel as SimpleStringProperty
     */
    public SimpleStringProperty getErrorLabel()
    {
        return errorLabel;
    }

    /**
     * A method meant to call start date objectProperty from the date piker
     *
     * @return startDatePicker
     */
    public ObjectProperty<LocalDate> getStartDatePicker()
    {
        return startDatePicker;
    }

    /**
     * A method meant to call end date objectProperty from the date piker
     *
     * @return endDatePicker
     */
    public ObjectProperty<LocalDate> getEndDatePicker()
    {
        return endDatePicker;
    }

    public void clear()
    {
        getAllAvailableRooms();
    }
}

