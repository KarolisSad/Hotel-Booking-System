package view.GUI;

import javafx.event.ActionEvent;
import view.ViewController;

import java.io.IOException;

/**
 * Class representing the UserLoginMainView window.
 *
 * @author Group 5
 * @version 23-05-2022
 */
public class UserLoginMainViewController extends ViewController
{
    @Override
    protected void init() {

    }

    @Override
    public void reset() {

    }

    /**
     * Method opening the BookingsForReceptionistView.
     * @throws IOException
     */
    public void receptionist() throws IOException {
        getViewHandler().openView("BookingsForReceptionistView.fxml");
    }

    /**
     * Method opening the MenuForHotelManager.
     * @throws IOException
     */
    public void hotelManager() throws IOException {
        getViewHandler().openView("MenuForHotelManager.fxml");
    }

    /**
     * Method opening the UserLoginView.
     * @throws IOException
     */
    public void login() throws IOException {
        getViewHandler().openView("UserLoginView.fxml");
    }

    /**
     * Method opening the UserRegisterGuest.
     * @throws IOException
     */
    public void register() throws IOException {
        getViewHandler().openView("UserRegisterGuest.fxml");
    }
}


//    public void receptionist() throws IOException {
//        getViewHandler().openView("GuestDetailsForReceptionist.fxml");
//    }