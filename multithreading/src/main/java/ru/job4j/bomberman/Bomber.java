package ru.job4j.bomberman;

import java.util.Random;

public class Bomber extends  Thread {
    private boolean aLive;
    private Cell position;
    private final Board board;

    public Bomber(Cell position, Board board) {
        this.aLive = true;
        this.position = position;
        this.board = board;
        new Thread().start();
    }

    @Override
    public void run() {
        Random random = new Random();
        board.occupied(position);
        while (!isInterrupted()) {
            if (aLive) {
                board.tryLock(position);
                aLive = false;
                Cell nextCell = new Cell(position.getX() + (random.nextInt(2) - 1), position.getY() + (random.nextInt(2) - 1));
                if (board.moove(position, nextCell)) {
                    this.position = nextCell;
                }
            }
        }
    }
}
