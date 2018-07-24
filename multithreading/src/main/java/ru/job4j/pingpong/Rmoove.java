package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class Rmoove implements Runnable {
    private final Rectangle rect;

    public Rmoove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int x = 1;
        synchronized (this) {
            while (!Thread.currentThread().isInterrupted()) {
                this.rect.setX(this.rect.getX() + x);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                if (this.rect.getX() == 290) {
                    x = -1;
                }
                if (this.rect.getX() == 5) {
                    x = 1;
                }
            }
        }

    }
}
