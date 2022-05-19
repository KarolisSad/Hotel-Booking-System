package view.GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.Model;
import view.ViewController;
import viewModel.GuestMenuModel;
import viewModel.RoomOverviewForGuestModel;

import java.io.IOException;

public class RoomOverviewForGuestController extends ViewController
{
    public TextField bookingIDtextField;
    public Label errorLabel;
    public TextField statusTextField;
    public TextField roomNumberField;
    public TextField nrOfBedsField;
    public TextField typeField;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    private RoomOverviewForGuestModel viewModel;

    /**
     * Implementation of abstract init method from ViewController-class.
     * Intentionally left empty, as this view only contains buttons opening other views.
     */
    @Override protected void init()
    {
        this.viewModel = getViewModelFactory().getRoomOverviewForGuestModel();

        try
        {
            bookingIDtextField.textProperty().bindBidirectional(viewModel.getBookingId());
            statusTextField.textProperty().bindBidirectional(viewModel.getStatus());
            typeField.textProperty().bindBidirectional(viewModel.getType());
            nrOfBedsField.textProperty().bindBidirectional(viewModel.getNrOfBeds());
            roomNumberField.textProperty().bindBidirectional(viewModel.getRoomNumber());
            startDatePicker.valueProperty()
                    .bindBidirectional(viewModel.getStartDatePicker());
            endDatePicker.valueProperty()
                    .bindBidirectional(viewModel.getEndDatePicker());
            errorLabel.textProperty()
                    .bindBidirectional(viewModel.getErrorLabelProperty());

        }
        catch (NullPointerException e)
        {
            viewModel.setErrorLabel("Please fill in all empty fields");
        }

        reset();
    }

    @Override
    public void reset() {

    }

    public void exitButton() throws IOException {
        getViewHandler().openView("BookingOverviewForGuestView.fxml");
    }
}
