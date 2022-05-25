package view.GUI;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import view.ViewController;
import viewModel.AdminLogInViewModel;


import java.io.IOException;

/**
 * A class where AdminLogInViewController is created and the functionality is given to the gui.
 *
 * @author Group 5
 * @version 25-05-25
 */
public class AdminLogInViewController extends ViewController
{
  private AdminLogInViewModel viewModel;
  @FXML private TextField username;
  @FXML private TextField password;
  @FXML private Label errorLabel;


  /**
   * A none argument, void method initializing instance variables.
   */
  @Override protected void init()
  {
    viewModel = getViewModelFactory().getAdminLogInViewModel();
    username.textProperty().bindBidirectional(viewModel.getUsername());
    password.textProperty().bindBidirectional(viewModel.getPassword());

    reset();
  }

  /**
   * A method used to reset the error label and
   * text fields of username and password to an empty string
   */
  @Override public void reset()
  {
    password.setText("");
    errorLabel.setText("");
    username.setText("");
  }

  /**
   * A method used to give functionality to the log in button.
   * In the method the program checks if the given username and password are correct
   * and if so it will open depending on the log in info either receptionist view or hotel manager view.
   * Otherwise it will provide an error label.
   * @throws IOException
   */
  public void LogInButton() throws IOException
  {
    String userID = viewModel.getUsername().get();
    String pass = viewModel.getPassword().get();

    if ((viewModel.getLogInfo().containsKey(userID)) && (viewModel.getLogInfo().get(userID).equals(pass))){
      if (userID.equals("hotel"))
      {
        getViewHandler().openView("HotelManagerMenu.fxml");
      }
      else
      {
        getViewHandler().openView("ReceptionistBookingView.fxml");
      }
    }
    else
    {
      errorLabel.setText("The password or username is incorrect");
    }
  }

  /**
   * A method used to provide functionality to the main menu button.
   * If the button is pressed the program goes back to the first window.
   * @throws IOException
   */
  public void MainMenuButton() throws IOException
  {
    getViewHandler().openView("MainMenu.fxml");
  }


}
