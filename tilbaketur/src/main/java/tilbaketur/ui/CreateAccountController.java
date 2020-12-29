package tilbaketur.ui;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import tilbaketur.core.AbstractUser;
import tilbaketur.core.Driver;
import tilbaketur.core.Provider;


public class CreateAccountController extends AbstractController {

  @FXML
  TextField nameField;
  @FXML
  TextField passwordField;
  @FXML
  TextField usernameField;
  @FXML
  TextField confirmPasswordField;
  @FXML
  RadioButton radioBtnDriver;
  @FXML
  RadioButton radioBtnProvider;
  @FXML
  Button createAccountBtn;
  @FXML
  Button goBackBtn;
  @FXML
  Label errorMessageLabel;

  ToggleGroup tg;

  public CreateAccountController() throws IOException {
    super();
  }

  @Override
  public void initialize() {
    tg = new ToggleGroup();
    radioBtnDriver.setToggleGroup(tg);
    radioBtnProvider.setToggleGroup(tg);

  }

  @FXML
  public void goToLogIn() throws IOException {
    switchScene("Login.fxml", goBackBtn);

  }

  public void addUser(AbstractUser user) throws IOException {
    allUsers.addItem(user);
    exportJson();
  }

  /**
   * User to check if all of the required details are filled in.
   *
   * @return allFilled true if all fields are filled.
   */
  private Boolean allFieldsFilled() {
    Boolean allFilled = radioBtnDriver.isSelected() || radioBtnProvider.isSelected();
    if (passwordField.getText().isEmpty()) {
      allFilled = false;
    }
    if (usernameField.getText().isEmpty()) {
      allFilled = false;
    }
    if (nameField.getText().isEmpty()) {
      allFilled = false;
    }
    return allFilled;
  }

  /**
   * Used to create a new User if the criteria are met.
   *
   * @throws IOException if error occurs when adding an user or switiching scene.
   */
  @FXML
  public void createAccount() throws IOException {
    if (!allFieldsFilled()) {
      errorMessageLabel
      .setText("All fields must be filled and either 'Driver' or 'Provider' must be seleceted");
      return;
    }

    String name = nameField.getText();
    String username = usernameField.getText();
    String password = passwordField.getText();

    if (allUsers.getItemsList().stream().anyMatch(user -> user.getUsername().matches(username))) {
      errorMessageLabel.setText("Username already exists");
      return;
    }
    if (!confirmPasswordField.getText().matches(password)) {
      errorMessageLabel.setText("Passwords doesn't match");
      return;
    }
    if (radioBtnDriver.isSelected()) {
      Driver driver = new Driver(username, password, name);
      addUser(driver);
    }
    if (radioBtnProvider.isSelected()) {
      Provider provider = new Provider(username, password, name);
      addUser(provider);
    }
    System.out.println(allUsers.toString());
    switchScene("Login.fxml", createAccountBtn);

  }
}
