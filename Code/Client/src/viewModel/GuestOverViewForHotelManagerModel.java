package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.GuestTransfer;
import model.Model;
import viewModel.Helpers.SimpleGuestViewModel;

/**
 * A class providing functionality for HotelManagerGuestOverViewController
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class GuestOverViewForHotelManagerModel
{

  private Model model;
  private ObservableList<SimpleGuestViewModel> guests;

  /**
   * Constructor initializing instance variables.
   *
   * @param model interface
   */
  public GuestOverViewForHotelManagerModel(Model model)
  {
    this.guests = FXCollections.observableArrayList();
    this.model = model;
  }

  /**
   * A method is used put guests into Guest transfer object.
   */
  public void getAllGuests()
  {
    guests.clear();
    GuestTransfer guest = model.getAllGuests();
    for (int i = 0; i < guest.getGuests().size(); i++)
    {
      guests.add(new SimpleGuestViewModel(guest.getGuests().get(i)));
    }
  }

  /**
   * A method used to call all guests.
   * @return guests
   */
  public ObservableList<SimpleGuestViewModel> getGuests()
  {
    getAllGuests();
    return guests;
  }
}
