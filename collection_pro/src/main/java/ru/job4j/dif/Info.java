package ru.job4j.dif;

import java.util.*;

public class Info {
    private Map<String, Integer> change = new TreeMap<>();



    public void add(String key, Integer store) {
        this.change.put(key, store);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Info info = (Info) o;

        return change != null ? change.equals(info.change) : info.change == null;
    }

    @Override
    public int hashCode() {
        return change != null ? change.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Collection statistics :" + change;
    }
}
