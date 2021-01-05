package tilbaketur.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class DriverTest {

    static Driver testDriver;

    @BeforeAll
    public static void setUp() {
        testDriver = new Driver("testUser1", "testPassword123", "Test Testersen");
    }
    
    @Test
    public void initialUserDataTest()
    {
        assertEquals("testUser1",testDriver.getUsername());
        assertEquals("Test Testersen", testDriver.getName());
    }

    @Test
    public void getCarsTest(){
        assertEquals(0,testDriver.getCars().size());
    }
}
