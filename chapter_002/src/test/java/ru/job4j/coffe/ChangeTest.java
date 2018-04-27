package ru.job4j.coffe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ChangeTest {
    @Test
    public void whenChangeGet11() {
        int[] expect = new int[] {0,  0, 0, 0, 1, 1, 0, 0};
        assertThat(Change.changes(50, 35
        ), is(expect));
    }

}