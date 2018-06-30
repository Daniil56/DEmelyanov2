package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SimpleHashMapTest {
    private SimpleHashMap<String, Integer> map;

    @Before
    public void setUp() {
        map =  new SimpleHashMap<>();
        map.put("One", 1);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
    }

    @Test
    public void whenMapPutThenGetCorrect() {
        assertThat(map.put("One", 1), is(false));
        assertThat(map.put("four", 4), is(false));
        assertThat(map.put("three", 3), is(false));
        assertThat(map.put("five", 5), is(false));
        assertThat(map.put("six", 6), is(true));
        assertThat(map.get("five"), is(5));
        assertThat(map.get("One"), is(1));
        assertThat(map.get("three"), is(3));
        assertThat(map.get("four"), is(4));
        assertThat(map.get("six"), is(6));
        assertThat(map.getSize(), is(5));
        assertThat(map.delete("One"), is(true));
        assertThat(map.delete("On3e"), is(false));
        assertThat(map.getSize(), is(4));

    }

    @Test
    public void whenKeyIteratorsMap() {
        SimpleHashMap<String, Integer>.KeyIterator keyiterator;
        keyiterator = map.new KeyIterator();
        assertThat(keyiterator.hasNext(), is(true));
        assertThat(keyiterator.hasNext(), is(true));
        assertThat(keyiterator.next(), is("three"));
        assertThat(keyiterator.next(), is("four"));
        assertThat(keyiterator.next(), is("five"));
        assertThat(keyiterator.next(), is("One"));
        assertThat(keyiterator.hasNext(), is(false));
    }

    @Test
    public void whenValueIteratorsMap() {
        SimpleHashMap<String, Integer>.ValueIterator valueIterator;
        valueIterator = map.new ValueIterator();
        assertThat(valueIterator.hasNext(), is(true));
        assertThat(valueIterator.hasNext(), is(true));
        assertThat(valueIterator.next(), is(3));
        assertThat(valueIterator.next(), is(4));
        assertThat(valueIterator.next(), is(5));
        assertThat(valueIterator.next(), is(1));
        assertThat(valueIterator.hasNext(), is(false));
    }

    @Test
    public void whenEntryIteratorsMap() {
        SimpleHashMap<String, Integer>.EntryIterator entryIteratorIterator;
        SimpleHashMap<String, Integer>.HashIterator  hasIteratorIterator;
        entryIteratorIterator = map.new EntryIterator();
        hasIteratorIterator = map.new HashIterator();
        assertThat(entryIteratorIterator.hasNext(), is(true));
        assertThat(entryIteratorIterator.hasNext(), is(true));
        assertThat(entryIteratorIterator.next(), is(hasIteratorIterator.nextNode()));
        assertThat(entryIteratorIterator.next(), is(hasIteratorIterator.nextNode()));
        assertThat(entryIteratorIterator.next(), is(hasIteratorIterator.nextNode()));
        assertThat(entryIteratorIterator.next(), is(hasIteratorIterator.nextNode()));
        assertThat(entryIteratorIterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenMapIteratorNoSuchelement() {
        SimpleHashMap<String, Integer>.ValueIterator valueIterator;
        valueIterator = map.new ValueIterator();
        assertThat(valueIterator.hasNext(), is(true));
        assertThat(valueIterator.hasNext(), is(true));
        assertThat(valueIterator.next(), is(3));
        assertThat(valueIterator.next(), is(4));
        assertThat(valueIterator.next(), is(5));
        assertThat(valueIterator.next(), is(1));
        valueIterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenMapIteratorConcurentModification() {
        SimpleHashMap<String, Integer>.ValueIterator valueIterator;
        valueIterator = map.new ValueIterator();
        assertThat(valueIterator.hasNext(), is(true));
        assertThat(valueIterator.hasNext(), is(true));
        assertThat(valueIterator.next(), is(3));
        assertThat(valueIterator.next(), is(4));
        assertThat(valueIterator.next(), is(5));
        assertThat(valueIterator.next(), is(1));
        map.put("six", 6);
        valueIterator.next();
    }

}