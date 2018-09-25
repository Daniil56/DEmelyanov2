package ru.job4j.list;

import ru.job4j.user.User;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {

    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }

    public List<User> sortNameLength(List<User> users) {
        users.sort((o1, o2) -> {
            final boolean rs1 = o1.getName().length() == o2.getName().length();
            return rs1 ? 0 : o1.getName().length() > o2.getName().length() ? 1 : -1;
        });
        return users;
    }

    public List<User> sortByAllFields(List<User> users) {
        users.sort(Comparator.comparing(User::getName));
        return users;
    }
}