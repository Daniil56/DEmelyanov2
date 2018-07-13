package ru.job4j.pingpong;


import javafx.scene.shape.Rectangle;

public class RmoveN implements Runnable {
    private final Rectangle rect;

    public RmoveN(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        synchronized (this) {
            while (rect.getX() == 300) {
                this.rect.setX(this.rect.getX() - 1);
                if (rect.getX() == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (rect.getX() == 300) {
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
