package tilbaketur.ui;

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
import tilbaketur.core.WorkSpace;
import tilbaketur.json.TilbaketurPersistence;

public abstract class AbstractController {

  protected WorkSpace workSpace;
  protected TilbaketurPersistence persistence;
  protected URL workSpaceUrl;
  protected Reader readerWorkSpace;
  protected String workSpaceFileLocation;

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
    workSpaceFileLocation = System.getProperty("user.home") + "/.tilbaketur/" + "workSpace.json";
    workSpaceUrl = TilbaketurApp.class.getResource("defaultWorkSpace.json");
    persistence = new TilbaketurPersistence();
    importJson();
    workSpace = persistence.readWorkSpace(readerWorkSpace);
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
        Path pathWorkSpace = Paths.get(System.getProperty("user.home")
            + "/.tilbaketur", "workSpace.json");
        readerWorkSpace = new FileReader(pathWorkSpace.toFile(), StandardCharsets.UTF_8);
      } catch (IOException e) {
        System.out.println(e.getMessage());
        useDefaultValues();
      }
    }

  }

  protected void useDefaultValues() throws IOException {
    readerWorkSpace = new InputStreamReader(workSpaceUrl.openStream(), StandardCharsets.UTF_8);
  }

  protected void exportJson() {
    try {
      persistence.configureBeforeWrite();
      persistence.writeWorkSpace(workSpaceFileLocation, getWorkSpace());

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  // skal legges i kontrolleren som bruker denne koden
  protected void addCar(Car car) {
    workSpace.addCar(car);
    exportJson();
  }

  // skal legges i kontrolleren som bruker denne koden
  protected UserList getAllUsers() {
    return workSpace.getUserList();
  }

  protected void removeUser(AbstractUser user) {
    workSpace.removeUser(user);
    exportJson();
  }

  // skal legges i kontrolleren som bruker denne koden
  protected CarList getAllCars() {
    return workSpace.getCarList();
  }

  protected WorkSpace getWorkSpace() {
    return this.workSpace;
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
