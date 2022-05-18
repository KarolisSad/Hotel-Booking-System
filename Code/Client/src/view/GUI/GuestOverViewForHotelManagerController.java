package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import view.ViewController;
import viewModel.GuestOverViewForHotelManagerModel;

import java.io.IOException;

/**
 * A class creating GuestOverViewForHotelManagerController object.
 *
 * @author Group 5
 * @version 12/05/2022
 */

public class GuestOverViewForHotelManagerController extends ViewController
{

  @FXML private ListView<String> listOfGuests;
  private GuestOverViewForHotelManagerModel viewModel;

  /**
   * A none argument, void method initializing instance variables.
   */
  @Override protected void init()
  {
    viewModel = getViewModelFactory().getGuestOverViewForHotelManagerModel();
    listOfGuests.setItems(viewModel.getGuests());
  }

  /**
   * Reset method. Meant for calling reset method in viewModel.
   */
  @Override public void reset()
  {

  }

  /**
   * A void method opening the menu view.
   */
  public void menuButton() throws IOException
  {
    getViewHandler().openView("MenuForHotelManager.fxml");
  }
}
