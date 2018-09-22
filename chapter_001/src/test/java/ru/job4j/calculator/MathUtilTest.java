package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MathUtilTest {
    @Test
    public void whenAddOnePlusOneThenTwo() {
        MathUtil calc = new MathUtil();
        MathUtil.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
    @Test

    public void whenSubtractOneMinusOneThenTwo() {
        MathUtil calc = new MathUtil();
        calc.subtract(4d, 3d);
        double result = calc.getResult();
        double expected = 1;
        assertThat(result, is(expected));
    }
    @Test

    public void whenDivOneDivOneThenTwo() {
        MathUtil calc = new MathUtil();
        calc.div(10D, 2D);
        double result = calc.getResult();
        double expected = 5D;
        assertThat(result, is(expected));
    }
    @Test

    public void whenMulOneMulOneThenTwo() {
        MathUtil calc = new MathUtil();
        calc.multiple(10D, 2D);
        double result = calc.getResult();
        double expected = 20D;
        assertThat(result, is(expected));
    }
}