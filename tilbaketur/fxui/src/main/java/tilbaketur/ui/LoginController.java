package tilbaketur.ui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tilbaketur.core.AbstractUser;

public class LoginController extends AbstractController {

  @FXML
  Button createAccountBtn;

  @FXML
  Button loginBtn;

  @FXML
  TextField usernameField;

  @FXML
  TextField passwordField;

  @FXML
  Label errorMessage;

  /**
   * Makes sure that no one are logged in when the app stars.
   *
   * @throws IOException I/O exceptions while exporting.
   */
  public LoginController() throws IOException {
    super();
    workSpace.setLoggedInAs(null);
    exportJson();
  }

  @Override
  public void initialize() {

  }

  /**
   * Method for loging into the app. Checks if the account exist before logging
   * in.
   *
   * @throws IOException if I/O errors occurs while switching scene.
   */
  @FXML
  public void login() throws IOException {
    String username = usernameField.getText();
    String password = passwordField.getText();
    AbstractUser currentUser = workSpace.getUserList().getItemsList().stream()
        .filter(user -> user.getUsername().equals(username))
        .findFirst().orElse(null);
    if (currentUser != null) {
      if (currentUser.getPassword().equals(password)) {
        workSpace.setLoggedInAs(currentUser);
        exportJson();
        switchScene("UserHome.fxml", loginBtn);
      }
    } else {
      errorMessage.setText("feil");
    }
  }

  @FXML
  public void goToCreateAccount() throws IOException {
    usernameField.setText("Virket");
    switchScene("CreateAccount.fxml", createAccountBtn);
  }

}
