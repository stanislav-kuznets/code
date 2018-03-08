package ru.otus.skuznets;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private int size;
    private Object[] o;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this.o = new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        throw new NotImplementedException();
    }

    public Iterator<T> iterator() {
        throw new NotImplementedException();
    }

    public String toString() {
    StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
        sb.append(o[i]);
        sb.append(", ");
        }
        return sb.toString();
    }

    public Object[] toArray() {
        return o.clone();
    }

    public void checkRange(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    public T get(int index) {
        checkRange(index);
        return (T) o[index];
    }

    public T set(int index, T element) {
        checkRange(index);
        T value = (T) o[index];
        o[index] = element;
        return value;
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new NotImplementedException();
    }

    public boolean add(T t) {
        size++;
        T[] t1 = (T[]) new Object[size];
        for (int i = 0; i < size - 1; i ++) {
            t1[i] = (T) o[i];
        }
        t1[size - 1] = t;
        o = t1;
        return false;
    }

    public ListIterator<T> listIterator() {
        return new MyArrayListIterator();
    }

    public ListIterator<T> listIterator(int index) {
        throw new NotImplementedException();
    }

    private class MyArrayListIterator implements ListIterator<T> {
        int cursor;
        int lastIndex = -1;

        public boolean hasNext() {
            return cursor != size;
        }

        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            cursor++;
            return (T) o[lastIndex = cursor - 1] ;
        }

        public void set(T t) {
            if(lastIndex < 0) throw new IllegalStateException();
            MyArrayList.this.set(lastIndex, t);
        }

        public boolean hasPrevious() {
            return false;
        }

        public T previous() {
            return null;
        }

        public int nextIndex() {
            return 0;
        }

        public int previousIndex() {
            return 0;
        }

        public void remove() { throw new NotImplementedException(); }

        public void add(T t) { throw new NotImplementedException(); }
    }

    public boolean remove(Object o) {
        throw new NotImplementedException();
    }

    public boolean containsAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public boolean addAll(Collection<? extends T> c) {
        throw new NotImplementedException();
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        throw new NotImplementedException();
    }

    public boolean removeAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public void clear() {
        throw new NotImplementedException();
    }

    public void add(int index, T element) {
        throw new NotImplementedException();
    }

    public T remove(int index) {
        throw new NotImplementedException();
    }

    public int indexOf(Object o) {
        throw new NotImplementedException();
    }

    public int lastIndexOf(Object o) {
        throw new NotImplementedException();
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new NotImplementedException();
    }

}
