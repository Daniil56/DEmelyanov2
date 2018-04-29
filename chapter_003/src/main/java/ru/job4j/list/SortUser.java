package ru.job4j.list;

import ru.job4j.user.User;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {

    public Set<User> sort(List<User> users) {
        Set<User> sort = new TreeSet<>();
        sort.addAll(users);
        return sort;
    }
}