module tilbaketur.fxui {
  requires transitive com.fasterxml.jackson.core;
  requires transitive com.fasterxml.jackson.databind;

  requires transitive javafx.fxml;
  requires transitive javafx.graphics;
  requires transitive javafx.controls;
  requires transitive tilbaketur.core;
  requires transitive tilbaketur.json;
  //requires fx.map.control;

  exports tilbaketur.ui;

  opens tilbaketur.ui to javafx.fxml;

}

