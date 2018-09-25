package ru.job4j.bank;

import ru.job4j.user.User;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Bank {
    private Map<User, ArrayList<Account>> userListMap = new TreeMap<>();

    public void addUser(User user) {
        this.userListMap.putIfAbsent(user, new ArrayList<>());

    }

    public void deleteUser(User user) {
        this.userListMap.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        this.userListMap.keySet().stream().filter(
                user -> user.getPasport().equals(passport)).forEach(user -> this.userListMap.get(user).add(account));
        }

    public void deleteAccountFromUser(User user, Account account) {
        this.userListMap.get(user).remove(account);
    }

    public List<Account> getUserAccount(String passport) {
           return  this.userListMap.keySet().stream().filter(user -> user.getPasport().equals(passport))
                   .flatMap(user -> userListMap.get(user).stream()).collect(Collectors.toList());
    }

    public boolean getUserRequisites(String requisites) {
        Predicate<ArrayList<Account>> predicate = p -> p.contains(getAccountActual(requisites));
        return userListMap.keySet().stream().filter(user -> predicate.test(userListMap.get(user)))
                .allMatch(user -> predicate.test(userListMap.get(user)));
    }

    public boolean getUserPassport(String passport) {
        Predicate<User> predicate = p -> p.getPasport().equals(passport);
        return userListMap.keySet().stream().anyMatch(predicate);
    }

    private Account getAccountActual(String requisites) {
        return userListMap.values().stream().flatMap(Collection::stream)
                .filter(account -> account.getRequisites().equals(requisites))
                .collect(Collectors.toCollection(ArrayList::new)).iterator().next();
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
       return   getUserPassport(destPassport) && getUserPassport(srcPassport) && getUserRequisites(srcRequisite) && getUserRequisites(destRequisite) &&  getAccountActual(srcRequisite).transfer(getAccountActual(destRequisite), amount);
    }

}