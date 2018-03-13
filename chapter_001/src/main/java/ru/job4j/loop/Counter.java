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
     * @param start  первое число диапозона
     * @param finish второе число диапозона
     * @return Сумма четных чисел в дипозоне от start до finish
     */
    public int add(int start, int finish) {
        for (int count = start; count <= finish; count++) {
            if (count % 2 == 0) {
                sum =  count + sum;
            }
        }
        return sum;
    }
}