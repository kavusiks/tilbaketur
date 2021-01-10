package tilbaketur.json;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import tilbaketur.core.Car;

class CarSerializer extends JsonSerializer<Car> {

  /*
   * format: { "provider": "...", "carClass": "...", "seats": "...", "price":
   * "...", "driver": "..." }
   */

  @Override
  public void serialize(Car car, JsonGenerator jsonGen, 
      SerializerProvider serializerProvider) throws IOException {
    jsonGen.writeStartObject();
    jsonGen.writeObjectField("provider", car.getProvider());
    jsonGen.writeStringField("carClass", car.getCarClass());
    jsonGen.writeNumberField("seats", car.getSeats());
    jsonGen.writeStringField("price", car.getPrice());
    jsonGen.writeObjectField("driver", car.getDriver());
    jsonGen.writeEndObject();

  }
}
