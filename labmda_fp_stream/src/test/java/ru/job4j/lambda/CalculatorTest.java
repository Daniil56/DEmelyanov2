package ru.job4j.lambda;

import org.junit.Test;
import ru.job4j.calculator.MathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CalculatorTest {
    @Test
    public void whenAdd1Until3() {
        Calculator calc = new Calculator();
        List<Double> buffer = new ArrayList<>();
        calc.multiple(0, 3, 1, MathUtil::add, buffer::add);
        assertThat(buffer, is(Arrays.asList(1D, 2D, 3D)));
    }

}