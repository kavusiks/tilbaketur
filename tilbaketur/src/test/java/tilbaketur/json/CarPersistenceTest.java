package tilbaketur.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.Test;

import tilbaketur.core.Car;

public class CarPersistenceTest extends AbstractPersistenceTest{

    @Test
    public void carSerializerTest() throws JsonProcessingException {
        assertEquals(car1CorrectFormat, mapper.writeValueAsString(car1));
        assertEquals(car2CorrectFormat, mapper.writeValueAsString(car2));
    }

    @Test
    public void carDeserializerTest() throws JsonMappingException, JsonProcessingException {
        Car car3 = mapper.readValue(car1CorrectFormat, Car.class);
        Car car4 = mapper.readValue(car2CorrectFormat, Car.class);
        //without assigned driver
        checkTwoCars(car1, car3);
        //with assigned driver
        checkTwoCars(car2, car4);


        
    }

}