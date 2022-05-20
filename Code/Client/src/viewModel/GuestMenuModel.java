package viewModel;

import mediator.RoomBookingTransfer;
import model.Model;

public class GuestMenuModel {

    private Model model;
    private String username;

    public GuestMenuModel(Model model) {
        this.model = model;
    }

    public void logOff()
    {
        model.logOutForGuest();
    }

    public void passTheUsernameInfo(String username){
        this.username = username;
        System.out.println("username passed: " + username);
    }

    public String getUsername(){
        return username;
    }

    public void getMyBookings() {
        RoomBookingTransfer r = model.getBookingsWhenLoggedIn();
        System.out.println(r.toString());
    }
}
