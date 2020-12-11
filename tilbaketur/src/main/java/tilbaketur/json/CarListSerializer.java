package tilbaketur.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import tilbaketur.core.CarList;

public class CarListSerializer extends JsonSerializer<CarList> {

    /*
    * format: {"cars": [...]}
    */


    @Override
    public void serialize(CarList carList, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeArrayFieldStart("cars");
        carList.getItemsList().forEach(car -> {
            try {
                jGen.writeObject(car);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jGen.writeEndArray();
        jGen.writeEndObject();

    }

}