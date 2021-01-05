package tilbaketur.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tilbaketur.core.UserList;

public class UserListPersistenceTest extends AbstractPersistenceTest {

    
    static UserList userList1;
    String userList1CorrectFormat = "{\"userList\":[\"Driver:\",{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null},\"Driver:\",{\"name\":\"Driver Two\",\"username\":\"driver2\",\"password\":\"Driver2\",\"requestedCar\":null},\"Provider:\",{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"}]}";

    @BeforeAll
    public static void setUp() {
        userList1 = new UserList();
        userList1.addItem(driver1);
        userList1.addItem(driver2);
        userList1.addItem(provider1);
    }

    @Test
    public void userListSerializerTest() throws JsonProcessingException {
        assertEquals(userList1CorrectFormat, mapper.writeValueAsString(userList1));
    }

    @Test
    public void userListDeserializerTest() throws JsonMappingException, JsonProcessingException {
        UserList userList2 = mapper.readValue(userList1CorrectFormat, UserList.class);
        checkTwoUserLists(userList1, userList2);
    }
}