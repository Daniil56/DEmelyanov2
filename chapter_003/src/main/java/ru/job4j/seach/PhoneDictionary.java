package ru.job4j.seach;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подошедшших пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person p: persons) {
            if (p.getName().equals(key) || p.getSurname().equals(key) || p.getAddress().equals(key) || p.getPhone().equals(key)) {
                result.add(p);
            }
        }
        return persons.stream().filter(p -> p.getName().equals(key) || p.getSurname().equals(key) || p.getAddress().equals(key) || p.getPhone().equals(key)).collect(Collectors.toList());
    }
}
