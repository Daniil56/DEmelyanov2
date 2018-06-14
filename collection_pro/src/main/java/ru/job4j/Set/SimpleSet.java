package ru.job4j.set;



import ru.job4j.container.Dynamic;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<E> implements Iterable<E> {

    private transient Dynamic<E> list = new Dynamic<>();
    int index = 0;
    private  int count = 0;

    public int size() {
        return list.size();
    }

    public boolean isempty() {
        return list.isEmpty();
    }

    public boolean checkOfDuplicates(E t) {
        boolean result = true;
        for (E value : this.list) {
            if (value.equals(t)) {
                result = false;
            }
        }
        return result;
    }

    public boolean add(E e) {
        //boolean result = true;
        for (E check: this.list) {
            if (check.equals(e)) {
                return false;
            }
        }
        this.list.add(e);
        count++;
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return count > index;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("no such");
                }
                return list.get(index++);
            }
        };
    }
}
