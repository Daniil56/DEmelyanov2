package ru.job4j.review;


import ru.job4j.user.User;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Sorter {

    /**
     * Конструктор для класса Sorter.
     * В классе Sorter уже определен неявный конструктор по умолчанию.
     * Наличие данного конструктора при отсутсвии других явных конструкторов не обязательно.
     */
    public Sorter() {
    }

    /**
     * Мктод добавления типа данных из коллекции лист в колекцию Set.
     * Для  создания коллеции из другой коллекции достаточно воспользоваться
     * ключевым словом new, названием коллекции и использованием типа данных в дженерике коллекции.
     *
     * @param list Входная коллекция List с типом даннхы User
     * @return возвращает коллецию TreeSet с данными из коллекции User
     */
    Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
        /*
       TreeSet<User> sortedList = new TreeSet<>();
       sortedList.addAll(list);
       return sortedList;
         */

    }

    /**
     * Метод сортировки по длинне имени в коллекции.
     * Для сортировки при помощи компаратора достаточно воспользоваться
     * анонимным классом, в котором создаем комппаратор и переопределям его метод.
     *
     * @param list Входная коллекция.
     * @return Возвращает отсортированную компаратором коллекцию.
     */
    List<User> sortnamelength(List<User> list) {
        list.sort(Comparator.comparingInt(o -> o.getName().length()));
        return list;
    }

    /**
     * Метод сортировки по двум полям.
     * Для сортировки по всем поялм достаточно использовать один компаратор.
     * @param list входная коллекция.
     * @return отсортированная коллекция.
     */
    List<User> sortboth(List<User> list) {
        list.sort(Comparator.comparing(User::getName));
        return list;
    }
}