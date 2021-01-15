package tilbaketur.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import tilbaketur.core.AbstractUser;
import tilbaketur.core.CarList;
import tilbaketur.core.UserList;
import tilbaketur.core.WorkSpace;


public class WorkSpaceDeserializer extends JsonDeserializer<WorkSpace> {
  @Override public WorkSpace deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    UserList userList = null;
    CarList carList = null;
    AbstractUser loggedInAs;

    if (treeNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) treeNode;
      JsonNode usersNode = objectNode.get("userList");
      if (usersNode instanceof ObjectNode) {
        userList = new UserListDeserializer().deserialize(usersNode);
      }

      JsonNode carsNode = objectNode.get("carList");
      if (carsNode instanceof ObjectNode) {
        carList = new CarListDeserializer().deserialize(carsNode);
      }

      JsonNode loggedInNode;
      loggedInNode = objectNode.get("LoggedInProvider");
      loggedInAs = new ProviderDeserializer().deserialize(loggedInNode);
      if (loggedInAs == null) {
        loggedInNode = objectNode.get("LoggedInDriver");
        loggedInAs = new DriverDeserializer().deserialize(loggedInNode);
      }
      if (loggedInAs != null) {
        return new WorkSpace(userList, carList, loggedInAs);
      } else {
        return new WorkSpace(userList, carList);
      }

    }


    return null;
  }
}
