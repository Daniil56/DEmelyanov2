package ru.job4j.departaments;

import java.util.*;

/**
 * Класс сортировки депортаментов.
 */
public class SortDepartaments {

    /**
     * Метод сортировки депортаментов.
     * В методе используется коллекция TreeSet, позволяющаю сортировать множество.
     * @param values Исходный массив строк департаментов.
     * @return Отсортированные депортаменты.
     */
    public Set<String> sortDepartments(String[] values) {
        TreeSet<String> temp = new TreeSet<>();
        Arrays.stream(values).forEach(value -> {
            StringBuilder builder = new StringBuilder();
            Arrays.stream(value.split("\\\\")).forEach(s -> {
                builder.append(s);
                temp.add(builder.toString());
                builder.append("\\");
            });
        });
        return temp;
    }

    /**
     * Метод реверсивной сортировки.
     * В методе используется метод из класса Comparator reverseOrder
     * @return Отсортированный реверсивно, массив.
     */
    public TreeSet<String> reverse(String[] departments) {
        TreeSet<String>  sortDepartments = new TreeSet<>(Comparator.reverseOrder());
          sortDepartments.addAll(sortDepartments(departments));
                return sortDepartments;
            }



}
