package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

/**
 * A class providing functionality for GuestOverViewForHotelManagerController
 *
 * @author Group 5
 * @version 12/05/2022
 */
public class GuestOverViewForHotelManagerModel {

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
    }

    // for loop to add all guests :)

    public ObservableList<String> getGuests() {
        return guests;
    }
}
