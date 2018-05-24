package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator  {

    private final int[][] values;
    private int out = 0;
    private int in = 0;
    private int cells = 0;
    private int index = 0;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        if (index == values.length - 1) {
            index = 0;
        }
        return values[index++].length - 1  > index;
    }

    @Override
    public Object next() {
try {
    cells = values.length % values[out].length == 1 ? values[out].length - 1 : values[out].length;
} catch (NumberFormatException exception) {
    System.out.println("No such element");
}
        if (in == cells) {
            in = 0;
            out++;

        }
        return values[out][in++];

    }
    }