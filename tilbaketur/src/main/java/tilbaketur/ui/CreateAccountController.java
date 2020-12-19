package tilbaketur.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import tilbaketur.core.AbstractUser;
import tilbaketur.core.Driver;
import tilbaketur.core.Provider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;


public class CreateAccountController extends AbstractController {

  @FXML TextField nameField;
  @FXML TextField passwordField;
  @FXML TextField usernameField;
  @FXML TextField confirmPasswordField;
  @FXML RadioButton radioBtnDriver;
  @FXML RadioButton radioBtnProvider;
  @FXML Button createAccountBtn;
  @FXML Button goBackBtn;
  @FXML Label errorMessageLabel;

  ToggleGroup tg;

  public CreateAccountController() throws IOException {
    super();
  }


  @Override public void initialize() {
    tg = new ToggleGroup();
    radioBtnDriver.setToggleGroup(tg);
    radioBtnProvider.setToggleGroup(tg);


  }


  @FXML public void goToLogIn() throws IOException {
    switchScene("Login.fxml", goBackBtn);

  }

  public void addUser(AbstractUser user) throws IOException {
    allUsers.addItem(user);
    exportJson();
  }

  private Boolean allFieldsFilled() {
    Boolean allFilled = radioBtnDriver.isSelected() || radioBtnProvider.isSelected();
    if (passwordField.getText().isEmpty()) allFilled=false;
    if (usernameField.getText().isEmpty()) allFilled = false;
    if (nameField.getText().isEmpty()) allFilled = false;
    return allFilled;
  }

  @FXML public void createAccount() throws NullPointerException, IOException {
    if (!allFieldsFilled()) {
      errorMessageLabel.setText("All fields must be filled and either 'Driver' or 'Provider' must be seleceted");
      return;
    }

    String name = nameField.getText();
    String username = usernameField.getText();
    String password = passwordField.getText();

    if(allUsers.getItemsList().stream().anyMatch(user -> user.getUsername().matches(username))){
      errorMessageLabel.setText("Username already exists");
      return;
    }
    if(!confirmPasswordField.getText().matches(password)) {
      errorMessageLabel.setText("Passwords doesn't match");
      return;
    }
    if(radioBtnDriver.isSelected()){
      Driver driver = new Driver(username,password,name);
      addUser(driver);
    }
    if(radioBtnProvider.isSelected()){
      Provider provider = new Provider(username,password,name);
      addUser(provider);
    }
    System.out.println(allUsers.toString());
    switchScene("Login.fxml",createAccountBtn);

  }
}
