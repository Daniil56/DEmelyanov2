package ru.job4j.dif;

import java.util.ArrayList;
import java.util.List;

public class Info {
    private  List<Store.User> change = new ArrayList<>();

    @Override
    public String toString() {
        return "Info{" + "change=" + change.size() + '}';
    }

    public void add(Store.User store) {
        this.change.add(store);
    }


}
