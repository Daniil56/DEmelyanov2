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
        boolean result = false;
        for (E value : list) {
            if (t.equals(value)) {
                result = true;
            }
        }
        return result;
    }

    public void add(E e) {
        if (isempty()) {
            count++;
            list.add(e);
        } else {
        if (!checkOfDuplicates(e)) {
            count++;
            list.add(e);
        }
        }
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
