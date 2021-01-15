package tilbaketur.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import tilbaketur.core.Car;
import tilbaketur.core.CarList;
import tilbaketur.core.Driver;
import tilbaketur.core.Provider;
import tilbaketur.core.UserList;
import tilbaketur.core.WorkSpace;

public class WorkSpaceSerializer extends JsonSerializer<WorkSpace> {

  @Override public void serialize(WorkSpace workSpace, JsonGenerator jsonGen,
      SerializerProvider serializerProvider) throws IOException {
    jsonGen.writeStartObject();
    jsonGen.writeObjectField("userList", workSpace.getUserList());
    jsonGen.writeObjectField("carList", workSpace.getCarList());
    /*
    try {

      if(workSpace.getLoggedInAs() == null) {
        jsonGen.writeObjectField("Null", workSpace.getLoggedInAs());
      }
      if(workSpace.getLoggedInAs() instanceof Driver) {
        //jsonGen.writeString("Driver:");
        jsonGen.writeObjectField("Driver", workSpace.getLoggedInAs());
      }
      if(workSpace.getLoggedInAs() instanceof Provider) {
       //jsonGen.writeString("Provider:");
        jsonGen.writeObjectField("Provider", workSpace.getLoggedInAs());
      }
      jsonGen.writeEndObject();
      } catch (IOException e) {
      e.printStackTrace();

    }

    */
    //nå setter den null som driver..jallakoding
    if (workSpace.getLoggedInAs() instanceof Provider) {
      //jsonGen.writeString("Provider:");
      jsonGen.writeObjectField("LoggedInProvider", workSpace.getLoggedInAs());
    } else {
      jsonGen.writeObjectField("LoggedInDriver", workSpace.getLoggedInAs());
    }
    jsonGen.writeEndObject();
  }

}
