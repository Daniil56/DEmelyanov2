package ru.job4j.array;

public class ArrayStringCheck {
    int count;

    boolean contains(String origin, String sub) {
        boolean result = true;
        char[] word = origin.toCharArray();
        char[] value = sub.toCharArray();
            for (int j = 0; j < word.length; j++) {
                if (word[j] != value[j - count]) {
                    count++;
                    if (j == value.length - 1) {
                        result = false;
                        break;

                    }
                }
            }
        return result;
    }
    }
