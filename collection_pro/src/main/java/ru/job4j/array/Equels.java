package ru.job4j.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Equels {
    private String[] array;

    public Equels(String[] array) {
        this.array = array;
    }

    public boolean check() {
        boolean result = false;
        Set<String> stringSet = new HashSet<>(Arrays.asList(this.array));
        if (stringSet.size() < this.array.length) {
            result = true;
        }

        return result;
    }
}
