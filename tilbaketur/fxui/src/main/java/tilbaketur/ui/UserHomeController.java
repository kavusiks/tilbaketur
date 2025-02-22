package tilbaketur.ui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserHomeController extends AbstractController {
  @FXML
  Button availableCarsBtn;

  @FXML
  Button logOutBtn;

  @FXML
  Label currentRequestLabel;
  @FXML
  Label welcomeLabel;

  public UserHomeController() throws IOException {
    super();
  }

  @Override
  public void initialize() {
    welcomeLabel.setText("Welcome " + loggedInAs.getName());
    /*
     * if(loggedInAs instanceof Driver) { currentRequestLabel.setText(((Driver)
     * loggedInAs).getRequestedCar().toString()); } else
     * currentRequestLabel.setText("");
     */
  }

  @FXML
  public void goToAvailableCars() throws IOException {
    switchScene("AvailableCars.fxml", availableCarsBtn);
  }

  /**
   * Sets loggedInAs null, before logging in.
   *
   * @throws IOException I/O exception when switching scene.
   */
  @FXML
  public void goToLogin() throws IOException {
    loggedInAs = null;
    exportJson();
    switchScene("Login.fxml", logOutBtn);
  }
}
