package tilbaketur.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tilbaketur.core.Driver;

//TODO: vurder å slå sammen med Provider dersom det ikke er behov for to ulike klasser her
public class DriverPersistenceTest {
    static ObjectMapper mapper;
    static Driver driver1 = new Driver("driver1", "Driver1", "Driver One");
    static String driver1CorrectFormat = "{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null}";

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new TilbaketurModule());
    }

    @Test
    public void driverSerializerTest() throws JsonProcessingException {
        assertEquals(driver1CorrectFormat, mapper.writeValueAsString(driver1));
    }

    @Test
    void driverDeserializerTest() throws JsonMappingException, JsonProcessingException {
        Driver driver2 = mapper.readValue(driver1CorrectFormat, Driver.class);
        checkTwoDrivers(driver1, driver2);
        //assertTrue(driver1.equals(driver2));
    }

    public static void checkTwoDrivers(Driver driver1, Driver driver2) {
        assertEquals(driver1.getName(),driver2.getName());
        assertEquals(driver1.getUsername(), driver2.getUsername());
        assertEquals(driver1.getPassword(), driver2.getPassword());
        assertEquals(driver1.getCars(), driver2.getCars());
    }




}
