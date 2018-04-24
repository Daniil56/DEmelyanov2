package ru.job4j.Coffe;
/**
 * Класс кофеавтомата.
 */
public class Automate {
    public int[] changes(int value, int price) {
        int difference = value - price;
        int size = (int) Math.ceil( 8- ( value/difference ) ); // столько монет понадобиться
        int[] odd = new int[size];


        for (int index = 0; odd.length > index; index++) {
            if (difference != 0 && difference >= 1000) {
                odd[index] = 1000;
                difference = difference - 1000;
            } else {
                if (difference != 0 && difference >= 500) {
                    odd[index] = 500;
                    difference = difference - 500;
                } else {
                    if (difference != 0 && difference >= 100) {
                        odd[index] = 100;
                        difference = difference - 100;
                    } else {
                        if (difference != 0 && difference >= 50) {
                            odd[index] = 50;
                            difference = difference - 50;
                        } else {
                            if (difference != 0 && difference >= 10) {
                                odd[index] = 10;
                                difference = difference - 10;
                            } else {
                                if (difference != 0 && difference >= 5) {
                                    odd[index] = 5;
                                    difference = difference - 5;
                                } else {
                                    if (difference != 0 && difference >= 2) {
                                        odd[index] = 2;
                                        difference = difference - 2;
                                    } else {
                                        if (difference != 0 && difference >= 1) {
                                            odd[index] = 1;
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

        return odd;
    }
}