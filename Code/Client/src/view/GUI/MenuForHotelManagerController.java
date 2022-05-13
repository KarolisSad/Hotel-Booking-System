package view.GUI;

import view.ViewController;

import java.io.IOException;

/**
 * A class creating MenuForHotelManageController
 *
 * @author Group 5
 * @version 12/05/2022
 */

public class MenuForHotelManagerController extends ViewController {

    @Override
    protected void init() {}

    @Override
    public void reset() {

    }

    /**
     * A method that provides functionality to the Room OverView button.
     * When the button is clicked a new window opens that contains Room List View.
     *
     * @throws IOException
     */
    public void roomOverviewButton() throws IOException {
        getViewHandler().openView("RoomListView.fxml");
    }

    /**
     * A method that provides functionality to the Booking OverView button.
     * When the button is clicked a new window opens that contains Booking List View.
     *
     * @throws IOException
     */
    public void bookingsOverviewButton() throws IOException {
        getViewHandler().openView("BookingView.fxml");
   }

    /**
     * A method that provides functionality to the Guest OverView button.
     * When the button is clicked a new window opens that contains Guest List View.
     *
     * @throws IOException
     */
    public void guestsOverviewButton() throws IOException {
        getViewHandler().openView("GuestOverViewForHotelManager.fxml");
    }
}
