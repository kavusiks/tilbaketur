module tilbaketur.fxui {
  requires transitive com.fasterxml.jackson.core;
  requires transitive com.fasterxml.jackson.databind;

  requires javafx.fxml;
  requires transitive javafx.graphics;
  requires javafx.controls;
  requires tilbaketur.core;
  requires tilbaketur.json;
  //requires fx.map.control;

  exports tilbaketur.ui;

  opens tilbaketur.ui to javafx.fxml;

}

