package ru.job4j.bomberman;

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
        board.occupied(position);
        while (aLive) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Cell nextCell = new Cell(
                        position.getX() + board.getShiftX(),
                        position.getY() + board.getShiftY());
                if (board.move(position, nextCell)) {
                    this.position = nextCell;
                }
            }
        }
    }

