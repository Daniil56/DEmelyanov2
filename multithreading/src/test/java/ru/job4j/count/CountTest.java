package ru.job4j.count;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CountTest {
    /**
     * Класс описывает нить со счетчиком
     */
    private class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        //счетчик
        final Count count = new Count();
        //нити
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        //главня нить дожыдается выполнение нитей 1 и 2
        first.join();
        second.join();
        assertThat(count.get(), is(2));
    }
}