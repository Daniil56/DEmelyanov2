package ru.job4j.iterator;

import java.util.*;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Iterator<Integer>> iterators = it;
            Iterator<Integer> integerIterator = it.next();

            private void reset() {
                if (this.integerIterator == null && this.iterators.hasNext()) {
                    integerIterator = this.iterators.next();
                }
            }

            @Override
            public boolean hasNext() {
                reset();
                if (integerIterator == null) {
                    return false;
                }
                if (integerIterator.hasNext()) {
                    return true;
                }
                if (iterators.hasNext()) {
                    integerIterator = iterators.next();
                }
                return integerIterator.hasNext();
            }

            @Override
            public Integer next() {
                reset();
                if (integerIterator == null) {
                    throw new NoSuchElementException();
                }
                if (!integerIterator.hasNext() && iterators.hasNext()) {
                    integerIterator = iterators.next();
                }

                return integerIterator.next();
            }
        };
    }
}
