package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
public class HashTableTest {
    private HashTable<String> hashTable;

    @Before
    public void setUp() {
        hashTable = new HashTable(10);
        hashTable.add("one");
        hashTable.add("two");
        hashTable.add("three");
        hashTable.add("four");
        hashTable.add("five");
        hashTable.add("six");
        hashTable.add("seven");
        hashTable.add("eight");
        hashTable.add("nine");
       // hashTable.add("ten");
       // hashTable.add("eleevenn");

    }

    @Test
    public void whentHashTableSize() {
        assertThat(hashTable.size(), is(9));
    }

    @Test
    public void whenHashTableDelete() {
        assertThat(hashTable.remove("one"), is(true));
        assertThat(hashTable.remove("one"), is(false));
    }

    @Test
    public void voidWhenHashTableContains() {
        assertThat(hashTable.conrains("one"), is(true));
        hashTable.remove("one");
        assertThat(hashTable.conrains("one"), is(false));
    }

}