package ru.job4j.loop;

/**
 * author Daniil Emelyanov
 * @version $id$
 * @since  13/03/2018
 */
public class Factorial {

    /**
     * Метод вычисления факториала n.
     *
     * @param n  входное значение
     * @return факториал от n
     */
    public int calc(int n) {
        int sum = 1;
        for (int val = 1; val <= n; val++) {
            if (n % 1 == 0) {
                sum = val * sum;
            }
            }
        return sum;
    }
}


