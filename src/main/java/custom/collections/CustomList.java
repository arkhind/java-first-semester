// CustomList.java
package custom.collections;

import java.util.Iterator;

public interface CustomList<T> extends Iterable<T> {
    boolean add(T element);
    T get(int index);
    T remove(int index);
    int size();
    boolean isEmpty();
}