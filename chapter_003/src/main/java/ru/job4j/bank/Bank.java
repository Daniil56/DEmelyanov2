package ru.job4j.bank;

import ru.job4j.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Bank {
    private Map<User, ArrayList<Account>> userListMap = new TreeMap<>();

    public void addUser(User user) {
        this.userListMap.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        this.userListMap.remove(user);
    }
    public void addAccounToUser(String passport, Account account) {
           for (User user: this.userListMap.keySet()) {
               if (user.getPasport().equals(passport))
                   this.userListMap.get(user).add(account);
           }

        }

    public void deleteAccountFromUser(User user, Account account) {
        this.userListMap.get(user).remove(account);
    }

    public List<Account> getUserAccount(String passport) {
           return this.userListMap.get(passport)  ;
    }

    public boolean  getUserPassport(String passport) {
        boolean equals = false;
        User user = new User();
        if (user.equals(passport)) {
            equals = true;
        }
        return equals;
    }

    public boolean getAccountRequisite(String requisite) {
        boolean equals = false;
        Account account = new Account();
        if (account.equals(requisite)) {
            equals = true;
        }
        return equals;
    }

    private Account getAccountActual(String passport, String requisites) {
        Account actual = new Account();
        for(Account account : this.userListMap.get(passport)) {
            if (account.getRequisites().equals(requisites)) {
                actual = account ;
            }
        }
        return actual;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
       return  (getUserPassport(srcPassport) && getUserPassport(destPassport) && getAccountRequisite(srcRequisite) && getAccountRequisite(destRequisite) &&
            getAccountActual(srcPassport, srcRequisite).transfer(getAccountActual(destPassport, destRequisite), amount));


    }
}
