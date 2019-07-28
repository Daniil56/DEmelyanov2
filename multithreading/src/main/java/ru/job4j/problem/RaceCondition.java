package ru.job4j.problem;

/**
 * Класс RaceCondition.
 * Описывает условие гонки потоков.
 * Где оканчательное значение поля count типа long, зависит от последнего, использовавшего его потока.
 * Соотвественно при каждом запуске программы печать count с большой вероятностью выдает разные значения.
 */
public class RaceCondition {

    public static void main(String[] args) {
        new RaceCondition().start();

    }
    private long count = 0;

    public void start() {
        new Thread(one).start();
        new Thread(two).start();
        new Thread(three).start();
        new Thread(five).start();
        new Thread(six).start();
        new Thread(nine).start();
        new Thread(eight).start();
        new Thread(four).start();
        System.out.println(this.count);
    }

    public  void add(long value) {
            count += value;
    }

    private Runnable one = () ->  add(2);

    private Runnable two = () -> add(3);

    private Runnable three = () -> add(4);

    private Runnable four = () -> add(5);

    private Runnable five = () -> add(6);

    private Runnable six = () -> add(7);

    private Runnable eight = () -> add(8);

    private Runnable nine = () -> add(9);
}