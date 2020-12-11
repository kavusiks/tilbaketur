package tilbaketur.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tilbaketur.core.Driver;
import tilbaketur.core.Provider;
import tilbaketur.core.UserList;


public class UserListPersistenceTest {
        static ObjectMapper mapper;

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new TilbaketurModule());
    }

    
    @Test
    public void userListSerializerTest() throws JsonProcessingException {
        Driver driver1 = new Driver("driver1", "Driver1", "Driver One");
        Driver driver2 = new Driver("driver2", "Driver2", "Driver Two");
        Provider provider1 = new Provider("provider1", "Provider123", "CarRentals");
        UserList userList = new UserList();
        userList.addItem(driver1);
        userList.addItem(driver2);
        userList.addItem(provider1);

        String userListCorrectFormat = "{\"userList\":[\"Driver:\",{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null},\"Driver:\",{\"name\":\"Driver Two\",\"username\":\"driver2\",\"password\":\"Driver2\",\"requestedCar\":null},\"Provider:\",{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"}]}";
        assertEquals(userListCorrectFormat, mapper.writeValueAsString(userList));

    }
}