package ru.job4j.review;

import org.junit.Test;
import ru.job4j.user.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    @Test
    public void whenMapThenAddUser() {
        Bank bank = new Bank();
        User daniil = new User("daniil", 18);
        User ivan = new User("ivan", 20);
        Account accountDaniil = new Account(100000, "M8801516789");
        Account accountIvan = new Account(5000, "12f212f");
        bank.addUser(daniil);
        bank.addUser(ivan);
        bank.add(daniil, accountDaniil);
        bank.add(ivan, accountIvan);

    }

}