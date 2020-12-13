package tilbaketur.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * UserList
 */
public abstract class AbstractItemList<T> {

    private Collection<T> items;

    public AbstractItemList() {
        items = new ArrayList<T>();
    }

    public AbstractItemList(Collection<T> items) {
        this.items = items;
    }

    public void addItem(T item) {
        items.add(item);
    }

    public void deleteItem(T item) {
        items.remove(item);
    }

    @Deprecated
    public Collection<T> getItemsList() {
        return items;
    }

    public Iterator<T> getIterator() {
        return items.iterator();
    }



}