package tilbaketur.core;

/**
 * Car
 */
public class Car {

    private Provider provider;
    private String carClass;
    private int seats;
    private String price;
    private Driver driver;


    public Car (Provider provider, String carClass, int seats, String price) {
        this.provider = provider;
        this.carClass = carClass;
        this.seats = seats;
        this.price = price;
    }

    public Car (Provider provider, String carClass, int seats, String price, Driver driver) {
        this.provider = provider;
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

    public Provider getProvider() {
        return provider;
    }


}