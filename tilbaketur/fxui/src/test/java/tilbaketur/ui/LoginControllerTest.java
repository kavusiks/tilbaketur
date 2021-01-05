package tilbaketur.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;




public class LoginControllerTest extends ApplicationTest {

    private LoginController controller;

    @Override
    public void start(final Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    public void testController() {
        assertNotNull(controller, "The controller is not set");
    }

    @Test
    public void testGoToCreateAccount() {
        clickOn("#createAccountBtn");
        // Assert on correct scene by verifying that a correct button is on scene
        Node textFieldOnCreateAccountScene = lookup("#nameField").query();
        assertNotNull(textFieldOnCreateAccountScene, "Button didn't change the scene");
    }

    @Test
    public void testInvalidEmptyLogin() {
         // Assert on correct scene by verifying that a correct button is on scene
        Node buttonOnCreateAccountScene = lookup("#loginBtn").query();
        assertNotNull(buttonOnCreateAccountScene, "Is not on the correct Scene");
        //Verifying that no errors are shown
        Label errorMessage = lookup("#errorMessage").query();
        assertEquals("", errorMessage.getText());
        clickOn("#loginBtn");
        //Looking up for the same errorMessage label on the same screen
        errorMessage = lookup("#errorMessage").query();
        assertNotNull(errorMessage, "The scene shall not be changed");
        //verifying the errormessage
        assertNotNull(errorMessage.getText());

    }

    @Test
    public void testValidLogin() {
        //Login in with an already existing account
        clickOn("#usernameField");
        write("driver1");
        clickOn("#passwordField");
        write("Driver1");
        clickOn("#loginBtn"); 
    }





    
}