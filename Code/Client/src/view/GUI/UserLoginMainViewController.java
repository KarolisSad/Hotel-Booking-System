package view.GUI;

import javafx.event.ActionEvent;
import view.ViewController;

import java.io.IOException;


public class UserLoginMainViewController extends ViewController
{
    @Override
    protected void init() {

    }

    @Override
    public void reset() {

    }

    public void receptionist() throws IOException {
        getViewHandler().openView("LoginView.fxml");
    }

    public void hotelManager() throws IOException {
        getViewHandler().openView("LoginView.fxml");
    }

    public void login() throws IOException {
        getViewHandler().openView("UserLoginView.fxml");
    }

    public void register() throws IOException {
        getViewHandler().openView("UserRegisterGuest.fxml");
    }
}


//    public void receptionist() throws IOException {
//        getViewHandler().openView("GuestDetailsForReceptionist.fxml");
//    }