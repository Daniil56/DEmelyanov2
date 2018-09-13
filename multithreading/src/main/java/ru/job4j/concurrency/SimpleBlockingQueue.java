package ru.job4j.concurrency;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy(value = "this")
    private final Queue<T> queue = new LinkedList<>();
    private boolean block = false;

    public synchronized Queue<T> getQueue() {
        return this.queue;
    }

    public void offer(T value) {
        synchronized (this) {
            while (block) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            block = true;
            queue.offer(value);
            notify();
            System.out.println(value + " добавлено в очередь ");

        }

    }

    public  T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                wait();
            }
            block = false;
            notify();
            T value = queue.poll();
            System.out.println(value + " удаленно из очереди ");
            return value;
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
