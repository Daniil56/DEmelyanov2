package ru.job4j.array;
/**
 * 	Дан массив надо проверить что он отсортирован.
 */

public class ArraySortCheck {
    public  boolean sort(int[] array, boolean reserved) {
        if (reserved == false) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i + 1] < array[i]) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < array.length - 1; i++) {

                if (array[i + 1] > array[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}