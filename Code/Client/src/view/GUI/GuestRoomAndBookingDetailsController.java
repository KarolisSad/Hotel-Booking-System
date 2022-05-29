package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.ViewController;
import viewModel.GuestRoomAndBookingDetailsViewModel;

import java.io.IOException;

/**
 * A class creating a RoomDetailsForReceptionist Object
 *
 * @author Group 5
 * @version 23-05-2022
 */
public class GuestRoomAndBookingDetailsController extends ViewController
{
    public TextField bookingIDtextField;
    public Label errorLabel;
    public TextField statusTextField;
    public TextField roomNumberField;
    public TextField nrOfBedsField;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    @FXML private TextField roomTypeField;
    @FXML private TextField priceField;
    private GuestRoomAndBookingDetailsViewModel viewModel;

    /**
     * Implementation of abstract init method from ViewController-class.
     * Intentionally left empty, as this view only contains buttons opening other views.
     */
    @Override protected void init()
    {
        this.viewModel = getViewModelFactory().getRoomOverviewForGuestModel();

        try
        {
            //TODO shouldn't all bindings here be regular and not bi-directional??

            bookingIDtextField.textProperty().bindBidirectional(viewModel.getBookingId());
            statusTextField.textProperty().bindBidirectional(viewModel.getStatus());
            roomTypeField.textProperty().bindBidirectional(viewModel.getType());
            nrOfBedsField.textProperty().bindBidirectional(viewModel.getNrOfBeds());
            roomNumberField.textProperty().bindBidirectional(viewModel.getRoomNumber());
            priceField.textProperty().bindBidirectional(viewModel.getPrice());
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

        viewModel.reset();
    }

    /**
     * Method called when the back button is pressed.
     * Opens the BookingOverviewForGuestView window.
     * @throws IOException
     */
    public void exitButton() throws IOException {
        getViewModelFactory().getBookingOverviewForGuestModel().reset();
        getViewHandler().openView("GuestBookingOverView.fxml");
    }
}
