package tilbaketur.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarListTest {

    CarList carList1;
    Driver driver1;
    Driver driver2;
    Car car1;
    Car car2;
    Car car3;
    Provider provider1;

    @BeforeEach
    public void setup() {
        carList1 = new CarList();
        driver1 = new Driver("driver1", "Driver1", "Driver One");
        driver2 = new Driver("driver2", "Driver2", "Driver Two");
        provider1 = new Provider("provider1", "Provider123", "CarRentals");
        car1 = new Car(provider1 ,"B", 5, "Free");
        car2 = new Car(provider1, "B", 5, "Fuel cost");
        car3 = new Car(provider1, "B", 4, "Free", driver2);

        carList1.addItem(car1);
        //carList1.addItem(car2);        
    }

    @Test
    public void constructorWithCarsTest(){
        Collection<Car> testCars = new ArrayList<Car>();
        testCars.add(car2);
        testCars.add(car3);
        CarList testCarsList = new CarList(testCars);
        assertTrue(testCarsList.getItemsList().contains(car2));
        assertTrue(testCarsList.getItemsList().contains(car3));

    }

    @Test
    public void addCarTest() {
        assertEquals(1,carList1.getItemsList().size(), "It should only be one car in the list");
        carList1.addItem(car2);
        assertEquals(2,carList1.getItemsList().size(), "It should be two car in the list");
        assertTrue(carList1.getItemsList().contains(car2));   
    }

    @Test
    public void deleteCarTest() {
        assertTrue(carList1.getItemsList().contains(car1));
        carList1.deleteItem(car1);
        assertFalse(carList1.getItemsList().contains(car1));
    }
}