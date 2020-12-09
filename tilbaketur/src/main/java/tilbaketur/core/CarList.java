package tilbaketur.core;

import java.util.Collection;

public class CarList extends AbstractItemList<Car> {

    public CarList() {
        super();
    }

	public CarList(Collection<Car> cars) {
        super(cars);
	}
    
}