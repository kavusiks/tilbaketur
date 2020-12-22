package tilbaketur.core;

public class Car {

  private Provider provider;
  private String carClass;
  private int seats;
  private String price;
  private Driver driver;

  /**
   * Used to create an car without assigned driver. Needed when a Provider is
   * adding a new car.
   *
   * @param provider of the car
   * @param carClass cartype in String 
   * @param seats amount of seats
   * @param price info about the price/fee
   */
  public Car(Provider provider, String carClass, int seats, String price) {
    this.provider = provider;
    this.carClass = carClass;
    this.seats = seats;
    this.price = price;
    // provider.addCars(this);
  }

  /**
   * Used to create an car with assigned driver. Is needed by deserialization of a
   * rented car.
   *
   * @param provider of the car
   * @param carClass cartype in String 
   * @param seats amount of seats
   * @param price info about the price/fee
   * @param driver currently assignned to the car
   */
  public Car(Provider provider, String carClass, int seats, String price, Driver driver) {
    this.provider = provider;
    this.carClass = carClass;
    this.seats = seats;
    this.price = price;
    // provider.addCars(this);

    this.driver = driver;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
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
    if (driver == null) {
      rider = "null";
    } else {
      rider = driver.toString();
    }
    return provider.toString() + carClass + seats + price + rider.toString();
  }

}