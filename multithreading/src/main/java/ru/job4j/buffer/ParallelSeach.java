package ru.job4j.buffer;

import ru.job4j.concurrency.SimpleBlockingQueue;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelSeach {
    public static void main(String[] args) {
        AtomicBoolean lock = new AtomicBoolean(false);
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final Thread consumer = new Thread(
                () -> {
                    while (!lock.get()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
         new Thread(
                () -> {
                        for (int index = 0; index != 3; index++) {
                            try {
                                queue.offer(index);
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                            if (index >= 1) {
                                lock.set(true);
                            }
                        }

                }

        ).start();
    }
}
