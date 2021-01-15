package tilbaketur.json;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import tilbaketur.core.Driver;
import tilbaketur.core.Provider;
import tilbaketur.core.UserList;

class UserListSerializer extends JsonSerializer<UserList> {

  /*
   * format: {"users": [...]}
   */

  @Override
  public void serialize(UserList userList, JsonGenerator jsonGen, 
      SerializerProvider serializerProvider) throws IOException {
    jsonGen.writeStartObject();
    jsonGen.writeArrayFieldStart("users");
    userList.getItemsList().forEach(user -> {
      try {
        if (user instanceof Driver) {
          jsonGen.writeString("Driver:");
        }
        if (user instanceof Provider) {
          jsonGen.writeString("Provider:");
        }
        jsonGen.writeObject(user);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    jsonGen.writeEndArray();
    jsonGen.writeEndObject();

  }

}
