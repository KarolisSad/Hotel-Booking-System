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

  /**
   * Implementation of abstract init method from ViewController-class.
   * Intentionally left empty, as this view only contains buttons opening other views.
   */
  @Override protected void init()
  {

  }

  /**
   * A method that provides functionality to the Hotel Manager button.
   * When the button is clicked a new window opens that contains Room List View.
   *
   * @throws IOException
   */
  public void hotelManagerButton() throws IOException
  {
    getViewHandler().openView("MenuForHotelManager.fxml");
  }

  /**
   * Method called when clicking the show booking button in the GUI.
   * @throws IOException
   */
  public void showBookingButton() throws IOException
  {
    getViewHandler().openView("ShowBookingView.fxml");
  }

  /**
   * A method that provides functionality to Guest button.
   * When the button is clicked a new window that is reservation view opens.
   *
   * @throws IOException
   */
  public void guestButton() throws IOException
  {
    getViewHandler().openView("ReservationView.fxml");
  }

  /**
   * Method that provides functionality to the receptionist button.
   * When the button is pressed, the BookingsForReceptionistView window opens.
   * @throws IOException
   */
  public void receptionist() throws IOException
  {
    getViewHandler().openView("BookingsForReceptionistView.fxml");
  }

  /**
   * Method left empty intentionally, as there is nothing to reset.
   */
  @Override public void reset()
  {
    //
  }
}