package viewModel;

import javafx.collections.ObservableList;
import model.Model;

public class BookingsForReceptionistViewModel
{
  private Model model;

  private ObservableList<String> bookings;

  public BookingsForReceptionistViewModel(Model model)
  {
    this.model = model;
  }

  public void reset()
  {
    //todo do something
  }


}
