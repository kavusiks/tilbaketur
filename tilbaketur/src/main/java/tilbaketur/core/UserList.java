package tilbaketur.core;

import java.util.Collection;
import java.util.Iterator;

public class UserList extends AbstractItemList<AbstractUser> implements Iterable<AbstractUser> {

    public UserList() {
        super();
    }

	public UserList(Collection<AbstractUser> users) {
        super(users);
	}

    @Override
    public Iterator<AbstractUser> iterator() {
        // TODO Vurder behovet for denne metoden
        return (Iterator<AbstractUser>) this.getItemsList();
    }
    
}