package viewModel;

import model.Model;

public class ViewModelFactory {

    private GuestInformationViewModel guestInformationViewModel;
    private ReservationViewModel reservationViewModel;
    private RoomListViewModel roomListViewModel;
    private AddEditViewModel addEditViewModel;
    private GuestOverViewForHotelManagerModel GuestOverViewForHotelManagerModel;
    private BookingViewModel bookingViewModel;

    private LogInViewModel logInViewModel;

    public ViewModelFactory(Model model){
        TemporaryInformation tempInfo = new TemporaryInformation();
        ViewState state = new ViewState();

        this.reservationViewModel = new ReservationViewModel(model,tempInfo);
        this.guestInformationViewModel = new GuestInformationViewModel(model,tempInfo);
        this.roomListViewModel = new RoomListViewModel(model, state);
        this.addEditViewModel = new AddEditViewModel(model, state);
        this.logInViewModel = new LogInViewModel(model);
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

    public viewModel.GuestOverViewForHotelManagerModel getGuestOverViewForHotelManagerModel() {
        return GuestOverViewForHotelManagerModel;
    }

    public BookingViewModel getBookingViewModel() {
        return bookingViewModel;
    }
}