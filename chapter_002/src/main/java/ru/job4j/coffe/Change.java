package ru.job4j.coffe;

/**
 * Класс размены  купюр.
 */

public  class Change {
    /**
     * Метод возвращающий массив количества каждой купюры.
     * где:
     * odd[0] количество купюр номиналом 1000.
     * odd[1] количество купюр номиналом 500.
     * odd[2] количество купюр номиналом 100.
     * odd[3] количество купюр номиналом 50.
     * odd[4] количество монет номиналом 10.
     * odd[5] количество монет номиналом 5.
     * odd[6] количество монет номиналом 2.
     * odd[7] количество монет номиналом 1.
     * @param value номинал купюры.
     * @param price цена кофе.
     * @return массив количества купюр.
     */
    public static int[] changes(int value, int price) {
    int[] odd = new int[8];
    int[] bank = new int[] {1000, 500, 100, 50, 10, 5, 2, 1};
    int diff = value - price;

    for (int index = 0; index < odd.length; index++) {
        while (diff != 0 && diff >= bank[index]) {
            odd[index] += 1;
            diff -= bank[index];
        }
    }
        return odd;
    }

}
