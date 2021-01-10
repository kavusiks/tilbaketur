module tilbaketur.fxui {
  requires transitive javafx.fxml;
  requires transitive javafx.graphics;
  requires transitive javafx.controls;
  requires transitive tilbaketur.core;
  requires transitive tilbaketur.json;

  exports tilbaketur.ui;

  opens tilbaketur.ui to javafx.fxml;

}

