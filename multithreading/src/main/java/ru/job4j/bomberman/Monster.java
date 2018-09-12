package ru.job4j.bomberman;

import java.util.Random;

public class Monster extends Thread {
    private Cell position;
    private boolean aLive;
    private final Board board;
    private final String name;

    public Monster(Cell position, Board board, String name) {
        this.board = board;
        this.position = position;
        this.aLive = true;
        this.name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {
        Random random = new Random();
        board.occupied(position);
        while (aLive) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Cell nextCell = new Cell(position.getX() + (random.nextInt(2) - 1),
                    position.getY() + random.nextInt(2) - 1);
            if (board.move(position, nextCell)) {
                this.position = nextCell;
            }
        }
    }
}
