package ru.job4j.bank;

import java.util.Objects;

public class Account {
    private double value;
    private String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }
    public Account() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {

        return Objects.hash(requisites);
    }

    @Override
    public String toString() {
        return "Account{" + "value=" + value + ", requisites='" + requisites + '\'' + '}';
    }

    /**
     * Метод проверки возможности перевода.
     * @param destination Аккаунт на которой оосвершается перевод.
     * @param amount количество денег для перевода.
     * @return возаращает перевод средств, или false.
     */
    public boolean transfer(Account destination, double amount) {
        boolean success = false;
        if (amount > 0 && destination != null && amount < this.value) {
            success = true;
            this.value -= amount;
            destination.value += amount;

        }
        return success;

    }
}
