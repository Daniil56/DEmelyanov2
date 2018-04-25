package ru.job4j.Coffe;

import java.util.Arrays;

/**
 * Класс кофеавтомата.
 */
public class Automate {
    public int[] changes(int value, int price) {
        int difference = value - price;
        int size = (int) Math.ceil( 10 - ( value/difference ) ); // столько монет понадобиться
        int[] odd = new int[size];
        int unique = size;


        for (int out = 0; unique > out; out++) {
            for (int in = 0; in < unique; in++) {
                if (difference != 0 && difference >= 1000) {
                    odd[in] = 1000;
                    difference = difference - 1000;
                } else {
                    if (difference != 0 && difference >= 500) {
                        odd[in] = 500;
                        difference = difference - 500;
                    } else {
                        if (difference != 0 && difference >= 100) {
                            odd[in] = 100;
                            difference = difference - 100;
                        } else {
                            if (difference != 0 && difference >= 50) {
                                odd[in] = 50;
                                difference = difference - 50;
                            } else {
                                if (difference != 0 && difference >= 10) {
                                    odd[in] = 10;
                                    difference = difference - 10;
                                } else {
                                    if (difference != 0 && difference >= 5) {
                                        odd[in] = 5;
                                        difference = difference - 5;
                                    } else {
                                        if (difference != 0 && difference >= 2) {
                                            odd[in] = 2;
                                            difference = difference - 2;
                                        } else {
                                            if (difference != 0 && difference >= 1) {
                                                odd[in] = 1;
                                                difference = difference - 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            } unique--;
        }

        return Arrays.copyOf(odd, unique);
    }
}