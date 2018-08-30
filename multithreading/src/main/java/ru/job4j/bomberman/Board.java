package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
   private final ReentrantLock[][] board;
   private final int x;
   private final int y;

    public Board(int x, int y) {
        this.x = x;
        this.y = y;
        board = new ReentrantLock[x][y];
        for (int high = 0; high < x; high++) {
            for (int length = 0; length < y; length++) {
                board[high][length] = new ReentrantLock();
            }
        }
    }

    void add(Cell cell) {
        new Bomber(cell, this);
    }

    boolean moove(Cell source, Cell dest) {
        boolean result = false;
            if (dest.getX() >= 0 && dest.getX() <= this.x && dest.getY() >= 0 && dest.getY() <= this.y) {
                result = tryLockCell(source, dest);
                System.out.println("Поток " + Thread.currentThread().getId() + " смещен на координаты X = " + dest.getX() + ": Y= " + dest.getY());
            }

        return result;
    }

    public boolean tryLockCell(Cell source, Cell dest) {
        try {
            if (this.board[dest.getX()][dest.getY()].tryLock(500, TimeUnit.MILLISECONDS)) {
                board[source.getX()][source.getY()].unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void tryLock(Cell source) {
        board[source.getX()][source.getY()].lock();
    }

    public boolean occupied(Cell cell) {
        boolean result = false;
        try {
            result = cell.getX() >= 0 && cell.getX() <= this.x && cell.getY() >= 0 && cell.getY() <= this.y && this.board[cell.getX()][cell.getY()].tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
