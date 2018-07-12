package ru.job4j.trie;

import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.io.IOException;

public class TriePracticeTest {
    @Test
    public void whenTierThen() {
        TriePractice trie = new TriePractice();
        trie.add("lol");
        trie.add("hi");
        trie.add("ab");
        trie.add("ac");
        trie.add("ad");
        trie.add("previous");
        trie.add("daniil");
        trie.add("daniil");
        trie.add("daniil");
        trie.add("daniil");
        trie.add("dan");
        trie.add("dano");
        trie.add("danvos");
        trie.add("danvno");
    }
}