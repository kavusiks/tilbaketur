package tilbaketur.json;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.Test;

import tilbaketur.core.Driver;

//TODO: vurder å slå sammen med Provider dersom det ikke er behov for to ulike klasser her
public class DriverPersistenceTest extends AbstractPersistenceTest{
    
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

}
