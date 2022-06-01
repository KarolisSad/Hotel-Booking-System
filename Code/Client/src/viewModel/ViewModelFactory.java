package viewModel;

import model.Model;
import viewModel.Helpers.TemporaryInformation;
import viewModel.Helpers.ViewState;

/**
 * A class that is used to create all ViewModel objects and
 * gives access to them.
 *
 * @author Group 5
 * @version 04/18/2022
 */
public class ViewModelFactory
{

  private GuestReservationViewModel guestReservationViewModel;
  private HotelManagerRoomListViewModel hotelManagerRoomListViewModel;
  private HotelManagerAddEditRoomViewModel hotelManagerAddEditRoomViewModel;
  private ReceptionistBookingViewModel receptionistBookingViewModel;
  private ReceptionistGuestDetailsViewModel receptionistGuestDetailsViewModel;
  private ReceptionistRoomAndBookingDetailsViewModel receptionistRoomAndBookingDetailsViewModel;
  private HotelManagerBookingViewModel hotelManagerBookingViewModel;
  private HotelManagerGuestOverViewModel hotelManagerGuestOverViewModel;
  private MainMenuViewModel mainMenuViewModel;
  private GuestLoginModel guestLoginModel;
  private GuestRegisterModel guestRegisterModel;
  private GuestMenuModel guestMenuModel;
  private GuestBookingOverViewModel guestBookingOverViewModel;
  private GuestRoomAndBookingDetailsViewModel guestRoomAndBookingDetailsViewModel;
  private GuestPersonalInformationViewModel guestPersonalInformationViewModel;
  private AdminLogInViewModel adminLogInViewModel;
  private GuestConferenceAvailableRoomViewModel guestConferenceAvailableRoomViewModel;

  /**
   * A ViewModelFactory constructor initializing all instance variables.
   * @param model the modelManager implementing the model interface.
   */
  public ViewModelFactory(Model model)
  {

    TemporaryInformation tempInfo = new TemporaryInformation();
    ViewState state = new ViewState();

    this.guestReservationViewModel = new GuestReservationViewModel(model);
    this.hotelManagerRoomListViewModel = new HotelManagerRoomListViewModel(model, state);
    this.hotelManagerAddEditRoomViewModel = new HotelManagerAddEditRoomViewModel(model, state);
    this.receptionistBookingViewModel = new ReceptionistBookingViewModel(
        model);
    this.receptionistGuestDetailsViewModel = new ReceptionistGuestDetailsViewModel(
        model);
    this.receptionistRoomAndBookingDetailsViewModel = new ReceptionistRoomAndBookingDetailsViewModel(
        model);
    this.hotelManagerBookingViewModel = new HotelManagerBookingViewModel(model);

    this.hotelManagerGuestOverViewModel = new HotelManagerGuestOverViewModel(
        model);
    this.mainMenuViewModel = new MainMenuViewModel(model);
    this.guestLoginModel = new GuestLoginModel(model);
    this.guestRegisterModel = new GuestRegisterModel(model);
    this.guestMenuModel = new GuestMenuModel(model);
    this.guestBookingOverViewModel = new GuestBookingOverViewModel(model);
    this.guestRoomAndBookingDetailsViewModel = new GuestRoomAndBookingDetailsViewModel(model);
    this.guestPersonalInformationViewModel = new GuestPersonalInformationViewModel(model);
    this.adminLogInViewModel = new AdminLogInViewModel();
    this.guestConferenceAvailableRoomViewModel = new GuestConferenceAvailableRoomViewModel(model);
  }
  
/**
   * A getter for AdminLogInViewModel.
   * @return AdminLogInViewModel object
   */
  public AdminLogInViewModel getAdminLogInViewModel() {
    return adminLogInViewModel;
  }


  /**
   * A getter for guestReservationViewModel.
   * @return GuestReservationViewModel object.
   */
  public GuestReservationViewModel getReservationViewModel()
  {
    return guestReservationViewModel;
  }

  /**
   * A getter for hotelManagerRoomListViewModel.
   * @return HotelManagerRoomListViewModel object.
   */
  public HotelManagerRoomListViewModel getRoomListViewModel()
  {
    return hotelManagerRoomListViewModel;
  }

  /**
   * A getter for hotelManagerAddEditRoomViewModel.
   * @return HotelManagerAddEditRoomViewModel object.
   */
  public HotelManagerAddEditRoomViewModel getAddEditViewModel()
  {
    return hotelManagerAddEditRoomViewModel;
  }

  /**
   * A getter for receptionistBookingViewModel.
   * @return ReceptionistBookingViewModel object.
   */
  public ReceptionistBookingViewModel getBookingsForReceptionistViewModel()
  {
    return receptionistBookingViewModel;
  }

  /**
   * A getter for receptionistGuestDetailsViewModel.
   * @return ReceptionistGuestDetailsViewModel object.
   */
  public ReceptionistGuestDetailsViewModel getGuestDetailsForReceptionistViewModel()
  {
    return receptionistGuestDetailsViewModel;
  }

  /**
   * A getter for receptionistRoomAndBookingDetailsViewModel.
   * @return ReceptionistRoomAndBookingDetailsViewModel object.
   */
  public ReceptionistRoomAndBookingDetailsViewModel getRoomDetailsForReceptionistModel()
  {
    return receptionistRoomAndBookingDetailsViewModel;
  }

  /**
   * A getter for hotelManagerBookingViewModel.
   * @return HotelManagerBookingViewModel object.
   */
  public HotelManagerBookingViewModel getBookingViewModel()
  {
    return hotelManagerBookingViewModel;
  }

  /**
   * A getter for hotelManagerGuestOverViewModel.
   * @return HotelManagerGuestOverViewModel object.
   */
  public HotelManagerGuestOverViewModel getGuestOverViewForHotelManagerModel()
  {
    return hotelManagerGuestOverViewModel;
  }

  /**
   * A getter for guestRegisterModel.
   * @return GuestRegisterModel object.
   */
  public GuestRegisterModel getUserRegisterModel() {
    return guestRegisterModel;
  }

  /**
   * A getter for mainMenuViewModel.
   * @return MainMenuViewModel object.
   */
  public MainMenuViewModel getUserLoginMainModel() {
    return mainMenuViewModel;
  }

  /**
   * A getter for guestLoginModel.
   * @return GuestLoginModel object.
   */
  public GuestLoginModel getUserLoginModel() {
    return guestLoginModel;
  }

  /**
   * A getter for guestMenuModel.
   * @return GuestMenuModel object.
   */
  public GuestMenuModel getGuestMenuModel() {
    return guestMenuModel;
  }

  /**
   * A getter for guestBookingOverViewModel.
   * @return GuestBookingOverViewModel object.
   */
  public GuestBookingOverViewModel getBookingOverviewForGuestModel() {
    return guestBookingOverViewModel;
  }

  /**
   * A getter for guestRoomAndBookingDetailsViewModel.
   * @return GuestRoomAndBookingDetailsViewModel object.
   */
  public GuestRoomAndBookingDetailsViewModel getRoomOverviewForGuestModel() {
    return guestRoomAndBookingDetailsViewModel;
  }

  /**
   * A getter for guestPersonalInformationViewModel.
   * @return GuestPersonalInformationViewModel object.
   */
  public GuestPersonalInformationViewModel getGuestPersonalInformationViewModel() {
    return guestPersonalInformationViewModel;
  }

  public GuestConferenceAvailableRoomViewModel getConferenceAvailableRoomViewModel() {
    return guestConferenceAvailableRoomViewModel;
  }
}