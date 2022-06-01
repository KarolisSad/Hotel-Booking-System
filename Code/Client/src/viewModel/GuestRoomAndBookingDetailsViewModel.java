package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.Room;
import model.RoomType;
import viewModel.Helpers.SimpleBookingViewModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class GuestRoomAndBookingDetailsViewModel
{

    private Model model;
    private StringProperty type; // String type
    private StringProperty bookingId;
    private StringProperty status;
    private StringProperty roomNumber;
    private StringProperty nrOfBeds;
    private StringProperty price;
    private ArrayList<RoomType> types;
    private ObjectProperty<LocalDate> startDatePicker;
    private ObjectProperty<LocalDate> endDatePicker;
    private SimpleStringProperty errorLabel;

    public GuestRoomAndBookingDetailsViewModel(Model model)
    {
        this.model = model;
        bookingId = new SimpleStringProperty();
        status = new SimpleStringProperty();
        roomNumber = new SimpleStringProperty();
        type = new SimpleStringProperty();
        price = new SimpleStringProperty();
        startDatePicker = new SimpleObjectProperty<>();
        endDatePicker = new SimpleObjectProperty<>();
        nrOfBeds = new SimpleStringProperty();
        errorLabel = new SimpleStringProperty("");
    }

    public void reset()
    {
        errorLabel.set("");
    }

    /**
     * A getter returning the start date of the date picker
     *
     * @return startDatePicket
     */
    public ObjectProperty<LocalDate> getStartDatePicker()
    {
        return startDatePicker;
    }

    /**
     * A getter returning the end date of the date picker
     *
     * @return endDatePicker
     */
    public ObjectProperty<LocalDate> getEndDatePicker()
    {
        return endDatePicker;
    }

    /**
     * A getter returning the error label encapsulated in String property object
     *
     * @return errorLabel
     */
    public StringProperty getErrorLabelProperty()
    {
        return errorLabel;
    }

    /**
     * A setter to give a value to a error label
     *
     */
    public void setErrorLabel(String errorLabel)
    {
        this.errorLabel.set(errorLabel);
    }

    /**
     * A getter that returns a booking ID.
     *
     * @return bookingId
     */
    public StringProperty getBookingId()
    {
        return bookingId;
    }

    /**
     * A getter that return number of beds
     *
     * @return nrOfBeds
     */
    public StringProperty getNrOfBeds()
    {
        return nrOfBeds;
    }

    /**
     * A getter that return the status of the booking
     *
     * @return status
     */
    public StringProperty getStatus()
    {
        return status;
    }

    /**
     * getter that returns the selected room number
     *
     * @return roomNumbers
     */
    public StringProperty getRoomNumber()
    {
        return roomNumber;
    }

    /**
     * Getter for room type
     * @return type
     */
    public StringProperty getType()
    {
        return type;
    }

    public StringProperty getPrice()
    {
        return price;
    }

    /**
     * Setter for room booking details.
     * This method retrieves a specific room from the model
     * and sets all variables.
     * @param selectedBooking the booking selected.
     */
    public void setRoomBookingDetails(SimpleBookingViewModel selectedBooking) {
        try {
            Room room = model.getRoom(selectedBooking.roomIdProperty().get()).getRoom();
            roomNumber.setValue(room.getRoomId());
            nrOfBeds.setValue(String.valueOf(room.getNumberOfBeds()));
            type.setValue(room.getRoomType().toString());
            price.setValue(String.valueOf(room.getPrice()));
            bookingId.setValue(
                    String.valueOf(selectedBooking.bookingIdProperty().get()));
            status.setValue(selectedBooking.bookingStateProperty().get());
            startDatePicker.setValue(selectedBooking.getStartDate());
            endDatePicker.setValue(selectedBooking.getEndDate());
        }
       catch (Exception e)
       {
           errorLabel.setValue("Please select booking!");
       }
    }
}
