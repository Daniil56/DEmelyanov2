package ru.job4j.trie;

import static org.junit.Assert.assertThat;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class TrieTest {

    @Test
    public void whenTierThen() {
        Trie trie = new Trie();
        trie.insert("lol");
        trie.insert("hi");
        trie.insert("previous");
        trie.insert("daniil");
        trie.insert("daniil");
        trie.insert("daniil");
        trie.insert("daniil");
        trie.insert("dan");
        trie.insert("dano");
        trie.insert("danvos");
        trie.insert("danvno");
        assertThat(trie.exists("daniil"), is(true));
    }


}