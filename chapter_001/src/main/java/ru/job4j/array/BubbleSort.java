package ru.job4j.array;

/**
 * Класс пузырьковой сортировки.
 */
public class BubbleSort {
    /**
     * Метод пузырьковой сортировки.
     * @param array входящий массив чисел.
     * @return отсортированный по возврастанию массив.
     */
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int out = array.length - 1; out > i; out--) {
                if (array[out - 1] > array[out]) {
                    int temp = array[out - 1];
                    array[out - 1] = array[out];
                    array[out] = temp;
                }
            }
        }
        return array;
    }
}

