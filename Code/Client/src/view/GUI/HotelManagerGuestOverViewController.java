package view.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import view.ViewController;
import viewModel.HotelManagerGuestOverViewModel;
import viewModel.Helpers.SimpleGuestViewModel;

import java.io.IOException;

/**
 * A class creating HotelManagerGuestOverViewController object.
 *
 * @author Group 5
 * @version 12/05/2022
 */

public class HotelManagerGuestOverViewController extends ViewController
{


  @FXML private TableView<SimpleGuestViewModel> guestTable;

  @FXML private TableColumn<SimpleGuestViewModel, String> userNameColumn;
  @FXML private TableColumn<SimpleGuestViewModel, String> fNameColumn;
  @FXML private TableColumn<SimpleGuestViewModel, String> lNameColumn;
  @FXML private TableColumn<SimpleGuestViewModel, String> emailColumn;
  @FXML private TableColumn<SimpleGuestViewModel, Integer> phoneColumn;
  private HotelManagerGuestOverViewModel viewModel;

  /**
   * A none argument, void method initializing instance variables.
   */
  @Override protected void init()
  {
    viewModel = getViewModelFactory().getGuestOverViewForHotelManagerModel();

    userNameColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
    fNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
    lNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNrProperty().asObject());

    reset();
  }

  /**
   * Reset method. Meant for calling reset method in viewModel.
   */
  @Override public void reset()
  {
    guestTable.setItems(viewModel.getGuests());
  }

  /**
   * A void method opening the menu view.
   */
  public void menuButton() throws IOException
  {
    getViewHandler().openView("HotelManagerMenu.fxml");
  }
}