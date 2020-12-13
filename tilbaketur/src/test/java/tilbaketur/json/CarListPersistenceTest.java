package tilbaketur.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tilbaketur.core.CarList;

public class CarListPersistenceTest extends AbstractPersistenceTest{
    static CarList carList1;
    // To long string for java, so had to divide it in two
    static String carList1CorrectFormat1of2 = "{\"cars\":[{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Free\",\"driver\":null},{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Fuel cost\",";
    static String carList1CorrectFormat2of2 = "\"driver\":{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null}}]}";

    @BeforeAll
    public static void setUp() {
        carList1 = new CarList();
        carList1.addItem(car1);
        carList1.addItem(car2);
    }

    @Test
    public void carListSerializerTest() throws JsonProcessingException {
        assertEquals(carList1CorrectFormat1of2 + carList1CorrectFormat2of2, mapper.writeValueAsString(carList1));
    }

    @Test
    public void carListDeserializerTest() throws JsonMappingException, JsonProcessingException {
        CarList carList2 = mapper.readValue((carList1CorrectFormat1of2+carList1CorrectFormat2of2), CarList.class);
        checkTwoCarLists(carList1, carList2);

    }
}