package ru.job4j.coffe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ChangeMoneyTest {
    @Test
    public void when100Get75() {
        ChangeMoney auto = new ChangeMoney();
        int[] expect = new int[] {50,  10, 10, 5};
        assertThat(auto.changes(100, 25
        ), is(expect));
    }

}