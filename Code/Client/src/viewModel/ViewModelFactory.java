package viewModel;

import model.Model;

public class ViewModelFactory {

    private GuestInformationViewModel guestInformationViewModel;
    private ReservationViewModel reservationViewModel;
    private RoomListViewModel roomListViewModel;
    private AddEditViewModel addEditViewModel;
    private LogInViewModel logInViewModel;
    private BookingsForReceptionistViewModel bookingsForReceptionistViewModel;
    private GuestDetailsForReceptionistViewModel guestDetailsForReceptionistViewModel;
    private GuestOverViewForHotelManagerModel GuestOverViewForHotelManagerModel;
    private BookingViewModel bookingViewModel;

    public ViewModelFactory(Model model){

        TemporaryInformation tempInfo = new TemporaryInformation();
        ViewState state = new ViewState();

        this.reservationViewModel = new ReservationViewModel(model,tempInfo);
        this.guestInformationViewModel = new GuestInformationViewModel(model,tempInfo);
        this.roomListViewModel = new RoomListViewModel(model, state);
        this.addEditViewModel = new AddEditViewModel(model, state);
        this.logInViewModel = new LogInViewModel(model);

        this.bookingsForReceptionistViewModel = new BookingsForReceptionistViewModel(model);
        this.guestDetailsForReceptionistViewModel = new GuestDetailsForReceptionistViewModel(model);

        this.GuestOverViewForHotelManagerModel = new GuestOverViewForHotelManagerModel(model);
        this.bookingViewModel = new BookingViewModel(model);

    }

    public GuestInformationViewModel getGuestInformationViewModel(){
        return guestInformationViewModel;
    }

    public ReservationViewModel getReservationViewModel() {
        return reservationViewModel;
    }

    public RoomListViewModel getRoomListViewModel() {
        return roomListViewModel;
    }

    public AddEditViewModel getAddEditViewModel() {
        return addEditViewModel;
    }

    public LogInViewModel getLogInViewModel()
    {
        return logInViewModel;
    }

    public BookingsForReceptionistViewModel getBookingsForReceptionistViewModel()
    {
        return bookingsForReceptionistViewModel;
    }

    public GuestDetailsForReceptionistViewModel getGuestDetailsForReceptionistViewModel() {
        return guestDetailsForReceptionistViewModel;
    }

    public viewModel.GuestOverViewForHotelManagerModel getGuestOverViewForHotelManagerModel() {
        return GuestOverViewForHotelManagerModel;
    }

    public BookingViewModel getBookingViewModel() {
        return bookingViewModel;
    }
}