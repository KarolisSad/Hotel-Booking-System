package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import view.ViewController;
import viewModel.LogInViewModel;
import viewModel.ViewModelFactory;

import java.io.IOException;

/**
 * A class creating LogInViewController object
 *
 * @author Group 5
 * @version 09/05/2022
 */
public class LogInViewController extends ViewController
{

    @Override
    protected void init() {}

    /**
     * A method that provides functionality to the Hotel Manager Button
     * When the button is pressed the program goes back to BookingForReceptionistView.
     *
     * @throws IOException
     */
    public void receptionistButton() throws IOException {
        getViewHandler().openView("BookingForReceptionistView.fxml");
    }

    /**
     * A method that provides functionality to the Hotel Manager button.
     * When the button is clicked a new window opens that contains Room List View.
     *
     * @throws IOException
     */
    public void hotelManagerButton() throws IOException {
        getViewHandler().openView("RoomListView.fxml");
    }

    /**
     * A method that provides functionality to Guest button.
     * When the button is clicked a new window that is reservation view opens.
     *
     * @throws IOException
     */
    public void guestButton() throws IOException {
        getViewHandler().openView("ReservationView.fxml");
    }

    /**
     * A getter method returning the Region object.
     *
     * @return A Region object called root.
     */


    @Override
    public void reset() {

    }
}