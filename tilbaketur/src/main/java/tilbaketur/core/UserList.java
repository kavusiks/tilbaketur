package tilbaketur.core;

import java.util.Collection;

public class UserList extends AbstractItemList<AbstractUser> {

    public UserList() {
        super();
    }

	public UserList(Collection<AbstractUser> users) {
        super(users);
	}
    
}