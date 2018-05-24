package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatrixIteratorTest {

    @Test
    public void whenGetNextCallSholdPointerForward() {
        MatrixIterator it = new MatrixIterator(new int[][] {
                {1, 2, 6},
                {3, 4}
    });
        it.next();
        it.next();
        it.next();
        it.next();
        int result = (Integer) it.next();
        assertThat(result, is(4));
    }

    @Test
    public void whenCheckNextPositionShouldReturnConantValue() {
        MatrixIterator it = new MatrixIterator(new int[][]{
                {1, 2},
                {3, 4}
        });
        it.next();
        it.hasNext();
        it.hasNext();

        boolean result = it.hasNext();

        assertThat(result, is(false));
    }


}