package tilbaketur.core;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Driver
 */
public class Driver extends AbstractUser {
    Collection<Car> requestedCars = new ArrayList<>();

    public Driver(String username, String password, String name){
        super(username, password, name);
    }

    public void requestCar(Car car){
        requestedCars.add(car);
    }

    //TODO: should only be added when a request gets accepted
    public void addCars(Car car){
        //super.getCars().add(car);
    }

}