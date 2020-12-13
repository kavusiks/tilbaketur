package tilbaketur.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import tilbaketur.core.Car;
import tilbaketur.core.CarList;
import tilbaketur.core.Driver;
import tilbaketur.core.Provider;
import tilbaketur.core.UserList;

public class TilbaketurModule extends SimpleModule {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final String NAME = "TilbaketurModule";
    private static final VersionUtil VERSION_UTIL = new VersionUtil() {
    };

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

    public static void main(String[] args) throws JsonProcessingException {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new TilbaketurModule());
      CarList carList1 = new CarList();
      Driver driver1 = new Driver("driver1", "Driver1", "Driver One");
      Driver driver2 = new Driver("driver2", "Driver2", "Driver Two");
      Provider provider1 = new Provider("provider1", "Provider123", "CarRentals");
      Car car1 = new Car(provider1 ,"B", 5, "Free");
      Car car2 = new Car(provider1, "B", 5, "Fuel cost", driver1);
      Car car3 = new Car(provider1, "B", 4, "Free", driver2);
      carList1.addItem(car1);
      carList1.addItem(car2);
      carList1.addItem(car3);
      //System.out.println(mapper.writeValueAsString(carList1));
      //System.out.println(provider1.getCars());

      String json = mapper.writeValueAsString(car1);
      Car provi = mapper.readValue(json,Car.class);
      System.out.println(car1.toString());
      System.out.println(provi.toString());
      if(car1.equals(provi)) System.out.println("True");
      else System.out.println("false");

      }

}