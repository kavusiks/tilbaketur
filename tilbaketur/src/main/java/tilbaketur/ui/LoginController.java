package tilbaketur.ui;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tilbaketur.core.AbstractUser;


import java.io.IOException;

public class LoginController extends AbstractController {

  @FXML
  Button createAccountBtn;

  @FXML
  Button loginBtn;

  @FXML
  TextField usernameField;

  @FXML
  TextField passwordField;

  @FXML Label errorMessage;

  public LoginController() throws IOException {
    super();
  }


  @Override public void initialize() {

  }

  @FXML
  public void login() throws IOException {
    String username = usernameField.getText();
    String password = passwordField.getText();
    AbstractUser currentUser = allUsers.getItemsList().stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    if(currentUser!=null) {
      if (currentUser.getPassword().equals(password)) {
        loggedInAs = currentUser;
        switchScene("UserHome.fxml", loginBtn);
      }
    }
    else errorMessage.setText("feil");
  }

  @FXML
  public void goToCreateAccount() throws IOException {
    usernameField.setText("Virket");
    switchScene("CreateAccount.fxml", createAccountBtn);
  }
    
}
