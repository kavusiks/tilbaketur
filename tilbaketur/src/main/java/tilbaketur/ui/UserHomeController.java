package tilbaketur.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tilbaketur.core.Driver;

import java.io.IOException;

public class UserHomeController extends AbstractController{
  @FXML
  Button availableCarsBtn;

  @FXML Button logOutBtn;

  @FXML Label currentRequestLabel;
  @FXML Label welcomeLabel;

  public UserHomeController() throws IOException {
    super();
  }



  @Override public void initialize() {
    welcomeLabel.setText("Welcome "+ loggedInAs.getName());
    /*if(loggedInAs instanceof Driver) {
      currentRequestLabel.setText(((Driver) loggedInAs).getRequestedCar().toString());
    }
    else currentRequestLabel.setText("");*/
  }

  @FXML
  public void goToAvailableCars() throws IOException {
    switchScene("AvailableCars.fxml", availableCarsBtn);
  }

  @FXML
  public void goToLogin() throws IOException {
    switchScene("Login.fxml", logOutBtn);
  }
}
