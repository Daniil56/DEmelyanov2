package ru.job4j.user;


import java.util.Objects;

public class User implements Comparable<User> {
    private int id;
    private String name;
    private String city;
    private int age;

     public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return String.format("%s, %d", name, age);
    }

    @Override
    public int compareTo(User o) {
      int dif = (this.age - o.age);
        return Integer.compare(dif, 0);
    }
}