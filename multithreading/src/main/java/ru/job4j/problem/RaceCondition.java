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
    protected long count = 0;

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

    Runnable one = () ->  add(2);

    Runnable two = () -> add(3);

    Runnable three = () -> add(4);

    Runnable four = () -> add(5);

    Runnable five = () -> add(6);

    Runnable six = () -> add(7);

    Runnable eight = () -> add(8);

    Runnable nine = () -> add(9);
}