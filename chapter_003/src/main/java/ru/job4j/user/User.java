package ru.job4j.user;


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
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", city='" + city + '\'' + '}';
    }

    @Override
    public int compareTo(User o) {
      int dif = (this.age - o.age);
      if (dif == 0) {
          return 0;
      }
      if (dif > 0) {
          return 1;
      }
      if (dif < 0) {
          return -1;
      }
        return dif;
    }
}