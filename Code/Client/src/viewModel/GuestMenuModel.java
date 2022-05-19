package viewModel;

import model.Model;

public class GuestMenuModel {

    private Model model;

    public GuestMenuModel(Model model) {
        this.model = model;
    }

    public void logOff()
    {
        model.logOutForGuest();
    }
}
