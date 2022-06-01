package viewModel;

import mediator.RoomBookingTransfer;
import model.Model;

/**
 * A class providing functionality for GuestMenuController.
 *
 * @version 23/05/2022
 */
public class GuestMenuModel {

    private Model model;
    private String username;

    /**
     * Constructor initializing instance variables.
     * @param model model
     */
    public GuestMenuModel(Model model) {
        this.model = model;
    }

    /**
     * Method calling logOutForGuest() from the model.
     */
    public void logOff()
    {
        model.logOutForGuest();
    }

    /**
     * Method setting username information.
     * @param username the value to set the username to
     */
    public void passTheUsernameInfo(String username){
        this.username = username;
    }

    /**
     * Method getting username information.
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**
     * Method calling getBookingsWhenLoggedIn() from the model.
     */
    public void getMyBookings() {
        RoomBookingTransfer r = model.getBookingsWhenLoggedIn();
    }
}
