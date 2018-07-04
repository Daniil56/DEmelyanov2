package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleTreeTest {
    private Tree<Integer> tree;

    @Before
    public void setUp() {
        tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);


    }

    @Test
    public void when6ElFindLastThen6() {
        assertThat(tree.findBy(6).isPresent(), is(true));
    }

    @Test
    public void when7ElFindNotExitThenOptionEmpty() {
        assertThat(tree.findBy(7).isPresent(), is(false));
    }

    @Test
    public void whenTreeIsBinary() {
        assertThat(tree.isBinary(), is(false));
    }

    @Test
    public void whenTreeIterator() {
        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenTreeIteratorNoSuchelement() {
        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void wnhenTreeIteratorAddCocurrentMod() {
        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        tree.add(5, 7);
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));

    }
}