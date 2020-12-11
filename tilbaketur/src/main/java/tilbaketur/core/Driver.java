package tilbaketur.core;

/**
 * Driver
 */
public class Driver extends AbstractUser {
    Car requestedCar;

    public Driver(String username, String password, String name){
        super(username, password, name);
    }

    public void requestCar(Car car){
        requestedCar = car;
    }

    public Car getRequestedCar() {
        return requestedCar;
    }

    //TODO: should only be added when a request gets accepted
    public void addCars(Car car){
        //super.getCars().add(car);
    }

}