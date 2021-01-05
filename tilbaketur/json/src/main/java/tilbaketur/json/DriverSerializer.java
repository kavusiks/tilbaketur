package tilbaketur.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import tilbaketur.core.Driver;

public class DriverSerializer extends JsonSerializer<Driver> {

  /*
   * format: { "name": "...", "username": "...", "password": "...", "cars":[...],
   * "requestedCar": "..." }
   */

  @Override
  public void serialize(Driver driver, JsonGenerator jsonGen,
      SerializerProvider serializerProvider) throws IOException {
    jsonGen.writeStartObject();
    jsonGen.writeStringField("name", driver.getName());
    jsonGen.writeStringField("username", driver.getUsername());
    jsonGen.writeStringField("password", driver.getPassword());
    /*
     * jGen.writeArrayFieldStart("cars"); driver.getCars().forEach(car -> { try {
     * jGen.writeObject(car); } catch (IOException e) { e.printStackTrace(); } });
     * jGen.writeEndArray();
     */
    jsonGen.writeObjectField("requestedCar", driver.getRequestedCar());
    jsonGen.writeEndObject();

  }

}