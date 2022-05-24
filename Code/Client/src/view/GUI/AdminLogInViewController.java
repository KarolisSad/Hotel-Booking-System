package view.GUI;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import view.ViewController;
import viewModel.AdminLogInViewModel;


import java.io.IOException;

public class AdminLogInViewController extends ViewController
{
  private AdminLogInViewModel viewModel;
  @FXML private TextField username;
  @FXML private TextField password;
  @FXML private Label errorLabel;


  @Override protected void init()
  {
    viewModel = getViewModelFactory().getAdminLogInViewModel();
    errorLabel.setText("");
    username.setText("");
    password.setText("");

    reset();
  }

  @Override public void reset()
  {
    password.setText("");
    errorLabel.setText("");
    username.setText("");
  }

  public void LogInButton() throws IOException
  {
    String userID = username.getText();
    String pass = password.getText();

    if ((viewModel.getLogInfo().containsKey(userID)) && (viewModel.getLogInfo().get(userID).equals(pass))){
      if (userID.equals("hotel"))
      {
        getViewHandler().openView("MenuForHotelManager.fxml");
      }
      else
      {
        getViewHandler().openView("BookingsForReceptionistView.fxml");
      }
    }
    else
    {
      errorLabel.setText("The password or username is incorrect");
    }
  }

  public void MainMenuButton() throws IOException
  {
    getViewHandler().openView("UserLoginMainView.fxml");
  }


}
