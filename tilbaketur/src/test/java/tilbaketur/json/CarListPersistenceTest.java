package tilbaketur.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tilbaketur.core.Car;
import tilbaketur.core.CarList;
import tilbaketur.core.Driver;
import tilbaketur.core.Provider;

public class CarListPersistenceTest {
    static ObjectMapper mapper;

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new TilbaketurModule());
    }

    @Test
    public void carListSerializerTest() throws JsonProcessingException {
        Driver driver1 = new Driver("driver1", "Driver1", "Driver One");
        Provider provider1 = new Provider("provider1", "Provider123", "CarRentals");
        Car car1 = new Car(provider1 ,"B", 5, "Free");
        Car car2 = new Car(provider1, "B", 5, "Fuel cost", driver1);
        CarList carList = new CarList();
        carList.addItem(car1);
        carList.addItem(car2);
        String carListCorrectFormat = "{\"cars\":[{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Free\",\"driver\":null},{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Fuel cost\",\"driver\":{\"name\":\"Driver One\"";
        String rest = ",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null}}]}";
        
        assertEquals(carListCorrectFormat+rest, mapper.writeValueAsString(carList));

    }
}