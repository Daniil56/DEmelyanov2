package ru.job4j.io;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearchTest {
    @Rule
    public TemporaryFolder folder =  new TemporaryFolder();

    @Test
    public void whenSearchIntoConditionThen() throws IOException {
        Search first = new Search();
        Predicate<String> conditional = s -> (s.endsWith(".txt"));
        File firstTxt = folder.newFile("test.txt");
        File secondTmp = folder.newFile();
        File nextTmp = folder.newFile();
        File thirdTxt = folder.newFile("third.txt");
        folder.newFolder("three");
        File one = new File(folder.newFolder("one"), "One_Child.txt");
        one.createNewFile();
        File twoFolder = folder.newFolder("two");
        File two = new File(twoFolder, "two.txt");
        File twoChild = new File(twoFolder, "Two_Child.txt");
        two.createNewFile();
        twoChild.createNewFile();
        List<File> actual = first.files(folder.getRoot().getPath(), conditional);
        List<File> expected = List.of(firstTxt, thirdTxt, one, two, twoChild);
        assertThat(actual, is(expected));
    }

}