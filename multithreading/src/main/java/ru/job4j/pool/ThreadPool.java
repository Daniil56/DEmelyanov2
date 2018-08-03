package ru.job4j.pool;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@ThreadSafe
public class ThreadPool {
    @GuardedBy(value = "this")
    private final List<Thread> threads = new LinkedList<>();
    @GuardedBy("this")
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>(1);

    private  boolean isRunnung = false;


    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int position = 0; position < size; position++) {
           threads.add(new TaskWorker());
           threads.get(position).start();
        }

    }

    public  void work(Runnable job) {
        synchronized (tasks) {
            tasks.offer(job);
             tasks.notifyAll();
        }

    }

    public void shutdown() {
        synchronized (tasks) {
            this.isRunnung = true;
            for (Thread t : threads) {
               t.interrupt();
            }
        }
    }

     class TaskWorker extends Thread {

        @Override
        public void run() {
                while (!isRunnung) {
                    synchronized (tasks) {
                        while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            System.out.println("shout down");
                        }
                    }
                    Runnable nextTask;
                    nextTask = tasks.poll();
                    if (nextTask != null) {
                        nextTask.run();
                    }

                }
                }
            }
        }
}


