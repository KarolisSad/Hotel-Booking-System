package view.GUI;

import javafx.event.ActionEvent;
import view.ViewController;
import viewModel.GuestMenuModel;

import java.io.IOException;

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


    public void logOff() throws IOException {
        viewModel.logOff();

        getViewHandler().openView("UserLoginMainView.fxml");
    }

    public void makeABooking() throws IOException {
        getViewHandler().openView("ReservationView.fxml");
    }

    public void bookingOverView() {
        System.out.println("Booking overview");
    }

    public void personalDetails() {
        System.out.println("Personal details");
    }
}