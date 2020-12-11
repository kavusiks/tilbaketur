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
        //provider.addCars(this);
    }

    public Car (Provider provider, String carClass, int seats, String price, Driver driver) {
        this.provider = provider;
        this.carClass = carClass;
        this.seats = seats;
        this.price = price;
        //provider.addCars(this);

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

    public int getSeats() {
        return seats;
    }

    public String getPrice() {
        return price;
    }

    public String getCarClass() {
        return carClass;
    }

    @Deprecated
    @Override
    public String toString() {
        String rider;
        if(driver==null)rider="null";
        else rider=driver.toString();
        return provider.toString() + carClass + seats+ price+ rider.toString();
    }


}