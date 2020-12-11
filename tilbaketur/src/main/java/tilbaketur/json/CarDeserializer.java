package tilbaketur.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import tilbaketur.core.Car;
import tilbaketur.core.Driver;
import tilbaketur.core.Provider;

public class CarDeserializer extends JsonDeserializer<Car> {

    @Override
    public Car deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    public Car deserialize(JsonNode treeNode){
         if (treeNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) treeNode;
            //Car car = new Car();
            String carClass="";
            int seats=0;
            String price="";
            JsonNode providerNode = objectNode.get("provider");
            Provider provider = new ProviderDeserializer().deserialize(providerNode);
            JsonNode carClassNode = objectNode.get("carClass");
            if(carClassNode instanceof TextNode) carClass = ((TextNode) carClassNode).asText();
            JsonNode seatsNode = objectNode.get("seats");
            if(seatsNode instanceof IntNode) seats = ((IntNode) seatsNode).asInt();
            JsonNode priceNode = objectNode.get("price");
            if(priceNode instanceof TextNode) price = ((TextNode) priceNode).asText();
            JsonNode driverNode=null;
            Driver driver=null;
            if (objectNode.has("driver")){
                driverNode = objectNode.get("driver");
                driver = new DriverDeserializer().deserialize(driverNode);
            }
            if((provider==null) || carClass=="" || price=="") return null;
            else if (driver != null) return new Car(provider, carClass, seats, price, driver);
            else return new Car(provider, carClass, seats, price);
        }
        return null;
    }
}
