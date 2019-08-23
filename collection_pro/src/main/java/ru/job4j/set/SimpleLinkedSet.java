package ru.job4j.set;

import ru.job4j.List.FirstLastList;

import java.util.Iterator;

public class SimpleLinkedSet<E> implements Iterable<E> {

    private transient FirstLastList<E> list = new FirstLastList<>();

    public int size() {
        return list.getSize();
    }

    public boolean add(E e) {
        boolean result = true;
        for (int index = 0; index < this.list.getSize(); index++) {
            if (e == null ? this.list.get(index) == null : e.equals(this.list.get(index))) {
                result = false;
                break;
            }
        }
        if (result) {
            this.list.add(e);
        }
        return true;
}

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}