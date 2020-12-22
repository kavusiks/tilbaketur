package tilbaketur.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import tilbaketur.core.Provider;

public class ProviderSerializer extends JsonSerializer<Provider> {

  /*
   * format: { "name": "...", "username": "...", "password": "...", "cars":[...] }
   */

  // TODO: vurder behovet for å ha separat provider og driver

  /**
   * Serializer for Provider.
   *
   * @param provider to be serialized
   * @param jsonGen used to write the JSONstrings.
   * @param serializerProvider serializer to use
   *
   * @throws IOException when there are I/O errors
   */
  @Override
  public void serialize(Provider provider, JsonGenerator jsonGen, 
      SerializerProvider serializerProvider) throws IOException {
    jsonGen.writeStartObject();
    jsonGen.writeStringField("name", provider.getName());
    jsonGen.writeStringField("username", provider.getUsername());
    jsonGen.writeStringField("password", provider.getPassword());
    /*
     * jGen.writeArrayFieldStart("cars"); provider.getCars().forEach(car-> { try {
     * jGen.writeObject(car); } catch (IOException e) { e.printStackTrace(); } });
     * jGen.writeEndArray();
     */
    jsonGen.writeEndObject();

  }

}