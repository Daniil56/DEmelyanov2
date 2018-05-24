package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator  {

    private final int[][] values;
    private int out = 0;
    private int in = 0;
    private int cells = 0;


    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {

        return values.length < out;
    }

    @Override
    public Object next() {
        int result = -1;
        int index = 0;
        for (int[] array : values) {
            cells = values.length % array.length == 1 ? array.length - 1 : array.length + 1;
        }
        result = values[out][in++];
            if (in == cells) {
                in = 0;
                out++;
                index++;
        } else {
                if (values[index + 1].length > values[index].length) {
                    result = values[out++][in];
                    while (values[index + 1].length < in) {
                        in++;
                    }
                }
            }
        return result;
    }
    }