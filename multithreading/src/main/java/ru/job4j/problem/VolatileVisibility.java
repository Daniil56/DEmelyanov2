package ru.job4j.problem;

/**
 * Класс VolatileVisibility.
 * Демонстрирует применение ключевого слова volatile для видимости общих объектов.
 * В классе объявлен метод start(), который запускает два потока: gui и start.
 * В потоке gui имитирующи выход из интерфейса, происходит присотановка потока на 2 секунды,
 * и присвоение btExit значения true. С последующим выводом на печать строки.
 * При volatile, изменения btExit записывается в RAM, иначе из Tread Stack записывается в кэш, где это изменение
 * не доступно для второго потока game, который в этом случае уходит в вечный цикл.
 */
public class VolatileVisibility {
    public static void main(String[] args) {
        new VolatileVisibility().start();
    }

     volatile private boolean btExit = false; // volatile влияет на работу класса

    // запуск потоков
    public void start() {
        new Thread(gui).start();
        System.out.println("gui thread started");
        new Thread(game).start();
        System.out.println("game thread started");
    }

    // поток для интерфейса
    Runnable gui = () -> {
        try {
            Thread.sleep(2000); // выход из потока путем его приостановки и смены состояния btExit
        } catch (InterruptedException ignored) {

        }
        btExit = true; // поток game не видит это изменение без volatile
        System.out.println("gui thread finished");
    };

    // поток для игры
    Runnable game = () -> {
        while (!btExit) {             // без volatile этот цикл крутится бесконечно
            int index = 0;
            index++;
        }
        System.out.println("game thread finished");
    };
}