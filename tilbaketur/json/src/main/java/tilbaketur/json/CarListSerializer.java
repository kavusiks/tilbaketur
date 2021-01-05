package tilbaketur.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import tilbaketur.core.CarList;

public class CarListSerializer extends JsonSerializer<CarList> {

  /*
   * format: {"cars": [...]}
   */

  @Override
  public void serialize(CarList carList, JsonGenerator jsonGen,
      SerializerProvider serializerProvider)
      throws IOException {
    jsonGen.writeStartObject();
    jsonGen.writeArrayFieldStart("cars");
    carList.getItemsList().forEach(car -> {
      try {
        jsonGen.writeObject(car);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    jsonGen.writeEndArray();
    jsonGen.writeEndObject();

  }

}