package view;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
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

  private Region root;
  private ViewHandler viewHandler;
  private LogInViewModel viewModel;

  @Override public void init()
  {

  }

  /**
   * A void method initializing instance variables
   *
   * @param viewHandler A ViewHandler object is initializing the viewHandler instance variable.
   * @param viewModelFactory a ViewModelFactory object is initializing the viewModelFactory instance variable.
   * @param root A Region object which is initializing the root instance variable.
   */
  @Override public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
    this.viewModel = viewModelFactory.getLogInViewModel();
    init();
  }

  /**
   * A method that provides functionality to the Hotel Manager button.
   * When the button is clicked a new window opens that contains Room List View.
   *
   * @throws IOException
   */
  public void hotelManagerButton() throws IOException
  {
    viewHandler.openView("RoomListView.fxml");
  }

  /**
   * A method that provides functionality to Guest button.
   * When the button is clicked a new window that is reservation view opens.
   *
   * @throws IOException
   */
  public void guestButton() throws IOException
  {
    viewHandler.openView("ReservationView.fxml");
  }

  /**
   * A getter method returning the Region object.
   *
   * @return A Region object called root.
   */
  public Region getRoot(){
    return root;
  }

  @Override public void reset()
  {

  }
}