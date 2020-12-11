package tilbaketur.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tilbaketur.core.Car;
import tilbaketur.core.Driver;
import tilbaketur.core.Provider;

public class CarPersistenceTest {
    static ObjectMapper mapper;
    static Driver driver1;
    static Provider provider1;
    static Car car1;
    static Car car2;
    static String car1CorrectFormat = "{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Free\",\"driver\":null}";
    static String car2CorrectFormat = "{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Fuel cost\",\"driver\":{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null}}";
    static Driver driver2;

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new TilbaketurModule());
        driver1 = new Driver("driver1", "Driver1", "Driver One");

        driver2 = new Driver("driver1", "Driver1", "Driver On");
        provider1 = new Provider("provider1", "Provider123", "CarRentals");
        car1 = new Car(provider1, "B", 5, "Free");
        car2 = new Car(provider1, "B", 5, "Fuel cost", driver1);
    }

    @Test
    public void carSerializerTest() throws JsonProcessingException {
        assertEquals(car1CorrectFormat, mapper.writeValueAsString(car1));
        assertEquals(car2CorrectFormat, mapper.writeValueAsString(car2));
    }

    public void carDeserializerTest() throws JsonMappingException, JsonProcessingException {
        Car car3 = mapper.readValue(car1CorrectFormat, Car.class);
        Car car4 = mapper.readValue(car2CorrectFormat, Car.class);
        car4.setDriver(driver2);
        checkTwoCars(car1, car3);
        checkTwoCars(car2, car4);


        
    }

    private void checkTwoCars(Car car1, Car car2) {
        assertEquals(car1.getProvider(), car2.getProvider());
        assertEquals(car1.getCarClass(), car2.getCarClass());
        assertEquals(car1.getSeats(), car2.getSeats());
        assertEquals(car1.getPrice(), car2.getPrice());
       //TODO:Fikse denne
        DriverPersistenceTest.checkTwoDrivers(car1.getDriver(), car2.getDriver());

    }
}