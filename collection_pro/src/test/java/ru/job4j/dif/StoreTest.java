package ru.job4j.dif;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreTest {

    @Test
    public void whenTest() {
        Store.User user1 = new Store.User(1, "Ivan");
        Store.User user2 = new Store.User(2, "Daniil");
        Store.User user3 = new Store.User(1, "Oleg");
        Store.User user4 = new Store.User(2, "Daniil");
        List<Store.User> previous = new ArrayList<>();
        List<Store.User> current = new ArrayList<>();
        previous.add(user1);
        previous.add(user2);
        current.add(user3);
        current.add(user4);
        Store store = new Store();
        Info expected = new Info();
        expected.add(user3);
      //  assertThat(store.diff(previous, current), is(expected));


    }

}