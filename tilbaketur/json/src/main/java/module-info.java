module tilbaketur.json {
  requires transitive com.fasterxml.jackson.core;
  requires transitive com.fasterxml.jackson.databind;

  requires tilbaketur.core;

  exports tilbaketur.json;
}
