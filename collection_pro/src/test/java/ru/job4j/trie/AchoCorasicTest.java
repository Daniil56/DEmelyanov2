package ru.job4j.trie;

import static org.junit.Assert.assertThat;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
public class AchoCorasicTest {

    @Test
    public void whenFIledLoadThenWordsPrint()  {
        Set<Integer> set = new HashSet<>();
        set.add(9);
        set.add(4);
        set.add(5);
        AchoCorasic word = new AchoCorasic(100);
        word.add("lol");
        word.add("hi");
        word.add("ab");
        word.add("dan");
        word.add("dan");
        word.add("ac");
        word.add("ab");
        word.add("ad");
        word.add("dan");
        assertThat(word.seach("dan"), is(set));
        assertThat("dan".indexOf("i"), is(-1));


    }
}