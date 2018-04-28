package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {
    @Test
    public void when7ElementThan9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3);
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void whenListThenArrayList() {
        ConvertList2Array list = new ConvertList2Array();
        List<int[]> expect = new ArrayList<int[]>();
        expect.add(new int[]{1, 2});
        expect.add(new int[]{3, 4, 5, 6});
        expect.add(new int[]{7, 8, 9});
        List<Integer> result = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(list.convert(expect), is(result));
    }
}