package ru.job4j.problem;

public class CounterThread extends Thread {
      Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
            for (int value = 0; value < 1000; value++) {
                counter.incrementCount();

            }

    }
}