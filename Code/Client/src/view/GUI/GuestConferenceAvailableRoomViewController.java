package view.GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.ViewController;
import viewModel.ConferenceAvailableRoomViewModel;
import viewModel.Helpers.SimpleRoomViewModel;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.function.UnaryOperator;

/**
 * A class creating an GuestReservationController object.
 *
 * @author Group 5
 * @version 04/05/2022
 */
public class GuestConferenceAvailableRoomViewController extends ViewController
{

    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private Label errorLabel;
    @FXML private TableView<SimpleRoomViewModel> availableRoom;
    @FXML private TableColumn<SimpleRoomViewModel, String> roomIdColumn;
    @FXML private TableColumn<SimpleRoomViewModel, Integer> capacityColumn;
    @FXML private TableColumn<SimpleRoomViewModel, String> dailyPriceColumn;
    @FXML private TableColumn<SimpleRoomViewModel, String> totalPriceColumn;
    private ConferenceAvailableRoomViewModel viewModel;

    /**
     * A none argument, void method initializing instance variables.
     */
    @Override protected void init()
    {

        viewModel = getViewModelFactory().getConferenceAvailableRoomViewModel();
        DecimalFormat currencyFormat = new DecimalFormat("0.00 DKK");

        UnaryOperator<TextFormatter.Change> numberValidationFormatter = change -> {
            if (change.getText().matches("\\d+"))
            {
                return change;
            }
            else
            {
                change.setText("");
                return change;
            }
        };

        startDate.getEditor().setDisable(true);
        startDate.getEditor().setOpacity(1);
        endDate.getEditor().setDisable(true);
        endDate.getEditor().setOpacity(1);

        // Binding
        startDate.valueProperty().bindBidirectional(viewModel.getStartDatePicker());
        endDate.valueProperty().bindBidirectional(viewModel.getEndDatePicker());
        errorLabel.textProperty().bind(viewModel.getErrorLabel());

        roomIdColumn.setCellValueFactory(cellData -> cellData.getValue()
            .roomNumberProperty());
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().numberOfBedsProperty().asObject());
        dailyPriceColumn.setCellValueFactory(cellData -> {
            String formattedPrice = currencyFormat.format(cellData.getValue().dailyPriceProperty().get());
            return new SimpleStringProperty(formattedPrice);
        });
        totalPriceColumn.setCellValueFactory(cellData -> {
            String formattedPrice = currencyFormat.format(cellData.getValue().totalPriceProperty().get());
            return new SimpleStringProperty(formattedPrice);
        });

        availableRoom.setItems(viewModel.getRooms());

        availableRoom.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            viewModel.setSelected(newVal);
        });


        reset();
    }

    /**
     * Method used for resetting the view.
     * This is done by calling the clear() method in the viewModel.
     */
    @Override public void reset()
    {
       viewModel.clear();
    }

    /**
     * A void method that looks for available rooms.
     */

    public void lookForAvailableRooms()
    {
        viewModel.getAllAvailableRooms();
    }

    /**
     * A void method  opening the GuestInformation view.
     */

    public void reservationButton() throws IOException
    {
        viewModel.bookARoom(viewModel.getSelected().get().roomNumberProperty().get());
        reset();
    }

    /**
     * Method called when clicking the back button in the GUI.
     * Opens the LogInView view.
     * @throws IOException
     */
    public void back() throws IOException
    {
        getViewHandler().openView("GuestMenuView.fxml");
    }



}