package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class Rmoove implements Runnable {
    private final Rectangle rect;

    public Rmoove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        synchronized (this) {
            while (this.rect.getX() <= 300) {
                this.rect.setX(this.rect.getX() + 1);
                if (rect.getX() >= 300) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (rect.getX() == 1) {
                    run();
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
