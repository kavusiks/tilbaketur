package tilbaketur.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import tilbaketur.core.CarList;
import tilbaketur.core.Driver;
import tilbaketur.core.UserList;

public class TilbaketurPersistence {
  private ObjectMapper mapper;

  public TilbaketurPersistence() {
    mapper = new ObjectMapper();
    mapper.registerModule(new TilbaketurModule());
  }

  public UserList readUserList(Reader reader) throws IOException {
    return mapper.readValue(reader, UserList.class);
  }

  public CarList readCarList(Reader reader) throws IOException {
    return mapper.readValue(reader, CarList.class);
  }

  public Driver readDriver(Reader reader) throws IOException {
    return mapper.readValue(reader, Driver.class);
  }

  public void configureBeforeWrite() {
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
  }

  public void writeUserList(String userListFileLocation, UserList userList) throws IOException {
    mapper.writeValue(new File(userListFileLocation), userList);
  }

  public void writeCarList(String carListFileLocation, CarList carList) throws IOException {
    mapper.writeValue(new File(carListFileLocation), carList);
  }

  public void writeDriver(String loggedInAsFileLocation, Driver driver) throws IOException {
    mapper.writeValue(new File(loggedInAsFileLocation), driver);
  }
}
