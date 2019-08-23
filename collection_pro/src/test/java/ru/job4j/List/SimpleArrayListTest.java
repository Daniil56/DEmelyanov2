package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.List.SimpleArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void setUp() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenAddThreelementsThenUseGetSizeResultThree() {
        list.delete();
        assertThat(list.getSize(), is(2));
    }

    @Test
    public void whenDeleteElement() {
        list.delete();
        assertThat(list.getSize(), is(2));
        assertThat(list.get(0), is(2));
    }
}