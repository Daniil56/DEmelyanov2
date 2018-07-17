package ru.job4j.problem;

import org.junit.Test;

public class CounterTest {
    @Test
    public void whenSunchronizedThenRaceNotCondition() {
        Counter counter = new Counter();

        for (int i = 0; i < 200; i++) {
            CounterThread ct = new CounterThread(counter);
            ct.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.count);

    }
}