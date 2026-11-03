package org.example.collection;

public interface MyList<T> {
    int size();

    boolean add(T e);

    boolean addFirst(T e);

    boolean set(int index, T e);

    T get(int index);

    boolean remove(T o);

    boolean remove(int index);
}