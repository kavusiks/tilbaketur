package tilbaketur.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserListTest {
    UserList userList1;
    Driver driver1;
    Driver driver2;
    Provider provider1;

    @BeforeEach
    public void setup() {
        userList1 = new UserList();
        driver1 = new Driver("driver1", "Driver1", "Driver One");
        driver2 = new Driver("driver2", "Driver2", "Driver Two");
        provider1 = new Provider("provider1", "Provider123", "CarRentals");
        userList1.addItem(driver1);
    }

    @Test
    public void constructorWithUsersTest(){
        Collection<AbstractUser> testUsers = new ArrayList<AbstractUser>();
        testUsers.add(driver2);
        testUsers.add(provider1);
        UserList testUserList = new UserList(testUsers);
        assertTrue(testUserList.getItemsList().contains(driver2));
        assertTrue(testUserList.getItemsList().contains(provider1));

    }

    @Test
    public void addUserTest() {
        assertEquals(1,userList1.getItemsList().size(), "It should only be one user in the list");
        userList1.addItem(driver2);
        assertEquals(2,userList1.getItemsList().size(), "It should be two user in the list");
        assertTrue(userList1.getItemsList().contains(driver2));   
    }

    @Test
    public void deleteUserTest() {
        assertTrue(userList1.getItemsList().contains(driver1));
        userList1.deleteItem(driver1);
        assertFalse(userList1.getItemsList().contains(driver1));
    }

}