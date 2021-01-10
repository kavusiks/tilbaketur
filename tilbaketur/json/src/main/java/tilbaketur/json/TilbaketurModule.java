package tilbaketur.json;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import tilbaketur.core.Car;
import tilbaketur.core.CarList;
import tilbaketur.core.Driver;
import tilbaketur.core.Provider;
import tilbaketur.core.UserList;

class TilbaketurModule extends SimpleModule {

  private static final long serialVersionUID = 1L;
  private static final String NAME = "TilbaketurModule";
  private static final VersionUtil VERSION_UTIL = new VersionUtil() {
  };

  /**
   * Setting up serializers and deserializers to their classes.
   */
  public TilbaketurModule() {
    super(NAME, VERSION_UTIL.version());
    addSerializer(Car.class, new CarSerializer());
    addSerializer(CarList.class, new CarListSerializer());
    addSerializer(Driver.class, new DriverSerializer());
    addSerializer(Provider.class, new ProviderSerializer());
    addSerializer(UserList.class, new UserListSerializer());

    addDeserializer(Car.class, new CarDeserializer());
    addDeserializer(CarList.class, new CarListDeserializer());
    addDeserializer(Driver.class, new DriverDeserializer());
    addDeserializer(Provider.class, new ProviderDeserializer());
    addDeserializer(UserList.class, new UserListDeserializer());

  }

}
