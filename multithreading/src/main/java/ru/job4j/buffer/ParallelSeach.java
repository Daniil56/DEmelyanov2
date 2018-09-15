package ru.job4j.buffer;

import ru.job4j.concurrency.SimpleBlockingQueue;

public class ParallelSeach {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                                System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                           System.out.println("Consumer complete");
                        }
                    }
                }
        );
        consumer.start();
         Thread producer = new Thread(
                () -> {
                        for (int index = 0; index != 3; index++) {
                            try {
                                queue.offer(index);
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                        }

                }

        );
         producer.start();
         producer.join();
         consumer.interrupt();
    }
}
