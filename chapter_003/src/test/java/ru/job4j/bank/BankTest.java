package ru.job4j.bank;

import org.junit.Test;
import ru.job4j.user.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    @Test
    public void whenMapThenAddUser() {
        Bank bank = new Bank();
        User daniil = new User("Daniil", "1852638" );
        User ivan = new User("Ivan", "1235696" );
        Account accountDaniil = new Account(10000, "123456");
        Account accountDaniil2 = new Account(5000, "654321");
        Account accountIvan = new Account(7000, "3214566");
        bank.addUser(daniil);
        bank.addUser(ivan);
        bank.addAccounToUser("1852638" , accountDaniil);
        bank.addAccounToUser("1852638" , accountDaniil2);
        bank.addAccounToUser("1235696", accountIvan);
        assertThat(bank.transferMoney("1235696", "3214566","1852638", "123456", 5000), is(true));




    }

}