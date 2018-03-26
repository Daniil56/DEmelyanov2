package ru.job4j.array;

/**
 * Даны 2 массива оба отсортированы по возрастанию необходимо добавить третий массив.
 * что бы в третьем массиве все элементы были отсортированы по возрастанию,
 */

public class ArrayThreeSort {
    public int[] threeIsSort(int[] array1, int[] array2) {
        int [] array3 = new int[array1.length + array2.length];
        int array1Index = 0;
        int array2Index = 0;

        for (int i = 0; i < array3.length; i++) {
            if (array1Index > array1.length - 1 ) {
                int buffer = array2[array2Index];
                array3[i] = buffer;
                array2Index++;
            } else if (array2Index > array2.length - 1 ) {
                int buffer = array1[array1Index];
                array3[i] = buffer;
                array1Index++;
            } else if (array1[array1Index] <= array2[array2Index]) {
                int buffer = array1[array1Index];
                array3[i] = buffer;
                array1Index++;
            } else if (array2[array2Index] <= array1[array1Index]) {
                int buffer = array2[array2Index];
                array3[i] = buffer;
                array2Index++;
            }
        }
        return array3;
    }
}