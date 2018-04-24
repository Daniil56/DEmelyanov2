package ru.job4j.Coffe;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class AutomateTest {
    @Test
    public void when100Get75() {
        Automate auto = new Automate();
        int[] expect = new int[] {50,  10, 10, 5};
        assertThat(auto.changes(100, 25), is(expect));
    }
}