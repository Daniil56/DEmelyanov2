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
        set.add(6);
        set.add(9);
        set.add(11);
        set.add(13);
        WordIndex word = new WordIndex();
        word.loadFile("C:\\Java course\\DEmelyanov2\\collection_pro\\input_with_split.txt");
       assertThat(word.getIndexes4Word("dan"), is(set));
    }

    @Test
    public void whenFileWithoutSplitLoadWhenSeachAhoCorasic() throws IOException {
        Set<Integer> set = new HashSet<>();
        set.add(6);
        set.add(9);
        set.add(11);
        set.add(13);
        WordIndex word = new WordIndex();
        word.loadFileWithoutSplit("C:\\Java course\\DEmelyanov2\\collection_pro\\input_without_split.txt");
        assertThat(word.getIndexes4AhoAloritm("dan"), is(set));

    }
}