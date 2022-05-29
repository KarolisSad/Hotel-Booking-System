package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.util.HashMap;

/**
 * A class creating AdminLogInViewModel object that contains a log-in details for receptionist and hotel manager.
 */
public class AdminLogInViewModel
{
  private HashMap<String, String> logInfo = new HashMap<String, String>();
  private StringProperty username;
  private StringProperty password;

  /**
   * Zero-argument constructor that puts value in the hashmap list.
   */
  public AdminLogInViewModel ()
  {
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    logInfo.put("receptionist1", "pass1");
    logInfo.put("receptionist2", "pass2");
    logInfo.put("receptionist3", "pass3");
    logInfo.put("hotel", "manager");
  }

  /**
   * A getter that calls the hashmap called logInfo.
   * @return logInfo
   */
  public HashMap<String, String> getLogInfo()
  {
    return logInfo;
  }

  public StringProperty getUsername()
  {
    return username;
  }

  public StringProperty getPassword()
  {
    return password;
  }

}
