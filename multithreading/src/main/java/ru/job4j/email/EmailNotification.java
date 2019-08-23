package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

  private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
   );


    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String subject = "Notification " + user.getUserName() + " email: " + user.getEmail();
            String body = "Add new event to " + user.getUserName();
              send(subject, body, user.getEmail());

            }
        });
    }

    public void send(String subject, String body, String email) {
        System.out.println("Execute " + Thread.currentThread().getName());
        System.out.println("subject " + subject);
        System.out.println("body " + body);
        System.out.println("email " + email);



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
