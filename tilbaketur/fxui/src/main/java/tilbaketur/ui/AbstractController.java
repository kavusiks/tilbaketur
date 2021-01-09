package tilbaketur.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tilbaketur.core.AbstractUser;
import tilbaketur.core.Car;
import tilbaketur.core.CarList;
import tilbaketur.core.Driver;
import tilbaketur.core.Provider;
import tilbaketur.core.UserList;
import tilbaketur.json.CarListSerializer;
import tilbaketur.json.TilbaketurModule;
import tilbaketur.json.UserListSerializer;

public abstract class AbstractController {

  protected UserList allUsers = new UserList();
  protected CarList allCars = new CarList();
  protected ObjectMapper mapper;
  protected AbstractUser loggedInAs;
  protected URL userListUrl;
  protected URL carListUrl;
  protected Reader readerCarList;
  protected Reader readerUserList;
  protected Reader readerLoggedInAs;
  protected String userListFileLocation;
  protected String carListFileLocation;
  protected String loggedInAsFileLocation;

  /**
   * Used to first create a folder on users computer.
   * Then loads the default values to the app.
   * Then saves the default values and
   * any added values to the created folder.
   *
   * @throws IOException when the mapper can't read the each readers.
   *
   */
  public AbstractController() throws IOException {
    userListFileLocation = System.getProperty("user.home") + "/.tilbaketur/" + "userList.json";
    carListFileLocation = System.getProperty("user.home") + "/.tilbaketur/" + "carList.json";
    loggedInAsFileLocation = System.getProperty("user.home") + "/.tilbaketur/" + "loggedInAs.json";
    userListUrl = TilbaketurApp.class.getResource("defaultUserList.json");
    carListUrl = TilbaketurApp.class.getResource("defaultCarList.json");
    //userListUrl = UserListSerializer.class.getResource("defaultUserList.json");
    //carListUrl = CarListSerializer.class.getResource("defaultCarList.json");
    mapper = new ObjectMapper();
    mapper.registerModule(new TilbaketurModule());
    importJson();
    allUsers = mapper.readValue(readerUserList, UserList.class);
    allCars = mapper.readValue(readerCarList, CarList.class);
    Driver loggedInDriver = null;
    Provider loggedInProvider = null;
    try {
      System.out.println("Try Driver");
      loggedInDriver = mapper.readValue(readerLoggedInAs, Driver.class);
      System.out.println("Driver succeed");
    } catch (RuntimeException e) {
      //TODO: handle exception
      System.out.println("Driver failed");
    }
    /*try {
      System.out.println("Try Provider");
      loggedInProvider = mapper.readValue(readerLoggedInAs, Provider.class);
      System.out.println("Provider succeed");
    } catch (Exception e) {
      //TODO: handle exception
      System.out.println("Provider failed");
    }*/
    //loggedInDriver = mapper.readValue(readerLoggedInAs, Driver.class);
    //loggedInProvider = mapper.readValue(readerLoggedInAs, Provider.class);
    if (loggedInDriver != null) {
      loggedInAs = loggedInDriver;
      System.out.println("Logged in as Driver");
    }
    /*if (loggedInProvider != null) {
      loggedInAs = loggedInProvider;
      System.out.println("Logged in as Provider");
    }*/

  }

  @FXML
  public abstract void initialize();

  /**
   * Initializes a JSONDeserialize-instance and reads main.json.
   */
  protected void importJson() throws IOException {
    File f = new File(System.getProperty("user.home") + "/.tilbaketur");
    boolean mkdir = f.mkdir();
    if (mkdir) {
      useDefaultValues();
    } else {
      try {
        System.out.println("prøver å sjekke save");
        Path pathUserList = Paths.get(System.getProperty("user.home")
            + "/.tilbaketur", "userList.json");
        readerUserList = new FileReader(pathUserList.toFile(), StandardCharsets.UTF_8);
        Path pathCarList = Paths.get(System.getProperty("user.home")
            + "/.tilbaketur", "carList.json");
        readerCarList = new FileReader(pathCarList.toFile(), StandardCharsets.UTF_8);
        Path pathLoggedInAs = Paths.get(System.getProperty("user.home")
            + "/.tilbaketur", "loggedInAs.json");
        readerLoggedInAs = new FileReader(pathLoggedInAs.toFile(), StandardCharsets.UTF_8);
      } catch (IOException e) {
        System.out.println(e.getMessage());
        useDefaultValues();
      }
    }

  }

  protected void useDefaultValues() throws IOException {
    readerUserList = new InputStreamReader(userListUrl.openStream(), StandardCharsets.UTF_8);
    readerCarList = new InputStreamReader(carListUrl.openStream(), StandardCharsets.UTF_8);
  }

  protected void exportJson() {
    try {
      mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
      mapper.writeValue(new File(userListFileLocation), getAllUsers());
      mapper.writeValue(new File(carListFileLocation), getAllCars());
      mapper.writeValue(new File(loggedInAsFileLocation), loggedInAs);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  // skal legges i kontrolleren som bruker denne koden
  protected void addCar(Car car) {
    allCars.addItem(car);
    exportJson();
  }

  protected UserList getAllUsers() {
    return this.allUsers;
  }

  protected void removeUser(AbstractUser user) {
    this.allUsers.getItemsList().remove(user);
    exportJson();
  }

  protected CarList getAllCars() {
    return this.allCars;
  }

  /**
   * Method used for switching scenes.
   *
   * @param source fxml file we want to switch to
   * @param button on current scene used to get the window
   *
   * @throws IOException thrown by the loader.
   */
  public void switchScene(String source, Node button) throws IOException {
    Stage stage = (Stage) button.getScene().getWindow();
    //Parent root = FXMLLoader.load(getClass().getResource(source));
    Parent root = FXMLLoader.load(AbstractController.class.getResource(source));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
