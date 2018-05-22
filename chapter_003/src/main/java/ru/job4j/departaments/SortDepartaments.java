package ru.job4j.departaments;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс сортировки депортаментов.
 */
public class SortDepartaments {
    private final String[] values;

    /**
     * Конструктр класса принимающий массив строк.
     * В конструкторе используется объект StringBullder для обновления значений строк.
     * Также применяется коллекция Set, не позволяющия хранить дублированные значения.
     * @param values исходный массив строк, передаваемый методу.
     */
    public SortDepartaments(String[] values) {
        Set<String> temp = new HashSet<>(Arrays.asList(values));
        for (String value: values) {
            StringBuilder builder = new StringBuilder();
            String[] separate = value.split("\\\\");
            builder.append(separate[0]);
            temp.add(builder.toString());
            for (int index = 1; index < separate.length - 1; index++) {
                builder.append("\\").append(separate[index]);
                temp.add(builder.toString());
            }
        }
        this.values = temp.toArray(new String[temp.size()]);
    }

    /**
     * Метод сортировки департаментов.
     * Для сортировки используем метод sort из класса Arrays
     * @return Отсортированный массив.
     */
    public String[] sort() {
        Arrays.sort(this.values);
        return this.values;
    }

    /**
     * Метод реверсивной сортировки.
     * В методе переопределен метод компоратора compare
     * @return Отсортированный реверсивно, массив.
     */
    public String[] reverse() {
        Arrays.sort(this.values, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int minLength = Math.min(o1.length(), o2.length());
                int result = o1.length() - o2.length();
                for (int index = 0; index < minLength; index++) {
                    char o1Char = o1.charAt(index);
                    char o2Char = o2.charAt(index);
                    if (o2Char - o1Char != 0) {
                        result = o2Char - o1Char;
                        break;
                    }
                }
                return result;
            }
        });
        return this.values;
    }
}
