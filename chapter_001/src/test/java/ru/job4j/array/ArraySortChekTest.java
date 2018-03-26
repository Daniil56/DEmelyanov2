package ru.job4j.array;

        import org.junit.Test;

        import static org.hamcrest.core.Is.is;
        import static org.junit.Assert.assertThat;

public class ArraySortChekTest {
    @Test
    public void isSort() {
        ArraySortCheck s = new ArraySortCheck();
        boolean result = s.sort(new int[]{1, 2, 3, 4, 5}, false);
        boolean expectResult = true;
        assertThat(result, is(expectResult));
    }

}