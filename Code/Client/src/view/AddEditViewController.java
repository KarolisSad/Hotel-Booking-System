package view;

import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.util.converter.NumberStringConverter;
import model.RoomType;
import viewModel.AddEditViewModel;
import viewModel.ViewModelFactory;

import javax.swing.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Optional;

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

    reset();

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
   * Furthermore, this checks whether the user accessed the windows by clicking Add or Edit, and updates accordingly:
   * If add, the Room Id field is available for the user to write in, and if Edit the field has read-only access.
   *
   */
  @Override
  public void reset() {
    viewModel.reset();
    selectedType = viewModel.getType();
    typeDropdown.getSelectionModel().select(selectedType);
    if (!viewModel.getViewState().isAdd())
    {
      idField.textProperty().unbindBidirectional(viewModel.getRoomIdProperty());
      idField.textProperty().bind(viewModel.getRoomIdProperty());
      idField.setDisable(true);
    }
    else
    {
      idField.textProperty().unbind();
      idField.textProperty().bindBidirectional(viewModel.getRoomIdProperty());
      idField.setDisable(false);
    }

  }

  /**
   * A non argument method that creates s confirmation window changed in a window data
   * and creates a new room, corrects existing room or goes back
   * to a Room Details view.
   */
  public void confirmButton() throws IOException {



    if (viewModel.getViewState().isAdd())
    {

      selectedType = typeDropdown.getSelectionModel().getSelectedItem();
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

    else
    {
      selectedType = typeDropdown.getSelectionModel().getSelectedItem();
      viewModel.setType(selectedType);
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setHeaderText(
          "Confirm edit of room: " + viewModel.getRoomId());
      alert.setContentText("Type: " + getType() + "\nNumber of beds: " + viewModel.getNumberOfBeds());

      ButtonType confirm = new ButtonType("Confirm");
      ButtonType cancel = new ButtonType("Cancel",
          ButtonBar.ButtonData.CANCEL_CLOSE);
      alert.getButtonTypes().setAll(confirm, cancel);

      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == confirm)
      {
        viewModel.editRoomInfo();
        viewModel.reset();
      }
      else
      {
        alert.close();
      }

      viewHandler.openView("RoomListView.fxml");
    }
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
    reset();
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