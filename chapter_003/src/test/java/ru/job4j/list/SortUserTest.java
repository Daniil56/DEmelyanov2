package ru.job4j.list;

import org.junit.Test;
import ru.job4j.user.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUserTest {
    @Test
    public void whenListThenSet() {
        SortUser user = new SortUser();
        List<User> users = new ArrayList<>();
        Set<User> expectSet = new TreeSet<>();
        Set<User> actual;
        User daniil = new User("Daniil", 24);
        User ivan = new User("Ivan", 30);
        User anton = new User("Anton", 20);
        User ban = new User("Ban", 18);
        users.add(daniil);
        users.add(ivan);
        users.add(anton);
        users.add(ban);
        expectSet.addAll(users);
        actual = user.sort(users);
        assertThat(actual, is(expectSet));
    }

}