package tilbaketur.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import tilbaketur.core.Provider;

public class ProviderSerializer extends JsonSerializer<Provider> {

    /*
     * format:
     * { 
     * "name": "...",
     * "username": "...",
     * "password": "...",
     * "cars":[...]
     * }
     */
// TODO: vurder behovet for å ha separat provider og driver
    @Override
    public void serialize(Provider provider, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("name", provider.getName());
        jGen.writeStringField("username", provider.getUsername());
        jGen.writeStringField("password", provider.getPassword());
        /*jGen.writeArrayFieldStart("cars");
        provider.getCars().forEach(car-> {
            try {
                jGen.writeObject(car);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jGen.writeEndArray();*/
        jGen.writeEndObject();

    }


}