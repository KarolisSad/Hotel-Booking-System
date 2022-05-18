package viewModel;

import model.Model;

/**
 * A class that is used to create all ViewModel objects and
 * gives access to them.
 *
 * @author Group 5
 * @version 04/18/2022
 */
public class ViewModelFactory
{

  private GuestInformationViewModel guestInformationViewModel;
  private ReservationViewModel reservationViewModel;
  private RoomListViewModel roomListViewModel;
  private AddEditViewModel addEditViewModel;
  private LogInViewModel logInViewModel;
  private BookingsForReceptionistViewModel bookingsForReceptionistViewModel;
  private GuestDetailsForReceptionistViewModel guestDetailsForReceptionistViewModel;
  private RoomDetailsForReceptionistModel roomDetailsForReceptionistModel;
  private BookingViewModel bookingViewModel;
  private GuestOverViewForHotelManagerModel guestOverViewForHotelManagerModel;
  private ShowBookingViewModel showBookingViewModel;

  /**
   * A ViewModelFactory constructor initializing all instance variables.
   */
  public ViewModelFactory(Model model)
  {

    TemporaryInformation tempInfo = new TemporaryInformation();
    ViewState state = new ViewState();

    this.reservationViewModel = new ReservationViewModel(model, tempInfo);
    this.guestInformationViewModel = new GuestInformationViewModel(model,
        tempInfo);
    this.roomListViewModel = new RoomListViewModel(model, state);
    this.addEditViewModel = new AddEditViewModel(model, state);
    this.logInViewModel = new LogInViewModel(model);

    this.bookingsForReceptionistViewModel = new BookingsForReceptionistViewModel(
        model);
    this.guestDetailsForReceptionistViewModel = new GuestDetailsForReceptionistViewModel(
        model);
    this.roomDetailsForReceptionistModel = new RoomDetailsForReceptionistModel(
        model, tempInfo);
    this.bookingViewModel = new BookingViewModel(model);

    this.guestOverViewForHotelManagerModel = new GuestOverViewForHotelManagerModel(
        model);

    this.showBookingViewModel = new ShowBookingViewModel(model);

  }

  /**
   * A getter for guestInformationViewModel.
   * @return GuestInformationViewModel object.
   */
  public GuestInformationViewModel getGuestInformationViewModel()
  {
    return guestInformationViewModel;
  }

  /**
   * A getter for reservationViewModel.
   * @return ReservationViewModel object.
   */
  public ReservationViewModel getReservationViewModel()
  {
    return reservationViewModel;
  }

  /**
   * A getter for roomListViewModel.
   * @return RoomListViewModel object.
   */
  public RoomListViewModel getRoomListViewModel()
  {
    return roomListViewModel;
  }

  /**
   * A getter for addEditViewModel.
   * @return AddEditViewModel object.
   */
  public AddEditViewModel getAddEditViewModel()
  {
    return addEditViewModel;
  }

  /**
   * A getter for logInViewModel.
   * @return LogInViewModel object.
   */
  public LogInViewModel getLogInViewModel()
  {
    return logInViewModel;
  }

  /**
   * A getter for bookingsForReceptionistViewModel.
   * @return BookingsForReceptionistViewModel object.
   */
  public BookingsForReceptionistViewModel getBookingsForReceptionistViewModel()
  {
    return bookingsForReceptionistViewModel;
  }

  /**
   * A getter for guestDetailsForReceptionistViewModel.
   * @return GuestDetailsForReceptionistViewModel object.
   */
  public GuestDetailsForReceptionistViewModel getGuestDetailsForReceptionistViewModel()
  {
    return guestDetailsForReceptionistViewModel;
  }

  /**
   * A getter for roomDetailsForReceptionistModel.
   * @return RoomDetailsForReceptionistModel object.
   */
  public RoomDetailsForReceptionistModel getRoomDetailsForReceptionistModel()
  {
    return roomDetailsForReceptionistModel;
  }

  /**
   * A getter for bookingViewModel.
   * @return BookingViewModel object.
   */
  public BookingViewModel getBookingViewModel()
  {
    return bookingViewModel;
  }

  /**
   * A getter for guestOverViewForHotelManagerModel.
   * @return GuestOverViewForHotelManagerModel object.
   */
  public GuestOverViewForHotelManagerModel getGuestOverViewForHotelManagerModel()
  {
    return guestOverViewForHotelManagerModel;
  }

  /**
   * A getter for showBookingViewModel.
   * @return ShowBookingViewModel object.
   */
  public ShowBookingViewModel getShowBookingViewModel()
  {
    return showBookingViewModel;
  }
}