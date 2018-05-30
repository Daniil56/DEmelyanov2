package ru.job4j.iterator;

import java.util.Iterator;

public class EvenIterator implements Iterator {
    private int[] array;
    private int index = 0;
    private int count = 0;

    public EvenIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (count < array.length - 1) {
            count++;
            if (this.array[this.count] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            try {
                throw new NoSuchFieldException("np such even element");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return this.array[this.count++];
    }
}