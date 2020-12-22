package tilbaketur.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TilbaketurApp extends Application {
  // todo: kan ikke være static, finn en måte å bytte scene på
  public static Stage stage;

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
    stage.setScene(new Scene(parent));
    stage.show();

  }

  public static void main(String[] args) {
    launch(TilbaketurApp.class, args);
  }

}
