package tilbaketur.core;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstractUser contains the common methods for the Provider and the Driver class
 *
 */
public abstract class AbstractUser 
{
    private String name;
    private String username;
    //TODO: should use hashing s-256 here..
    private String password;
    private List<Car> cars = new ArrayList<>();

    public AbstractUser(String username, String password, String name) {
        this.username=username;
        this.password=password;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    protected void setPassword(String currentPassword, String newPassword) {
        if(this.password==currentPassword) this.password = newPassword;
    }

    public List<Car> getCars() {
        return cars;
    }

    public abstract void addCars(Car car);
}
