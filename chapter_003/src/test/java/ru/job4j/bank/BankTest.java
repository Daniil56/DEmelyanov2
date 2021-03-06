package ru.job4j.bank;

import org.junit.Test;
import ru.job4j.user.User;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    @Test(expected = NoSuchElementException.class)
    public void whenMapThenAddUser() {
        Bank bank = new Bank();
        User daniil = new User("Daniil", "1852638");
        User ivan = new User("Ivan", "1235696");
        Account accountDaniil = new Account(10000, "123456");
        Account accountDaniil2 = new Account(5000, "654321");
        Account accountIvan = new Account(7000, "3214566");
        Account accountIvan2 = new Account(5000, "9876543");
        bank.addUser(daniil);
        bank.addUser(ivan);
        bank.addUser(new User("Daniil", "1852638"));
        bank.addUser(new User("Ivan", "1235696"));
        bank.addAccountToUser("1235696", accountIvan);
        bank.addAccountToUser("1235696", accountIvan2);
        bank.addAccountToUser("1852638", accountDaniil);
        bank.addAccountToUser("1852638", accountDaniil2);
        assertThat(bank.transferMoney("1852638", "123456", "1852638", "654321", 500), is(true));
        assertThat(bank.transferMoney("1852638", "1234567", "1852638", "654321", 500), is(false));
        assertThat(bank.transferMoney("18526389", "123456", "18526318", "654321", 500), is(false));
    }

    @Test
    public void whenMapDeleteUserThenMapVoid() {
        Bank bank = new Bank();
        User daniil = new User("Daniil", "1852638");
        bank.addUser(daniil);
        Account accountDaniil = new Account(10000, "123456");
        bank.addAccountToUser("1852638", accountDaniil);
        bank.deleteUser(daniil);
        assertThat(bank.getUserAccount("1852638").size(), is(0));
    }

    @Test
    public void whenMapDeleteAccountThenListAccountVoid() {
        Bank bank = new Bank();
        User daniil = new User("Daniil", "1852638");
        bank.addUser(daniil);
        Account accountDaniil = new Account(10000, "123456");
        bank.addAccountToUser("1852638", accountDaniil);
        bank.deleteAccountFromUser(daniil, accountDaniil);
        assertThat(bank.getUserAccount("1852638").size(), is(0));
    }


}