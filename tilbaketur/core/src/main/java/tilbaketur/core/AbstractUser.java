package tilbaketur.core;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstractUser contains the common methods for the Provider and the Driver
 * class.
 *
 */
public abstract class AbstractUser {
  private String name;
  private String username;
  // TODO: should use hashing s-256 here..
  private String password;
  private List<Car> cars = new ArrayList<>();

  /**
   * Constructor for creating a Driver or a Provider.
   *
   * @param username Unique username
   * @param password Choosen password
   * @param name Driver or Providers name
   */
  public AbstractUser(String username, String password, String name) {
    this.username = username;
    this.password = password;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getUsername() {
    return username;
  }

  //TODO:
  /**
   * User to change users password. Should not be public method.
   *
   * @param currentPassword password to change from
   * @param newPassword passward to change to
   * @throws IllegalArgumentException when currentPassword does 
   *                                  not match with the users current password
   */
  public void setPassword(String currentPassword, String newPassword)
      throws IllegalArgumentException {
    if (this.password.equals(currentPassword)) {
      this.password = newPassword;
    } else {
      throw new IllegalArgumentException("Wrong currentPassword");
    }
  }

  public List<Car> getCars() {
    return cars;
  }

  // TODO: should not be used
  public String getPassword() {
    return password;
  }

  public abstract void addCars(Car car);

  @Deprecated
  @Override
  public String toString() {
    return name + " " + username + " " + password + " " + cars.toString();
  }
}
