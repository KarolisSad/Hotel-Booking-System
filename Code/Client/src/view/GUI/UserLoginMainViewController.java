package view.GUI;

import javafx.event.ActionEvent;
import view.ViewController;

import java.io.IOException;

/**
 * A class that creates UserLoginMainViewController object.
 *
 * @author Group 5
 * @version 25-05-22
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
     * A method that provides functionality to the admin button.
     * When the button is pressed that program opens the log in window for the administration.
     * @throws IOException
     */
    public void admin() throws IOException {
        getViewHandler().openView("AdminLogInView.fxml");
    }

    /**
     * A method meant to provide functionality for log in button.
     * When the button is pressed it will open a log in window for the hotel guests.
     * @throws IOException
     */
    public void login() throws IOException {
        getViewHandler().openView("UserLoginView.fxml");
    }

    /**
     * A method meant to provide functionality for the register button.
     * When the button is pressed the program opens a window, where a new user can be created.
     * @throws IOException
     */
    public void register() throws IOException {
        getViewHandler().openView("UserRegisterGuest.fxml");
    }
}