package ru.job4j.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Equels<E> {
    private E[] array;

    public Equels(E[] array) {
        this.array = array;
    }

    public boolean check() {
        boolean result = false;
        Set<E> stringSet = new HashSet<>(Arrays.asList(this.array));
        if (stringSet.size() < this.array.length) {
            result = true;
        }

        return result;
    }
}
