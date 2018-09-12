package ru.job4j.email;

import org.junit.Test;

public class EmailNotificationTest {
    @Test
    public void whenNotificationOneThread() {
        EmailNotification email = new EmailNotification();
        User daniil = new User("daniil", "maskedon@ya.ru");
        email.emailTo(daniil);
        email.close();
    }
    @Test
    public void whenNotificationMultiThread() {
        EmailNotification email = new EmailNotification();
       for (int num = 0; num < 10; num++) {
           try {
               Thread.sleep(100);
               email.emailTo(new User("User" + num, "user" + num + "@yandex.ru"));
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       email.close();
    }

}