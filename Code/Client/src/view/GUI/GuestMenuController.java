package view.GUI;

import javafx.event.ActionEvent;
import view.ViewController;
import viewModel.GuestMenuModel;

import java.io.IOException;

/**
 * A class creating an GuestMenuController object.
 *
 * @author Group 5
 * @version 23/05/2022
 */

public class GuestMenuController extends ViewController
{
    private GuestMenuModel viewModel;

    /**
     * Implementation of abstract init method from ViewController-class.
     * Intentionally left empty, as this view only contains buttons opening other views.
     */
    @Override protected void init()
    {
        this.viewModel = getViewModelFactory().getGuestMenuModel();
    }


    @Override
    public void reset() {

    }

    /**
     * A method referring to ViewModel's method loggOf().
     * @throws IOException
     */
    public void logOff() throws IOException {
        viewModel.logOff();

        getViewHandler().openView("UserLoginMainView.fxml");
    }

    /**
     * A method opening ReservationView.
     * @throws IOException
     */
    public void makeABooking() throws IOException {
        getViewModelFactory().getReservationViewModel().getAllAvailableRooms();
        getViewHandler().openView("ReservationView.fxml");
    }

    /**
     * A method opening BookingOverViewForGuestView.
     * @throws IOException
     */
    public void bookingOverView() throws IOException {
        getViewHandler().openView("BookingOverViewForGuestView.fxml");
    }

    /**
     * A method sending personal details of the user to the GuestPersonalInformation.
     * @throws IOException
     */
    public void personalDetails() throws IOException {
        System.out.println("Personal details");
        getViewModelFactory().getGuestPersonalInformationViewModel().setUsername(viewModel.getUsername());
        getViewHandler().openView("GuestPersonalInformation.fxml");
        getViewModelFactory().getGuestPersonalInformationViewModel().setValues();

    }
}
