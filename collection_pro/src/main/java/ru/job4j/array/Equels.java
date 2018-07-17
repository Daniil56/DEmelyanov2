package ru.job4j.array;


import java.util.Arrays;


public class Equels {
    private String[] array;

    public Equels(String[] array) {
        this.array = array;
    }

    public boolean check() {
        char[] word1 = array[0].toCharArray();
        char[] word2 = array[1].toCharArray();
        Arrays.sort(word1);
        Arrays.sort(word2);
        return Arrays.equals(word1, word2);
    }
}