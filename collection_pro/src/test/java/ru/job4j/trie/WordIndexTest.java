package ru.job4j.trie;

import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;

public class WordIndexTest {
    @Test
    public void whenFIledLoadThenWordsPrint() throws IOException {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(4);
        set.add(5);
        WordIndex word = new WordIndex();
        word.loadFile("C:\\Java course\\DEmelyanov2\\collection_pro\\input.txt");
        assertThat(word.getIndexes4Word("daniil"), is(set));
        assertThat("dan".indexOf("i"), is(-1));

    }
}