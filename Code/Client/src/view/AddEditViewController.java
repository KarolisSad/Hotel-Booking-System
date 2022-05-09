package view;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.converter.NumberStringConverter;
import model.RoomType;
import viewModel.AddEditViewModel;
import viewModel.ViewModelFactory;

import javax.swing.*;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * A class creating an AddEditViewController object.
 *
 * @author Group 5
 * @version 09/05/2022
 */
public class AddEditViewController extends ViewController {
  public TextField idField;
  public ComboBox<RoomType> typeDropdown;
  public TextField nrOfBedsField;
  public RoomType selectedType;
  public Label errorLabel;
  private Region root;
  private ViewHandler viewHandler;
  private AddEditViewModel viewModel;

  /**
   * A none argument, void method binging instance variables to
   * an AddEditViewController variables.
   */
  @Override
  public void init() {

    try
    {
    // Binding
    idField.textProperty().bindBidirectional(viewModel.getRoomIdProperty());

    Bindings.bindBidirectional(nrOfBedsField.textProperty(), viewModel.numberOfBedsProperty(), new NumberStringConverter());
    typeDropdown.getItems().removeAll(typeDropdown.getItems());
    typeDropdown.getItems().add(RoomType.FAMILY);
    typeDropdown.getItems().add(RoomType.DOUBLE);
    typeDropdown.getItems().add(RoomType.SINGLE);
    typeDropdown.getItems().add(RoomType.SUITE);

    errorLabel.textProperty().bind(viewModel.errorPropertyProperty());
    }
    catch (NullPointerException e)
    {
      //
    }
  }

  /**
   * A void method initializing instance variables.
   *
   * @param viewHandler A ViewHandler object which will be used to set the instance variable.
   * @param viewModelFactory  A ViewModelFactory object which will be used to set the instance variable.
   * @param root        A Region object which will be used to set the instance variable.
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root)
      throws RemoteException
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
    this.viewModel = viewModelFactory.getAddEditViewModel();
    init();
  }

  /**
   * A non argument method that calls the reset() method from viewModel.
   */
  @Override
  public void reset() {
    viewModel.reset();
  }

  /**
   * A non argument method that creates s confirmation window changed in a window data
   * and creates a new room, corrects existing room or goes back
   * to a Room Details view.
   */
  public void confirmButton() throws IOException {

    selectedType = typeDropdown.getSelectionModel().getSelectedItem();
    System.out.println(selectedType);
    viewModel.setType(selectedType);

    JFrame jframe = new JFrame();
    int result = JOptionPane.showConfirmDialog(jframe, "Are you sure you want to make changes?");

    if (result == 0) {
      viewModel.addRoom();
      System.out.println("You confirmed.");
      viewHandler.openView("RoomListView.fxml");
    }
    else if (result == 1)
      System.out.println("You pressed NO");
  }

  /**
   * A getter method returning a Region object.
   *
   * @return A Region object called root.
   */
  @Override
  public Region getRoot() {
    return root;
  }

  /**
   * A getter method returning a ViewHandler object
   * @return A ViewHandler object called viewHandler.
   */
  @Override
  public ViewHandler getViewHandler() {
    return viewHandler;
  }

  /**
   * A void method opening the RoomList view.
   */
  public void exitButton() throws IOException {
    viewHandler.openView("RoomListView.fxml");
  }

  /**
   * A getter method returning a RoomTypes object
   * @return A RoomTypes object called selectedType.
   */
  public RoomType getType(){
    return selectedType;
  }

}