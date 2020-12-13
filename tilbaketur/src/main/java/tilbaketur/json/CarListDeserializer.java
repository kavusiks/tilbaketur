package tilbaketur.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import tilbaketur.core.Car;
import tilbaketur.core.CarList;



public class CarListDeserializer extends JsonDeserializer<CarList> {
    @Override
    public CarList deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        if (treeNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) treeNode;
            CarList carList = new CarList();
            JsonNode carsNode = objectNode.get("cars");
            if (carsNode instanceof ArrayNode) {
                for (JsonNode elementNode : ((ArrayNode) carsNode)) {
                    Car car = new CarDeserializer().deserialize(elementNode);
                    if (car!=null) carList.addItem(car);
                }
            }
            return carList;
        }
        return null;
    }
}