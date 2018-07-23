package ru.job4j.array;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Equels {
    private String[] array;

    public Equels(String[] array) {
        this.array = array;
    }

    /**
     * Метод принимающий массив.
     * Создает хэш-карту, где ключами являются символы, значения - количество символов.
     * В первом цикле метод добавляет значения в карту если там их нет, со значением 1.
     * Если ключ символа уже есть, добавляет его значению 1, инкрементируя count.
     * Во втором цикле проверяет, что ключ символ есть в карте и вычитает из его значения единицу. Если ключа нет,
     * добавляет его со значением -1.
     * Вернет false, если в карте для хотя бы одного символа будут добавляно значение отличное от нуля.
     * @return является ли два слова аннограммами
     */
    public boolean checkInMap() {
        boolean result = true;
        Map<Character, Integer> mapchar = new HashMap<>();
        char[] word1 = array[0].toCharArray();
        char[] word2 = array[1].toCharArray();
        for (char c : word1) {
            int count = 1;
            if (mapchar.containsKey(c)) {
                count = mapchar.get(c) + 1;
            }
            mapchar.put(c, count);
        }
        for (char c : word2) {
            int count = -1;
            if (mapchar.containsKey(c)) {
                count = mapchar.get(c) - 1;
            }
            mapchar.put(c, count);
        }
        for (char c : mapchar.keySet()) {
            if (mapchar.get(c) != 0) {
                result = false;
            }
        }
        return result;

    }

    public boolean check() {
        char[] word1 = array[0].toCharArray();
        char[] word2 = array[1].toCharArray();
        Arrays.sort(word1);
        Arrays.sort(word2);
        return Arrays.equals(word1, word2);
    }
}