package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static  org.hamcrest.Matchers.is;

public class EquelsTest {
    @Test
    public void whenArrayWorldCompare2World() {
        Equels eq = new Equels(new String[] {"ma", "mama"});
        assertThat(eq.check("ma"), is(true));
    }

}