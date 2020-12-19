package tilbaketur.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tilbaketur.core.*;
import tilbaketur.json.CarListSerializer;
import tilbaketur.json.TilbaketurModule;
import tilbaketur.json.UserListSerializer;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractController {


    protected UserList allUsers=new UserList();
    protected CarList allCars=new CarList();
    protected ObjectMapper mapper;
    protected static AbstractUser loggedInAs;
    protected URL userListURL;
    protected  URL carListURL;
    protected Reader readerCarList;
    protected Reader readerUserList;
    protected String userListFileLocation;
    protected String carListFileLocation;


  public AbstractController() throws IOException {
    userListFileLocation = System.getProperty("user.home")+"/.tilbaketur/"+"userList.json";
    carListFileLocation = System.getProperty("user.home")+"/.tilbaketur/"+"carList.json";
    userListURL = UserListSerializer.class.getResource("defaultUserList.json");
    carListURL = CarListSerializer.class.getResource("defaultCarList.json");
    mapper = new ObjectMapper();
    mapper.registerModule(new TilbaketurModule());
    importJson();
    allUsers = mapper.readValue(readerUserList,UserList.class);
    allCars = mapper.readValue(readerCarList, CarList.class);
  }

  @FXML public abstract void initialize();


    /**
     * Initializes a JSONDeserialize-instance and reads main.json.
     */
    protected void importJson() throws IOException {
      File f = new File(System.getProperty("user.home") + "/.tilbaketur");
      boolean mkdir = f.mkdir();
      if (mkdir) useDefaultValues();
      else {
        try {
          Path pathUserList = Paths.get(System.getProperty("user.home") + "/.tilbaketur", "userList.json");
          readerUserList = new FileReader(pathUserList.toFile(), StandardCharsets.UTF_8);
          Path pathCarList = Paths.get(System.getProperty("user.home") + "/.tilbaketur", "carList.json");
          readerCarList = new FileReader(pathCarList.toFile(), StandardCharsets.UTF_8);
        } catch (IOException e) {
          System.out.println(e.getMessage());
          useDefaultValues();
        }
      }

    }

    protected void useDefaultValues() throws IOException {
      readerUserList = new InputStreamReader(userListURL.openStream(), StandardCharsets.UTF_8);
      readerCarList = new InputStreamReader(carListURL.openStream(), StandardCharsets.UTF_8);
    }

    protected void exportJson() {
      try {
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.writeValue(new File(userListFileLocation), getAllUsers());
        mapper.writeValue(new File(carListFileLocation), getAllCars());

      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }


//skal legges i kontrolleren som bruker denne koden
    protected void addCar(Car car) {
      allCars.addItem(car);
      exportJson();
    }

    protected UserList getAllUsers() {
      return this.allUsers;
    }

    protected CarList getAllCars() {
      return this.allCars;
    }


  public void switchScene(String source, Node button) throws IOException {
    Stage stage = (Stage) button.getScene().getWindow();
    Parent root=FXMLLoader.load(getClass().getResource(source));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }


}
