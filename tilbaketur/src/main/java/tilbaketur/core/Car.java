package tilbaketur.core;

/**
 * Car
 */
public class Car {

    private String carClass;
    private int seats;
    private String price;
    private Driver driver;

    public Car (String carClass, int seats, String price) {
        this.carClass = carClass;
        this.seats = seats;
        this.price = price;
    }

    public Car (String carClass, int seats, String price, Driver driver) {
        this.carClass = carClass;
        this.seats = seats;
        this.price = price;

        this.driver=driver;
    }

    public void setDriver(Driver driver) {
        this.driver=driver;
    }

    public Driver getDriver() {
        return driver;
    }


}