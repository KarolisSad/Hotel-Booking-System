package view.GUI;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;
import model.RoomType;
import view.ViewController;
import viewModel.AddEditViewModel;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

/**
 * A class creating an AddEditViewController object.
 *
 * @author Group 5
 * @version 09/05/2022
 */
public class AddEditViewController extends ViewController {
    @FXML private TextField idField;
    @FXML private ComboBox<RoomType> typeDropdown;
    @FXML private TextField nrOfBedsField;
    private RoomType selectedType;
    private Label errorLabel;

    private AddEditViewModel viewModel;

    /**
     * A none argument, void method binging instance variables to
     * an AddEditViewController variables.
     */
    @Override
    protected void init() {
        viewModel = getViewModelFactory().getAddEditViewModel();

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
     * A non argument method that calls the reset() method from viewModel.
     * Furthermore, this checks whether the user accessed the windows by clicking Add or Edit, and updates accordingly:
     * If add, the Room Id field is available for the user to write in, and if Edit the field has read-only access.
     */
    @Override
    public void reset() {
        viewModel.reset();
        selectedType = viewModel.getType();
        typeDropdown.getSelectionModel().select(selectedType);
        if (!viewModel.getViewState().isAdd()) {
            idField.textProperty().unbindBidirectional(viewModel.getRoomIdProperty());
            idField.textProperty().bind(viewModel.getRoomIdProperty());
            idField.setDisable(true);
        } else {
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

        if (viewModel.getViewState().isAdd()) {

            selectedType = typeDropdown.getSelectionModel().getSelectedItem();
            viewModel.setType(selectedType);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //Style
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add("");
            dialogPane.getStylesheets().add(getClass().getResource("box.css").toExternalForm());
            dialogPane.getStyleClass().add("box.css");
            //
            alert.setHeaderText("Are you sure you want to make changes?");
            alert.setContentText("Type: " + getType() + "\nNumber of beds: " + viewModel.getNumberOfBeds());

            ButtonType confirm = new ButtonType("Confirm");
            ButtonType cancel = new ButtonType("Cancel",
                    ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(confirm, cancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == confirm) {
                viewModel.addRoom();
                System.out.println("You confirmed.");
                getViewHandler().openView("RoomListView.fxml");
            } else {
                System.out.println("You pressed NO");
                alert.close();
            }

        } else {
            selectedType = typeDropdown.getSelectionModel().getSelectedItem();
            viewModel.setType(selectedType);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //Style
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add("");
            dialogPane.getStylesheets().add(getClass().getResource("box.css").toExternalForm());
            dialogPane.getStyleClass().add("box.css");
            //
            alert.setHeaderText(
                    "Confirm edit of room: " + viewModel.getRoomId());
            alert.setContentText("Type: " + getType() + "\nNumber of beds: " + viewModel.getNumberOfBeds());

            ButtonType confirm = new ButtonType("Confirm");
            ButtonType cancel = new ButtonType("Cancel",
                    ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(confirm, cancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == confirm) {
                viewModel.editRoomInfo();
                viewModel.reset();
            } else {
                alert.close();
            }

            try {
                getViewHandler().openView("RoomListView.fxml");
            } catch (IOException e) {

            }
        }


    }

    /**
     * A void method opening the RoomList view.
     */
    public void exitButton() throws IOException {
        reset();
        getViewHandler().openView("RoomListView.fxml");
    }

    /**
     * A getter method returning a RoomTypes object
     *
     * @return A RoomTypes object called selectedType.
     */
    public RoomType getType() {
        return selectedType;
    }

}