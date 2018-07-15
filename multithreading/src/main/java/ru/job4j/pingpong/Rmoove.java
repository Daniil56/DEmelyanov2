package ru.job4j.pingpong;

import javafx.scene.control.Dialog;
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
            while (true) {
                this.rect.setX(this.rect.getX() + x);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
