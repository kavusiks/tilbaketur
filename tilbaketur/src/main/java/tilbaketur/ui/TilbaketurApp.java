package tilbaketur.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * TilbaketurApp
 */
public class TilbaketurApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setScene(new Scene(parent));

    }

    public static void main(String[] args) {
        launch(TilbaketurApp.class, args);
    }

    
}