package tilbaketur.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;

import tilbaketur.core.AbstractUser;
import tilbaketur.core.Car;
import tilbaketur.core.CarList;
import tilbaketur.core.Driver;
import tilbaketur.core.Provider;
import tilbaketur.core.UserList;

public abstract class AbstractPersistenceTest {

    protected static ObjectMapper mapper;
    protected static Driver driver1 = new Driver("driver1", "Driver1", "Driver One");
    protected static Driver driver2 = new Driver("driver2", "Driver2", "Driver Two");
    protected String driver1CorrectFormat = "{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null}";
    protected static Provider provider1 = new Provider("provider1", "Provider123", "CarRentals");
    protected String providerCorrectFormat = "{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"}";
    protected static Car car1 = new Car(provider1, "B", 5, "Free");
    protected static Car car2 = new Car(provider1, "B", 5, "Fuel cost", driver1);
    protected String car1CorrectFormat = "{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Free\",\"driver\":null}";
    protected String car2CorrectFormat = "{\"provider\":{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"},\"carClass\":\"B\",\"seats\":5,\"price\":\"Fuel cost\",\"driver\":{\"name\":\"Driver One\",\"username\":\"driver1\",\"password\":\"Driver1\",\"requestedCar\":null}}";

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new TilbaketurModule());
    }



    protected void checkTwoCars(Car car1, Car car2) {
        checkTwoProviders(car1.getProvider(), car2.getProvider());
        assertEquals(car1.getCarClass(), car2.getCarClass());
        assertEquals(car1.getSeats(), car2.getSeats());
        assertEquals(car1.getPrice(), car2.getPrice());
        // TODO:Fikse denne
        checkTwoDrivers(car1.getDriver(), car2.getDriver());

    }

    // TODO: vurder behov for å ha to ulike metoder for provider og driver
    protected void checkTwoProviders(Provider provider1, Provider provider2) {
        String ifFailed = "Not the same providers.";
        assertEquals(provider1.getName(), provider2.getName(),ifFailed);
        assertEquals(provider1.getUsername(), provider2.getUsername(), ifFailed);
        assertEquals(provider1.getPassword(), provider2.getPassword(), ifFailed);
        assertEquals(provider1.getCars(), provider2.getCars(), ifFailed);
    }

    protected void checkTwoDrivers(Driver driver1, Driver driver2) {
        String ifFailed = "Not the same drivers.";
        if(driver1==null){
            assertNull(driver2);
        }
        else if(driver2==null && driver1!=null) fail("The second driver is null, but the first isn't.");
        else{
            assertEquals(driver1.getName(),driver2.getName(), ifFailed);
            assertEquals(driver1.getUsername(), driver2.getUsername(), ifFailed);
            assertEquals(driver1.getPassword(), driver2.getPassword(),ifFailed);
            assertEquals(driver1.getCars(), driver2.getCars(),ifFailed);
        }
    }

    protected void checkTwoUserLists(UserList userList1, UserList userList2) {
        //TODO: vurderer å implementere iterator i userListen
        String ifFailed = "Not the same userLists";
        List<AbstractUser> userList1Drivers = userList1.getItemsList().stream()
                                            .filter(user -> user instanceof Driver)
                                            .collect(Collectors.toList());
                                            
        List<AbstractUser> userList1Providers = userList1.getItemsList().stream()
                                            .filter(user -> user instanceof Provider)
                                            .collect(Collectors.toList());

        List<AbstractUser> userList2Drivers = userList2.getItemsList().stream()
                                            .filter(user -> user instanceof Driver)
                                            .collect(Collectors.toList());
                                            
        List<AbstractUser> userList2Providers = userList2.getItemsList().stream()
                                            .filter(user -> user instanceof Provider)
                                            .collect(Collectors.toList());

        //verifying correct amount of drivers in the individual list:
        assertEquals(userList1Drivers.size(), userList2Drivers.size(), ifFailed);
        //verifying correct amount of providers in the individual list:
        assertEquals(userList1Providers.size(), userList2Providers.size(), ifFailed);
        
        //verifying that the indvidual drivers in each list are the same in identical order
        for(int i=0; i<userList1Drivers.size(); i++) {
            checkTwoDrivers((Driver) userList1Drivers.get(i), (Driver) userList2Drivers.get(i));
        }

        //verifying that the indvidual providers in each list are the same in identical order
        for(int i=0; i<userList1Providers.size(); i++) {
            checkTwoProviders((Provider) userList1Providers.get(i), (Provider) userList2Providers.get(i));
        }
    }

     protected void checkTwoCarLists(CarList carList1, CarList carList2) {
        String ifFailed = "Not the same carLists";
        List<Car> carList1Cars = carList1.getItemsList().stream()
                                            .collect(Collectors.toList());
        List<Car> carList2Cars = carList2.getItemsList().stream()
                                            .collect(Collectors.toList());

        //verifying correct amount of cars in the individual list:
        assertEquals(carList1Cars.size(), carList2Cars.size(), ifFailed);
        
        //verifying that the indvidual cars in each list are the same in identical order
        for(int i=0; i<carList1Cars.size(); i++) {
            checkTwoCars(carList1Cars.get(i), carList2Cars.get(i));
        }
    }
    

}