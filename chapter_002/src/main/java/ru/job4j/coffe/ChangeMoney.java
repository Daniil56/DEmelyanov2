package ru.job4j.coffe;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс выдачи сдачи кофемашиной.
 */
public class ChangeMoney {
    /**
     * Метод возвращающий массив купюр сдачи.
     * @param value номинал купюры.
     * @param price цена кофе.
     * @return массив купюр в качестве сдачи.
     */
    public int[] changes(int value, int price) {
        int[] oddMoney = new int[13];
        int difference = value - price;
        int[] size = Change.changes(value, price);
        int sum =0;

        for (int index = 0; index < oddMoney.length; index++) {
            if (difference != 0 && difference >= 1000) {
                oddMoney[index] = 1000;
                difference = difference - 1000;
            } else {
                if (difference != 0 && difference >= 500) {
                    oddMoney[index] = 500;
                    difference = difference - 500;
                } else {
                    if (difference != 0 && difference >= 100) {
                        oddMoney[index] = 100;
                        difference = difference - 100;
                    } else {
                        if (difference != 0 && difference >= 50) {
                            oddMoney[index] = 50;
                            difference = difference - 50;
                        } else {
                            if (difference != 0 && difference >= 10) {
                                oddMoney[index] = 10;
                                difference = difference - 10;
                            } else {
                                if (difference != 0 && difference >= 5) {
                                    oddMoney[index] = 5;
                                    difference = difference - 5;
                                } else {
                                    if (difference != 0 && difference >= 2) {
                                        oddMoney[index] = 2;
                                        difference = difference - 2;
                                    } else {
                                        if (difference != 0 && difference >= 1) {
                                            oddMoney[index] = 1;
                                            difference = difference - 1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int index = 0; index < Change.changes(value, price).length; index++) {
            sum = size[index] + sum;
        }
        return Arrays.copyOf(oddMoney, sum);
    }
}