package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.GuestTransfer;
import model.Model;

/**
 * A class providing functionality for GuestOverViewForHotelManagerController
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class GuestOverViewForHotelManagerModel
{

  private Model model;
  private ObservableList<String> guests;

  /**
   * Constructor initializing instance variables.
   *
   * @param model interface
   */
  public GuestOverViewForHotelManagerModel(Model model)
  {
    this.guests = FXCollections.observableArrayList();
    this.model = model;

    guests.add("TODO:  change to listView");
  }

  /**
   * A method is used put guests into Guest transfer object.
   */
  public void getAllGuests()
  {
    guests.clear();
    GuestTransfer guest = model.getAllGuests();
    System.out.println(guest.getGuests());
    for (int i = 0; i < guest.getGuests().size(); i++)
    {
      guests.add(
          "FirstName: " + guest.getGuests().get(i).getfName() + "    LastName "
              + guest.getGuests().get(i).getlName() + "    Phone Number: "
              + guest.getGuests().get(i).getPhoneNr());
    }
  }

  /**
   * A method used to call all guests.
   * @return guests
   */
  public ObservableList<String> getGuests()
  {
    getAllGuests();
    return guests;
  }
}
