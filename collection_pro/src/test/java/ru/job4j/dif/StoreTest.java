package ru.job4j.dif;


import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreTest {
    private List<Store.User> previous;
    private List<Store.User> current;
    private Store.User user1 = new Store.User(1, "Ivan");
    private Store.User user2 = new Store.User(2, "Daniil");
    private Store.User user3 = new Store.User(3, "Sanya");
    private Store.User user4 = new Store.User(1, "Oleg");
    private  Store.User user6 = new Store.User(4, "Yana");
    private Store.User user7 = new Store.User(5, "Yan");
    private Store.User user8 = new Store.User(5, "Alex");


    @Before
    public void setUp() {
      previous = new ArrayList<>();
      current = new ArrayList<>();

    }

    @Test
    public void whenTest() {
        previous.add(user1);
        previous.add(user2);
        previous.add(user3);
        previous.add(user7);
        current.add(user4);
        current.add(user2);
        current.add(user3);
        current.add(user6);
        Store store = new Store();
        Info expected = new Info();
        expected.add("Changes", 1);
        expected.add("Added", 1);
        expected.add("Deleted", 1);
        System.out.println(store.diff(previous, current));
        assertThat(store.diff(previous, current), is(expected));


    }

}