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

import tilbaketur.core.Driver;
import tilbaketur.core.Provider;
import tilbaketur.core.UserList;


public class UserListDeserializer extends JsonDeserializer<UserList> {
    @Override
    public UserList deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        if (treeNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) treeNode;
            UserList userList = new UserList();
            JsonNode usersNode = objectNode.get("userList");
            if (usersNode instanceof ArrayNode) {
                for (JsonNode elementNode : ((ArrayNode) usersNode)) {
                    if(elementNode.has("Driver:")){
                        Driver driver = new DriverDeserializer().deserialize(elementNode);
                        if (driver != null) userList.addItem(driver);
                    }

                    if(elementNode.has("Provider:")){
                        Provider provider = new ProviderDeserializer().deserialize(elementNode);
                        if (provider != null) userList.addItem(provider);
                    }
                }
            }
            return userList;
        }
        return null;
    }
}