package tilbaketur.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import tilbaketur.core.Driver;

public class DriverDeserializer extends JsonDeserializer<Driver> {
  @Override
  public Driver deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

  /**
   * Deserializer for Driver by JsonNode.
   *
   * @param treeNode JsonNode to deserialize by
   * @return deserialized Driver
   */
  public Driver deserialize(JsonNode treeNode) {
    if (treeNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) treeNode;
      // Car car = new Car();
      String username = "";
      String password = "";
      String name = "";
      JsonNode usernameNode = objectNode.get("username");
      if (usernameNode instanceof TextNode) {
        username = ((TextNode) usernameNode).asText();
      }
      JsonNode passwordNode = objectNode.get("password");
      if (passwordNode instanceof TextNode) {
        password = ((TextNode) passwordNode).asText();
      }
      JsonNode nameNode = objectNode.get("name");
      if (nameNode instanceof TextNode) {
        name = ((TextNode) nameNode).asText();
      }
      if (!(username.equals("") && name.equals("") && password.equals(""))) {
        return new Driver(username, password, name);
      }
    }
    return null;
  }

}