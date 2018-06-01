package ru.job4j.generic;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int index = 0;
    private int count = 0;


    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T model) {
        if (index >= objects.length) {
            throw new ArrayIndexOutOfBoundsException("Array overflow, adding a new element is impossible.");
        }
        this.objects[index++] = model;
    }

    public T get(int position) {
        return (T) this.objects[position];
    }

    public void set(int position, T model) {
        this.objects[position] = model;
    }

    public void delete(int position) {
        this.objects[position] = null;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return objects[count] != null;
            }

            @Override
            public T next() {
                if (count >= objects.length) {
                    throw new NoSuchElementException("no such element");
                }
                return (T) objects[count++];
            }
        };
    }
}
