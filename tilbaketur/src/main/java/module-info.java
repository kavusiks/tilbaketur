module tilbaketur {
  requires transitive com.fasterxml.jackson.core;
  requires transitive com.fasterxml.jackson.databind;

  requires javafx.fxml;
  requires transitive javafx.graphics;
  requires javafx.controls;

  //requires fx.map.control;

  exports tilbaketur.core;
  exports tilbaketur.json;
  exports tilbaketur.ui;

  opens tilbaketur.ui to javafx.fxml;

}
