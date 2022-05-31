package view.GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.ViewController;
import viewModel.GuestReservationViewModel;
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
public class GuestReservationController extends ViewController
{
  @FXML private Button bookButton;
  @FXML private Button filterPriceButton;
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
  private GuestReservationViewModel viewModel;
  private boolean filterByPriceActive;
  private boolean filterByBedsActive;

  /**
   * A none argument, void method initializing instance variables.
   */
  @Override protected void init()
  {

    viewModel = getViewModelFactory().getReservationViewModel();
    DecimalFormat currencyFormat = new DecimalFormat("0.00 DKK");
    filterByBedsActive = false;
    filterByPriceActive = false;

    startDate.getEditor().setDisable(true);
    startDate.getEditor().setOpacity(1);
    endDate.getEditor().setDisable(true);
    endDate.getEditor().setOpacity(1);


    // Binding
    startDate.valueProperty().bindBidirectional(viewModel.getStartDatePicker());
    endDate.valueProperty().bindBidirectional(viewModel.getEndDatePicker());
    errorLabel.textProperty().bindBidirectional(viewModel.getErrorLabel());

    // Table
    roomNumberColumn.setCellValueFactory(
        cellData -> cellData.getValue().roomNumberProperty());
    roomTypeColumn.setCellValueFactory(
        cellData -> cellData.getValue().roomTypeProperty().asString());
    numberOfBedsColumn.setCellValueFactory(
        cellData -> cellData.getValue().numberOfBedsProperty().asObject());

    // Formatter that makes sure that nothing other than number can be typed in the textfield.
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
          bookButton.setDisable(false);
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
          runFilters("roomType");
        });

    //Number of beds filter
    bedsFilter.textFormatterProperty()
        .setValue(new TextFormatter<>(numberValidationFormatter));
    bedsFilter.textProperty().addListener((obs, oldVal, newVal) -> {
      filterByBedCount(newVal);
    });

    // Price Filter
    priceFromFilter.textFormatterProperty()
        .setValue(new TextFormatter<>(numberValidationFormatter));
    priceToFilter.textFormatterProperty()
        .setValue(new TextFormatter<>(numberValidationFormatter));

    priceFromFilter.textProperty().addListener((obs, oldVal, newVal) -> {
      filterPriceButton.setDisable(false);
    });

    priceToFilter.textProperty().addListener((obs, oldVal, newVal) -> {
      filterPriceButton.setDisable(false);
    });

    reset();
  }

  /**
   * Method used for resetting the view.
   * This is done by calling the clear() method in the viewModel, and by resetting all filtering.
   */
  @Override public void reset()
  {
    viewModel.clear();

    roomTypeFilter.getSelectionModel().select("All");
    bedsFilter.textProperty().set("");
    priceFromFilter.textProperty().set("");
    priceToFilter.textProperty().set("");

    filterByPriceActive = false;
    filterByBedsActive = false;
    viewModel.getAllAvailableRooms();
    if (priceFromFilter.textProperty().get().isEmpty()
        && priceFromFilter.textProperty().get().isEmpty())
    {
      filterPriceButton.setDisable(true);
    }

    bookButton.setDisable(true);

  }

  /**
   * A void method that looks for available rooms.
   */

  public void lookForAvailableRooms()
  {
    reset();
    viewModel.getAllAvailableRooms();
  }

  /**
   * Method used for filtering by roomTypes.
   * This is done by removing all bookings from the list that does not match the roomtype selected.
   * @param selection The chosen selection of rooms.
   */
  private void filterByRoomTypes(String selection)
  {

    if (selection.equals("All"))
    {
      viewModel.getAllAvailableRooms();
    }

    else
    {
      viewModel.getAllAvailableRooms();
      viewModel.getRooms().removeIf(
          roomViewModel -> !roomViewModel.roomTypeProperty().get().toString()
              .equals(selection));

    }
  }

  /**
   * Method used for filtering by bed count.
   * This is done by removing all rooms from the list if the bedcount is less than the selection.
   * If nothing is given as selection, it cancels the filtering.
   * @param selection
   */
  private void filterByBedCount(String selection)
  {
    if (!selection.isEmpty())
    {
      filterByBedsActive = true;
      int nrBedsToFilter = Integer.parseInt(selection);
      viewModel.getRooms().removeIf(
          roomViewModel -> roomViewModel.numberOfBedsProperty().get()
              < nrBedsToFilter);
    }

    else
    {
      filterByBedsActive = false;
      runFilters("bed");
    }

  }

  /**
   * Method used to filter by the total price of the booking.
   * This is done by checking which of the filtering fields have contents in them to be used as filters,
   * and then removes all rooms from the list that does not fulfill the criteria specified by the filtering.
   */
  private void runFilterByPrice()
  {
    runFilters("price");

    if (!priceFromFilter.textProperty().get().isEmpty()
        && priceToFilter.textProperty().get().isEmpty())
    {
      filterByPriceActive = true;
      int lowPriceToFilter = Integer.parseInt(
          priceFromFilter.textProperty().get());
      viewModel.getRooms()
          .removeIf(room -> lowPriceToFilter > room.totalPriceProperty().get());
    }

    else if (priceFromFilter.textProperty().get().isEmpty()
        && !priceToFilter.textProperty().get().isEmpty())
    {
      filterByPriceActive = true;
      int highPriceToFilter = Integer.parseInt(
          priceToFilter.textProperty().get());
      viewModel.getRooms().removeIf(
          roomViewModel -> roomViewModel.totalPriceProperty().get()
              > highPriceToFilter);

    }

    else if (!priceFromFilter.textProperty().get().isEmpty()
        && !priceToFilter.textProperty().get().isEmpty())
    {
      filterByPriceActive = true;
      int lowPriceToFilter = Integer.parseInt(
          priceFromFilter.textProperty().get());
      int highPriceToFilter = Integer.parseInt(
          priceToFilter.textProperty().get());

      viewModel.getRooms().removeIf(
          roomViewModel -> roomViewModel.totalPriceProperty().get()
              > highPriceToFilter);
      viewModel.getRooms().removeIf(
          roomViewModel -> roomViewModel.totalPriceProperty().get()
              < lowPriceToFilter);
    }

    else
    {
      filterByPriceActive = false;
    }
  }

  /**
   * Method called each time a filter is to be run.
   * Checks which other filters - if any - are currently active, and makes sure that they stay active.
   * @param callingMethod
   */
  private void runFilters(String callingMethod)
  {
    switch (callingMethod)
    {
      case "bed":
      {
        filterByRoomTypes(roomTypeFilter.getSelectionModel().getSelectedItem());

        if (filterByPriceActive)
        {
          runFilterByPrice();
        }
        break;
      }

      case "price":
      {
        filterByRoomTypes(roomTypeFilter.getSelectionModel().getSelectedItem());

        if (filterByBedsActive)
        {
          filterByBedCount(bedsFilter.textProperty().get());
        }
        break;
      }

      case "roomType":
      {
        if (filterByBedsActive)
        {
          filterByBedCount(bedsFilter.textProperty().get());
        }

       else if (filterByPriceActive)
        {
          runFilterByPrice();
        }
        break;
      }
    }
  }

  /**
   * Method calling the runFilterByPrice method, and then runs the filter by bed count method.
   */
  @FXML private void filterByPrice()
  {
    runFilterByPrice();
    filterByBedCount(bedsFilter.textProperty().get());
  }

  /**
   * A void method calling the bookARoom method in the viewmodel.
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
    reset();
    getViewHandler().openView("GuestMenuView.fxml");
  }
}