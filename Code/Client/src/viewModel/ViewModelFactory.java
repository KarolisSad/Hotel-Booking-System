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
  private GuestDetailsForReceptionistViewModel guestDetailsForReceptionistViewModel;
  private ReceptionistRoomAndBookingDetailsViewModel receptionistRoomAndBookingDetailsViewModel;
  private HotelManagerBookingViewModel hotelManagerBookingViewModel;
  private GuestOverViewForHotelManagerModel guestOverViewForHotelManagerModel;
  private MainMenuViewModel mainMenuViewModel;
  private GuestLoginModel guestLoginModel;
  private GuestRegisterModel guestRegisterModel;
  private GuestMenuModel guestMenuModel;
  private GuestBookingOverViewModel guestBookingOverViewModel;
  private GuestRoomAndBookingDetailsViewModel guestRoomAndBookingDetailsViewModel;
  private GuestPersonalInformationViewModel guestPersonalInformationViewModel;
  private AdminLogInViewModel adminLogInViewModel;
  private ConferenceAvailableRoomViewModel conferenceAvailableRoomViewModel;

  /**
   * A ViewModelFactory constructor initializing all instance variables.
   */
  public ViewModelFactory(Model model)
  {

    TemporaryInformation tempInfo = new TemporaryInformation();
    ViewState state = new ViewState();

    this.guestReservationViewModel = new GuestReservationViewModel(model, tempInfo);
    this.hotelManagerRoomListViewModel = new HotelManagerRoomListViewModel(model, state);
    this.hotelManagerAddEditRoomViewModel = new HotelManagerAddEditRoomViewModel(model, state);
    this.receptionistBookingViewModel = new ReceptionistBookingViewModel(
        model);
    this.guestDetailsForReceptionistViewModel = new GuestDetailsForReceptionistViewModel(
        model);
    this.receptionistRoomAndBookingDetailsViewModel = new ReceptionistRoomAndBookingDetailsViewModel(
        model, tempInfo);
    this.hotelManagerBookingViewModel = new HotelManagerBookingViewModel(model);

    this.guestOverViewForHotelManagerModel = new GuestOverViewForHotelManagerModel(
        model);
    this.mainMenuViewModel = new MainMenuViewModel(model);
    this.guestLoginModel = new GuestLoginModel(model);
    this.guestRegisterModel = new GuestRegisterModel(model);
    this.guestMenuModel = new GuestMenuModel(model);
    this.guestBookingOverViewModel = new GuestBookingOverViewModel(model);
    this.guestRoomAndBookingDetailsViewModel = new GuestRoomAndBookingDetailsViewModel(model);
    this.guestPersonalInformationViewModel = new GuestPersonalInformationViewModel(model);
    this.adminLogInViewModel = new AdminLogInViewModel();
    this.conferenceAvailableRoomViewModel = new ConferenceAvailableRoomViewModel(model);
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
   * A getter for guestDetailsForReceptionistViewModel.
   * @return GuestDetailsForReceptionistViewModel object.
   */
  public GuestDetailsForReceptionistViewModel getGuestDetailsForReceptionistViewModel()
  {
    return guestDetailsForReceptionistViewModel;
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
   * A getter for guestOverViewForHotelManagerModel.
   * @return GuestOverViewForHotelManagerModel object.
   */
  public GuestOverViewForHotelManagerModel getGuestOverViewForHotelManagerModel()
  {
    return guestOverViewForHotelManagerModel;
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

  public ConferenceAvailableRoomViewModel getConferenceAvailableRoomViewModel() {
    return conferenceAvailableRoomViewModel;
  }
}