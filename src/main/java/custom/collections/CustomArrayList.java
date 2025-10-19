package custom.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomArrayList<T> implements CustomList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_FACTOR = 1.5;
    private Object[] elements;
    private int size;

    public CustomArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * RESIZE_FACTOR);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Null arguments are not supported in this implementation.");
        }
        ensureCapacity();
        elements[size++] = element;
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        @SuppressWarnings("unchecked")
        T element = (T) elements[index];
        return element;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        @SuppressWarnings("unchecked")
        T removedElement = (T) elements[index];

        if (index < size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        }

        elements[--size] = null;
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    private class CustomIterator implements Iterator<T> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            @SuppressWarnings("unchecked")
            T nextElement = (T) elements[cursor];
            cursor++;
            return nextElement;
        }
    }
}