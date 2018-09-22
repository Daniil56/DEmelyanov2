package lambda;

import ru.job4j.calculator.MathUtil;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Calculator {

    public void multiple(int start, int finish, int value,
                         BiFunction<Integer, Integer, Double> op,
                         Consumer<Double> media) {
        for (int index = start; index != finish; index++) {
           media.accept(op.apply(value, index));
        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.multiple(0, 10, 2, MathUtil::add, System.out::println);

    }
}
