package tilbaketur.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import tilbaketur.core.Car;

public class CarSerializer extends JsonSerializer<Car> {

    /*
    format:
    {
        "provider": "...",
        "carClass": "...",
        "seats": "...",
        "price": "...",
        "driver": "..."
    }
    */


    @Override
    public void serialize(Car car, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject();
        jGen.writeObjectField("provider", car.getProvider());
        jGen.writeStringField("carClass", car.getCarClass());
        jGen.writeNumberField("seats", car.getSeats());
        jGen.writeStringField("price", car.getPrice());
        jGen.writeObjectField("driver", car.getDriver());
        jGen.writeEndObject();

    }
}