package ru.job4j.departaments;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SortDepartamentsTest {
    private SortDepartaments sort;

    @Before
    public void initialize() {
        this.sort = new SortDepartaments(new String[]{
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1"
        });
        }

        @Test
    public void whenSort() {
        String[] actual = this.sort.sort();
        String[] expected = new String[] {
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        };
        assertThat(actual, is(expected));
    }

    @Test
    public void whenReverse() {
        String[] actual = this.sort.reverse();
        String[] expected = new String[]{
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"
        };
        assertThat(actual, is(expected));
        }
    }