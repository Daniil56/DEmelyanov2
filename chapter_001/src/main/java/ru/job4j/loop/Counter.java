package ru.job4j.loop;

/**
 * author Daniil Emelyanov
 * @version $id$
 * @since  13/03/2018
 */
public class Counter {
    private int sum;
    /**
     * Метод вычисления суммы четных чисел в диапозоне.
     *
     * @param start первое число диапозона
     * @param finish второе число диапозона
     * @return Сумма четных чисел в дипозоне от start до finish
     */
    public int add(int start, int finish) {
        for (int count = 0; count < finish; count++) {
            if (count % 2 == 0) {
                sum = sum + count + 2;
            }
        }
        return sum;
    }
}