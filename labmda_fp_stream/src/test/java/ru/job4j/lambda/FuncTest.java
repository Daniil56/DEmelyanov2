package ru.job4j.lambda;

import org.junit.Test;
import ru.job4j.lambda.Func;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FuncTest {
    private final Func calc = new Func();

    @Test
    public void whenLinerFuncrion() {
        int start = 1;
        int end = 5;
        List<Double> result = calc.diapason(start, end, (x) -> end * x + start);
        assertThat(result, is(Arrays.asList(6D, 11D, 16D, 21D, 26D)));
    }

    @Test
    public void whenFunctionQuadratic() {
        List<Double> result = calc.diapason(1, 5, (x) -> Math.pow(x, 2));
        assertThat(result, is(Arrays.asList(1D, 4D, 9D, 16D, 25D)));
    }

    @Test
    public void whenLogarithmicFunction() {
        List<Double> result = calc.diapason(1, 5, Math::log);
        assertThat(result, is(Arrays.asList(0.0, 0.6931471805599453, 1.0986122886681098, 1.3862943611198906, 1.6094379124341003)));
    }
}