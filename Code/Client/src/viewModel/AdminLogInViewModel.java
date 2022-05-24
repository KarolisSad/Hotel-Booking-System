package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.util.HashMap;

public class AdminLogInViewModel
{
  private HashMap<String, String> logInfo = new HashMap<String, String>();

  public AdminLogInViewModel ()
  {
    logInfo.put("receptionist1", "pass1");
    logInfo.put("receptionist2", "pass2");
    logInfo.put("receptionist3", "pass3");
    logInfo.put("hotel", "manager");
  }

  public HashMap<String, String> getLogInfo()
  {
    return logInfo;
  }

}
