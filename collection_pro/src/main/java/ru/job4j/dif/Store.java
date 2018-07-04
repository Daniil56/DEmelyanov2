package ru.job4j.dif;

import java.util.List;

public class Store {

    Info diff(List<User> previoues, List<User> current) {
        Info info = new Info();
        for (User list : previoues) {
            for (User listcur : current) {
                if ((list.name.equals(listcur.name))) {
                    info.add(listcur);
                }
            }

        }
        return info;
    }

    static class User {
        int id;
        String name;


        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}