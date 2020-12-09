package tilbaketur.core;

import java.util.ArrayList;
import java.util.Collection;

/**
 * UserList
 */
public abstract class AbstractItemList<Object> {

    private Collection<Object> items;

    public AbstractItemList() {
        items = new ArrayList<Object>();
    }

    public AbstractItemList(Collection<Object> items) {
        this.items = items;
    }

    public void addItem (Object item) {
        items.add(item);
    }

    public void deleteItem(Object item) {
        items.remove(item);
    }

    public Collection<Object> getItemsList() {
        return items;
    }

}