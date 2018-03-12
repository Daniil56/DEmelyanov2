package ru.job4j.max;

/**
 * author Daniil Emelyanov
 * @version $id$
 * @since  12/03/2018
 */
public class Max {
    /**
     * Отвечает на вопросы.
     * @param first первое значение
     * @param second второе значение
     * @return наибольшое значение.
     */
    public int max(int first, int second) {
        return  first > second ? first : second;
    }
}