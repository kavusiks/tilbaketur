package tilbaketur.ui;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import tilbaketur.core.Car;

public class AvailableCarsController extends AbstractController {
  @FXML
  ListView<Car> availableCarsListView;

  public AvailableCarsController() throws IOException {
    super();
  }

  @Override
  public void initialize() {
    allCars.getItemsList().stream().forEach(car -> availableCarsListView.getItems().add(car));
  }

}
