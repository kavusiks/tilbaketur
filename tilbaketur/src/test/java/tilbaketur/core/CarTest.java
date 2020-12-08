package tilbaketur.core;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * CarTest
 */
public class CarTest {
    static Driver driver1;
    static Driver driver2;
    static Car car1;
    Car car2;

    @BeforeAll
    public static void setUp() {
        driver1 = new Driver("driver1", "Driver1", "Driver One");
        driver2 = new Driver("driver2", "Driver2", "Driver Two");
        car1 = new Car("B", 5, "Free");
    }

    @Test
    public void getDriverTest() {
        assertNull(car1.getDriver(),"No drivers should been assigned!");
        car1.setDriver(driver1);
        assertSame(driver1, car1.getDriver());
    }
}