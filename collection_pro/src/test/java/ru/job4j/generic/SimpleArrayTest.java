package ru.job4j.generic;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;

public class SimpleArrayTest {

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenCrateContainerSholdBeReterunTheSameTypeAndArrayOut() {
        SimpleArray<Integer> simple = new SimpleArray<>(3);
        simple.add(10);
        assertThat(simple.get(0), is(10));
        simple.add(12);
        assertThat(simple.get(1), is(12));
        simple.add(13);
        assertThat(simple.get(2), is(13));
        simple.add(14);
    }

    @Test
    public void whenCreateCntainerSholdBeOpportunitySetValue() {
        SimpleArray<Integer> simple = new SimpleArray<>(3);
        simple.add(10);
        simple.add(12);
        simple.add(13);
        simple.set(1, 8);
        assertThat(simple.get(1), is(8));
    }

    @Test
    public void whenContainerIsCreatedItCanBeDeleted() {
        SimpleArray<String> simple = new SimpleArray<>(3);
        simple.add("A");
        simple.add("B");
        simple.add("C");
        simple.delete(2);
        assertThat(simple.iterator().next(), is("A"));
        assertThat(simple.iterator().next(), is("B"));
        assertThat(simple.iterator().hasNext(), is(false));
    }

}