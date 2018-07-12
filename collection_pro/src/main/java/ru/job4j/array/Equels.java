package ru.job4j.array;

import ru.job4j.trie.AchoCorasic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
public class Equels<E> {
    private String[] array;

    public Equels(String[] array) {
        this.array = array;
    }

    public boolean check(String s) {
        boolean result;
        AchoCorasic trie = new AchoCorasic(10);
        for (String anArray : this.array) {
            trie.add(anArray);
        }
        result = trie.isWord(s);
        return result;
    }
}