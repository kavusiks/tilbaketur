package tilbaketur.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tilbaketur.core.CarList;
import tilbaketur.core.UserList;
import tilbaketur.core.WorkSpace;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkSpacePersistenceTest extends AbstractPersistenceTest {
  static WorkSpace workSpace1;
  static UserList userList1;
  //String userList1CorrectFormat = "{\"userList\":[\"Driver:\",{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null},\"Driver:\",{\"name\":\"Driver Two\",\"username\":\"driver2\",\"password\":\"Driver2\",\"requestedCar\":null},\"Provider:\",{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"}]}";
  static CarList carList1;
  // To long string for java, so had to divide it in two
  static String workSpaceCorrectFormatProvider = "{\"userList\":{\"users\":[\"Driver:\",{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null},\"Driver:\",{\"name\":\"Driver Two\",\"username\":\"driver2\",\"password\":\"Driver2\",\"requestedCar\":null},\"Provider:\",{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"}]},\"carList\":{\"cars\":[{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Free\",\"driver\":null},{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Fuel cost\",\"driver\":{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null}}]},\"LoggedInProvider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"}}";
  static String workSpaceCorrectFormatDriver = "{\"userList\":{\"users\":[\"Driver:\",{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null},\"Driver:\",{\"name\":\"Driver Two\",\"username\":\"driver2\",\"password\":\"Driver2\",\"requestedCar\":null},\"Provider:\",{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"}]},\"carList\":{\"cars\":[{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Free\",\"driver\":null},{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Fuel cost\",\"driver\":{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null}}]},\"LoggedInDriver\":{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null}}";
  static String workSpaceCorrectFormatNull = "{\"userList\":{\"users\":[\"Driver:\",{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null},\"Driver:\",{\"name\":\"Driver Two\",\"username\":\"driver2\",\"password\":\"Driver2\",\"requestedCar\":null},\"Provider:\",{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"}]},\"carList\":{\"cars\":[{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Free\",\"driver\":null},{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Fuel cost\",\"driver\":{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null}}]},\"LoggedInDriver\":null}";
  @BeforeAll
  public static void setUp() {
    userList1 = new UserList();
    userList1.addItem(driver1);
    userList1.addItem(driver2);
    userList1.addItem(provider1);
    carList1 = new CarList();
    carList1.addItem(car1);
    carList1.addItem(car2);
    workSpace1 = new WorkSpace(userList1, carList1, provider1);
  }

  @Test
  public void workSpaceSerializerTest() throws JsonProcessingException {
    assertEquals(workSpaceCorrectFormatProvider, mapper.writeValueAsString(workSpace1), "WorkSpace with provder loggedin failed");
    //System.out.println(workSpace1.getCarList().toString());
    workSpace1.setLoggedInAs(driver1);
    assertEquals(workSpaceCorrectFormatDriver, mapper.writeValueAsString(workSpace1), "WorkSpace with driver loggedin failed");
    workSpace1.setLoggedInAs(null);
    assertEquals(workSpaceCorrectFormatNull, mapper.writeValueAsString(workSpace1), "WorkSpace with noone loggedin failed");

    //System.out.println(mapper.writeValueAsString(workSpace1));

  }

  @Test
  public void workSpaceDeserializerTest() throws JsonMappingException, JsonProcessingException {
    WorkSpace workSpace2 = mapper.readValue(workSpaceCorrectFormatProvider, WorkSpace.class);
    //System.out.println(mapper.writeValueAsString(workSpace2));
    checkTwoWorkSpaces(workSpace1, workSpace2);
    workSpace2 = mapper.readValue(workSpaceCorrectFormatDriver, WorkSpace.class);
    checkTwoWorkSpaces(workSpace1, workSpace2);
    workSpace2 = mapper.readValue(workSpaceCorrectFormatNull,WorkSpace.class);
    checkTwoWorkSpaces(workSpace1, workSpace2);

  }
}
