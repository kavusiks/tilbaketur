package tilbaketur.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.lang.ModuleLayer.Controller;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tilbaketur.core.AbstractUser;

/**
 * CreateUserControllerTest
 */
public class CreateUserControllerTest extends ApplicationTest {
    private CreateAccountController controller;

    @Override
    public void start(final Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccount.fxml"));
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
    public void testCreateInvalidEmptyAccount() {
          // Assert on correct scene by verifying that a correct button is on scene
        Node textFieldOnCreateAccountScene = lookup("#nameField").query();
        assertNotNull(textFieldOnCreateAccountScene, "Is not on the correct Scene");
        Label errorMessage = lookup("#errorMessageLabel").query();
        assertEquals("", errorMessage.getText());
        clickOn("#createAccountBtn");
        assertNotNull(errorMessage.getText());
    }

    @Test
    public void testCreateInvalidAlreadyExistingAccount() {
          // Assert on correct scene by verifying that a correct button is on scene
        Node textFieldOnCreateAccountScene = lookup("#nameField").query();
        assertNotNull(textFieldOnCreateAccountScene, "Is not on the correct Scene");
        clickOn("#nameField");
        write("test testersen");
        clickOn("#usernameField");
        write("driver1");
        clickOn("#passwordField");
        write("pass1");
        clickOn("#confirmPasswordField");
        write("pass1");
        clickOn("#radioBtnDriver");
        clickOn("#createAccountBtn");

        Label errorMessage = lookup("#errorMessageLabel").query();
        assertEquals("Username already exists", errorMessage.getText());
    }

   /* @Test
    public void testCreateValidAccount() throws InterruptedException {
        // Assert on correct scene by verifying that a correct button is on scene
        Node textFieldOnCreateAccountScene = lookup("#nameField").query();
        assertNotNull(textFieldOnCreateAccountScene, "Is not on the correct Scene");
        clickOn("#nameField");
        write("test testersen");
        clickOn("#usernameField");
        write("test1");
        clickOn("#passwordField");
        write("pass1");
        clickOn("#confirmPasswordField");
        write("pass1");
        clickOn("#radioBtnDriver");
        clickOn("#createAccountBtn");

        Thread.sleep(30);
        //Verifying thath we are on correct scene Login.fxml
        Node buttonOnLoginScene = lookup("#loginBtn").query();
        assertNotNull(buttonOnLoginScene);
        AbstractUser testUser = controller.getAllUsers().getItemsList().stream().filter(user -> user.getUsername().equals("test1")).findFirst().orElse(null);
        controller.removeUser(testUser);
        //TODO: metoden over bør gjøres på en annen måte.

    }*/
    
}

