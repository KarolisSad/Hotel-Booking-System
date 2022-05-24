package view.GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import mediator.RoomTransfer;
import model.RoomType;
import view.ViewController;
import viewModel.ReservationViewModel;
import viewModel.SimpleRoomViewModel;
import viewModel.ViewModelFactory;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.function.UnaryOperator;

/**
 * A class creating an ReservationController object.
 *
 * @author Group 5
 * @version 04/05/2022
 */
public class ReservationController extends ViewController
{
  @FXML private ComboBox<String> roomTypeFilter;
  @FXML private TextField bedsFilter;
  @FXML private TextField priceFromFilter;
  @FXML private TextField priceToFilter;
  @FXML private TableView<SimpleRoomViewModel> availableRoomsTable;
  @FXML private TableColumn<SimpleRoomViewModel, String> roomNumberColumn;
  @FXML private TableColumn<SimpleRoomViewModel, String> roomTypeColumn;
  @FXML private TableColumn<SimpleRoomViewModel, Integer> numberOfBedsColumn;
  @FXML private TableColumn<SimpleRoomViewModel, String> dailyPriceColumn;
  @FXML private TableColumn<SimpleRoomViewModel, String> totalPriceColumn;
  @FXML private DatePicker startDate;
  @FXML private DatePicker endDate;
  @FXML private Label errorLabel;
  private ReservationViewModel viewModel;

  private int priceToValue;
  private int priceFromValue;
  private boolean lowValueInput;
  private boolean highValueInput;
  private boolean filterByPriceActive;
  private boolean filterByBeds;

  /**
   * A none argument, void method initializing instance variables.
   */
  @Override protected void init()
  {

    viewModel = getViewModelFactory().getReservationViewModel();
    DecimalFormat currencyFormat = new DecimalFormat("0.00 DKK");

    // Binding
    startDate.valueProperty().bindBidirectional(viewModel.getStartDatePicker());
    endDate.valueProperty().bindBidirectional(viewModel.getEndDatePicker());
    errorLabel.textProperty().bind(viewModel.getErrorLabel());

    // Table
    roomNumberColumn.setCellValueFactory(
        cellData -> cellData.getValue().roomNumberProperty());
    roomTypeColumn.setCellValueFactory(
        cellData -> cellData.getValue().roomTypeProperty().asString());
    numberOfBedsColumn.setCellValueFactory(
        cellData -> cellData.getValue().numberOfBedsProperty().asObject());

    // Formatter that makes sure that nothing other than number can be typed in the textfield.
    UnaryOperator<TextFormatter.Change> bedCountValidationFormatter = change -> {
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

    // Format price information to danish standard
    dailyPriceColumn.setCellValueFactory(cellData -> {
      String formattedPrice = currencyFormat.format(
          cellData.getValue().dailyPriceProperty().get());
      return new SimpleStringProperty(formattedPrice);
    });
    totalPriceColumn.setCellValueFactory(cellData -> {
      String formattedPrice = currencyFormat.format(
          cellData.getValue().totalPriceProperty().get());
      return new SimpleStringProperty(formattedPrice);
    });

    availableRoomsTable.setItems(viewModel.getRooms());

    // Add event listener when selecting a room
    availableRoomsTable.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldValue, newValue) -> {
          viewModel.setSelected(newValue);
        });

    //Filtering

    // RoomType filter
    roomTypeFilter.getItems().removeAll(roomTypeFilter.getItems());
    roomTypeFilter.getItems().add("Single");
    roomTypeFilter.getItems().add("Double");
    roomTypeFilter.getItems().add("Family");
    roomTypeFilter.getItems().add("Suite");
    roomTypeFilter.getItems().add("All");
    roomTypeFilter.getSelectionModel().select("All");

    roomTypeFilter.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldVal, newVal) -> {
          filterByRoomTypes(newVal);
        });

    //Number of beds filter
    bedsFilter.textFormatterProperty()
        .setValue(new TextFormatter<>(bedCountValidationFormatter));
    bedsFilter.textProperty().addListener((obs, oldVal, newVal) -> {
      filterByBedCount(newVal);
    });

    // Price Filter
    priceFromFilter.textFormatterProperty().setValue(new TextFormatter<>(bedCountValidationFormatter));
    priceToFilter.textFormatterProperty().setValue(new TextFormatter<>(bedCountValidationFormatter));

    priceFromFilter.textProperty().addListener((obs, oldVal, newVal) -> {
        setPriceFromValue(newVal);
    });

    priceToFilter.textProperty().addListener((obs, oldVal, newVal) -> {
    setPriceToValue(newVal);
    });
  }



  /**
   * Method used for resetting the view.
   * This is done by calling the clear() method in the viewModel.
   */
  @Override public void reset()
  {
    viewModel.getAllAvailableRooms();
  }

  /**
   * A void method that looks for available rooms.
   */

  public void lookForAvailableRooms()
  {
    viewModel.getAllAvailableRooms();
  }

  private void filterByRoomTypes(String selection)
  {
    reset();

    if (selection.equals("All"))
    {
      reset();
    }

    else
    {
      viewModel.getRooms().removeIf(
          roomViewModel -> !roomViewModel.roomTypeProperty().get().toString()
              .equals(selection));
    }
  }

  private void filterByBedCount(String selection)
  {
    filterByRoomTypes(roomTypeFilter.getSelectionModel().getSelectedItem());


    if (!selection.isEmpty())
    {
      filterByBeds = true;
      System.out.println("Filtering by beds");

      int nrBedsToFilter = Integer.parseInt(selection);
      viewModel.getRooms().removeIf(
          roomViewModel -> roomViewModel.numberOfBedsProperty().get()
              < nrBedsToFilter);

    }
    else
    {
      reset();
      filterByRoomTypes(roomTypeFilter.getSelectionModel().getSelectedItem());
      filterByBeds = false;
      System.out.println("NOT Filtering by beds");
    }

    if (filterByPriceActive)
    {
      runFilterByPrice();
    }
  }

  private void setPriceFromValue(String selection)
  {
    if (!selection.isEmpty())
    {
      priceFromValue = Integer.parseInt(selection);
      lowValueInput = true;
      System.out.println("From value set: " + priceFromValue);
      if (highValueInput)
      {
        runFilterByPrice();
      }
    }
    else
    {
      filterByPriceActive = false;
      lowValueInput = false;
      filterByRoomTypes(roomTypeFilter.getSelectionModel().getSelectedItem());
    }

    if (filterByBeds)
    {
      filterByBedCount(bedsFilter.textProperty().get());
    }
  }

  private void setPriceToValue(String selection)
  {
    if (!selection.isEmpty())
    {
      int newToValue = Integer.parseInt(selection);
      if (newToValue > priceFromValue)
      {
        priceToValue = newToValue;
        highValueInput = true;
        System.out.println("To value set: " + priceToValue);
        if (lowValueInput)
        {
          runFilterByPrice();
        }
      }

    }
    else
    {
      filterByPriceActive = false;
      highValueInput = false;
      filterByRoomTypes(roomTypeFilter.getSelectionModel().getSelectedItem());
    }

    if (filterByBeds)
    {
      filterByBedCount(bedsFilter.textProperty().get());
    }
  }

  private void runFilterByPrice()
  {
    filterByPriceActive = true;
    viewModel.getRooms().removeIf(
        roomViewModel -> roomViewModel.totalPriceProperty().get() > priceToValue
            || roomViewModel.totalPriceProperty().get() < priceFromValue);

    if (filterByBeds)
    {
      filterByBedCount(bedsFilter.textProperty().get());
    }
  }

  /**
   * A void method  opening the GuestInformation view.
   */

  public void reservationButton() throws IOException
  {
    viewModel.bookARoom(
        viewModel.getSelected().get().roomNumberProperty().get());
    roomTypeFilter.getSelectionModel().select("All");
    reset();
  }

  /**
   * Method called when clicking the back button in the GUI.
   * Opens the LogInView view.
   *
   * @throws IOException
   */
  public void back() throws IOException
  {
    getViewHandler().openView("GuestMenuView.fxml");
  }

}