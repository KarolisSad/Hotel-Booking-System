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
  private UserLoginMainModel userLoginMainModel;
  private UserLoginModel userLoginModel;
  private UserRegisterModel userRegisterModel;
  private GuestMenuModel guestMenuModel;
  private BookingOverviewForGuestModel bookingOverviewForGuestModel;
  private RoomOverviewForGuestModel roomOverviewForGuestModel;
  private GuestPersonalInformationViewModel guestPersonalInformationViewModel;
  private AdminLogInViewModel adminLogInViewModel;

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
    this.userLoginMainModel = new UserLoginMainModel(model);
    this.userLoginModel = new UserLoginModel(model);
    this.userRegisterModel = new UserRegisterModel(model);
    this.guestMenuModel = new GuestMenuModel(model);
    this.bookingOverviewForGuestModel = new BookingOverviewForGuestModel(model);
    this.roomOverviewForGuestModel = new RoomOverviewForGuestModel(model);

    this.guestPersonalInformationViewModel = new GuestPersonalInformationViewModel(model);
    this.adminLogInViewModel = new AdminLogInViewModel();
  }

  /**
   * A getter for AdminLogInViewModel.
   * @return AdminLogInViewModel object
   */
  public AdminLogInViewModel getAdminLogInViewModel() {
    return adminLogInViewModel;
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

  /**
   * A getter for userRegisterModel.
   * @return UserRegisterModel object.
   */
  public UserRegisterModel getUserRegisterModel() {
    return userRegisterModel;
  }

  /**
   * A getter for userLoginMainModel.
   * @return UserLoginMainModel object.
   */
  public UserLoginMainModel getUserLoginMainModel() {
    return userLoginMainModel;
  }

  /**
   * A getter for userLoginModel.
   * @return UserLoginModel object.
   */
  public UserLoginModel getUserLoginModel() {
    return userLoginModel;
  }

  /**
   * A getter for guestMenuModel.
   * @return GuestMenuModel object.
   */
  public GuestMenuModel getGuestMenuModel() {
    return guestMenuModel;
  }

  /**
   * A getter for bookingOverviewForGuestModel.
   * @return BookingOverviewForGuestModel object.
   */
  public BookingOverviewForGuestModel getBookingOverviewForGuestModel() {
    return bookingOverviewForGuestModel;
  }

  /**
   * A getter for roomOverviewForGuestModel.
   * @return RoomOverviewForGuestModel object.
   */
  public RoomOverviewForGuestModel getRoomOverviewForGuestModel() {
    return roomOverviewForGuestModel;
  }

  /**
   * A getter for guestPersonalInformationViewModel.
   * @return GuestPersonalInformationViewModel object.
   */
  public GuestPersonalInformationViewModel getGuestPersonalInformationViewModel() {
    return guestPersonalInformationViewModel;
  }
}