package ru.job4j.problem;

/**
 * Класс Counter с полем типа long и синзронизированным методом.
 * Благодаря которому достигается отсуствия условия гонок потоков.
 */

public class Counter {

     long count = 0L;

    /**
     * Метод инкрементации переменной count.
     * Каждый созданный поток, в один момент времени может проинкрементировать переменную только один раз.
     * Благодаря чему достигается отсутсвие Race Condition, и на печать выводится всего 20000.
     * Если убрать synchronized результат вывода на печать не предсказуем.
     */
    public synchronized void incrementCount() {
            count++;
        }
}

