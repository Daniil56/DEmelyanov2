package ru.job4j.map;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String city;

     User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", city='" + city + '\'' + '}';
    }
}