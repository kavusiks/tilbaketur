package tilbaketur.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import tilbaketur.core.Driver;

public class DriverSerializer extends JsonSerializer<Driver> {

   
    /*
     * format:
     * { 
     * "name": "...",
     * "username": "...",
     * "password": "...",
     * "cars":[...],
     * "requestedCar": "..."
     * }
     */

    @Override
    public void serialize(Driver driver, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("name", driver.getName());
        jGen.writeStringField("username", driver.getUsername());
        jGen.writeStringField("password", driver.getPassword());
        /*jGen.writeArrayFieldStart("cars");
        driver.getCars().forEach(car -> {
            try {
                jGen.writeObject(car);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jGen.writeEndArray();*/
        jGen.writeObjectField("requestedCar", driver.getRequestedCar());
        jGen.writeEndObject();

    }


}