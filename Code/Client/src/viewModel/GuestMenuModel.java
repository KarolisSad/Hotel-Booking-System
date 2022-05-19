package viewModel;

import mediator.RoomBookingTransfer;
import model.Model;

public class GuestMenuModel {

    private Model model;

    public GuestMenuModel(Model model) {
        this.model = model;
    }

    public void logOff()
    {
        model.logOutForGuest();
    }

    public void getMyBookings() {
        RoomBookingTransfer r = model.getBookingsWhenLoggedIn();
        System.out.println(r.toString());
    }
}
