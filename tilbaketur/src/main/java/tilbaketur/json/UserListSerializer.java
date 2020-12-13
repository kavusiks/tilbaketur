package tilbaketur.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import tilbaketur.core.Driver;
import tilbaketur.core.Provider;
import tilbaketur.core.UserList;

public class UserListSerializer extends JsonSerializer<UserList> {

    /*
     * format: {"users": [...]}
     */

    @Override
    public void serialize(UserList userList, JsonGenerator jGen, SerializerProvider serializerProvider)
            throws IOException {
        jGen.writeStartObject();
        jGen.writeArrayFieldStart("userList");
        userList.getItemsList().forEach(user -> {
            try {
                if(user instanceof Driver) jGen.writeString("Driver:");
                if(user instanceof Provider) jGen.writeString("Provider:");
                jGen.writeObject(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jGen.writeEndArray();
        jGen.writeEndObject();

    }



}