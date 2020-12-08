package tilbaketur.core;

/**
 * ProviderUser
 */
public class Provider extends AbstractUser {

    public Provider(String username, String password, String name){
        super(username, password, name);
    }
    
    
    public void addCars(Car car){
        super.getCars().add(car);
    }
}