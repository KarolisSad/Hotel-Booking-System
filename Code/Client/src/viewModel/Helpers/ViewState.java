package viewModel.Helpers;

/**
 * A class that is used to check the state of the HotelManagerAddEditRoomViewModel.
 *
 * @author Group 5
 * @version 04/18/2022
 */
public class ViewState {
  private String number;
  private boolean add;

  /**
   * A ViewState constructor initializing all instance variables.
   */
  public ViewState(){
    this.number = null;
    this.add = false;
  }

  /**
   * A getter method that returns a number of a room.
   * @return String object
   */
  public String getNumber(){
    return number;
  }

  /**
   * A setter method that sets a number of a room.
   */
  public void setNumber(String number){
    this.number = number;
  }

  /**
   * A boolean method returning true if the AddEditViewControllers next action
   * is to add a new room.
   * And false if next action to edit an existing room.
   * @return true if next action is to add a room, and false if next action is edit.
   */
  public boolean isAdd(){
    return add;
  }

  /**
   * A boolean method setting an add variable to either true or false
   * @param add boolean. True if adding, false if not
   */
  public void setAdd(boolean add){
    this.add = add;
  }
}
