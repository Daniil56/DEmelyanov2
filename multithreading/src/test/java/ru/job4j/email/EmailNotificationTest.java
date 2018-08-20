package ru.job4j.email;

import org.junit.Test;

public class EmailNotificationTest {
    @Test
    public void whenNotificationTrue() {
        EmailNotification email = new EmailNotification();
      //  assertThat(email.isReady(), is(false));
        User daniil = new User("daniil", "maskedon@ya.ru");
        email.mailTo(daniil);
     //   assertThat(email.isReady(), is(true));
        email.close();
       // assertThat(email.isReady(), is(false));


    }

}