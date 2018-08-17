package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

  private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );
  private boolean ready = false;
  private String subject;
  private String body;

    public boolean isReady() {
        return ready;
    }

    public void emailTo(User user) {
        this.subject = "Notification for " + user.getUserName() + "to email " + user.getEmail();
        this.body = "Add a new event to " + user.getUserName();
    }

    public void send(String subject, String body, String email) {
        System.out.println("Execute " + Thread.currentThread().getName());

    }

    public void mailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                emailTo(user);
                ready = true;
                this.notifyAll();
                System.out.println("Execute " + Thread.currentThread().getName());

            }
        });
        pool.submit(new Runnable() {
            @Override
            public void run() {
                while (!ready) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                send(subject, body, user.getEmail());
                ready = false;
            }
        });
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
