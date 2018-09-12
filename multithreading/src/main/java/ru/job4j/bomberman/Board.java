package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
   private final ReentrantLock[][] board;
   private final int x;
   private final int y;
   private final AtomicInteger shiftX;
   private final AtomicInteger shiftY;

    public Board(int x, int y, int numOfMonsters, int numOfStones) {
        this.x = x;
        this.y = y;
        board = new ReentrantLock[x][y];
        this.shiftX = new AtomicInteger(0);
        this.shiftY = new AtomicInteger(0);
        for (int high = 0; high < x; high++) {
            for (int length = 0; length < y; length++) {
                board[high][length] = new ReentrantLock();
            }
        }
        Random random = new Random();
        addBomberman(new Cell(random.nextInt(x), random.nextInt(y)));
        for (int num = 0; num < numOfMonsters; num++) {
            int randomX = random.nextInt(x);
            int randomY = random.nextInt(y);
            addMonster(new Cell(randomX, randomY), "Monster #" + num);
            System.out.println("The coordinate of the monster # " + num + " : X = " + randomX + ", Y = " + randomY);

        }
        for (int num = 0; num < numOfStones; num++) {
            int randomX = random.nextInt(x);
            int randomY = random.nextInt(y);
            addStone(new Cell(randomX, randomY));
            System.out.println("The coordinate of the stone : X = " + randomX + ", Y = " + randomY);
        }

    }

    private void addStone(Cell cell) {
        new Stone(cell, this);
    }

    private void addMonster(Cell cell, String name) {
        new Monster(cell, this, name);
    }

    void addBomberman(Cell cell) {
        new Bomber(cell, this);
    }

    boolean move(Cell source, Cell dest) {
        boolean result = false;
            if (dest.getX() >= 0 && dest.getX() <= this.x && dest.getY() >= 0 && dest.getY() <= this.y && occupied(dest)) {
                result = true;
                tryLockCell(source, dest);
                System.out.println("Поток " + Thread.currentThread().getId() + " смещен на координаты X = " + dest.getX() + ": Y= " + dest.getY());
            }

        return result;
    }

    private void tryLockCell(Cell source, Cell dest) {
        try {
            if (this.board[dest.getX()][dest.getY()].tryLock(500, TimeUnit.MILLISECONDS)) {
                board[source.getX()][source.getY()].unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tryLock(Cell source) {
        board[source.getX()][source.getY()].lock();
    }

    public boolean occupied(Cell cell) {
        boolean result = false;
        try {
            result = cell.getX() >= 0 && cell.getX() <= this.x && cell.getY() >= 0 && cell.getY() <= this.y
                    && this.board[cell.getX()][cell.getY()].tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getShiftX() {
        return shiftX.getAndSet(0);
    }

    public void setShiftX(int shiftX) {
        this.shiftX.set(shiftX);
    }

    public int getShiftY() {
        return shiftY.getAndSet(0);
    }

    public void setShiftY(int shiftY) {
        this.shiftY.set(shiftY);
    }


}
