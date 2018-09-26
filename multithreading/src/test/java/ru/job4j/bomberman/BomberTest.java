package ru.job4j.bomberman;

import org.junit.Test;

public class BomberTest {
    @Test
    public void whenBomberAddThenIsBoardAlive() {
        Board board = new Board(5, 5, 0, 0);
        board.addBomberman(new Cell(2, 2));
        board.addBomberman(new Cell(4, 4));
    }
    @Test
    public void whenBoar() throws InterruptedException {
        Bomber bomb = new Bomber(new Cell(5, 5), new Board(10, 10, 2, 2));
        bomb.start();
        Thread.sleep(1000);
    }
    @Test
    public void whenBoard12X12ThreeMonstersAndFourStones() throws InterruptedException {
        Board board = new Board(3, 3, 1, 1);
        for (int position = 1; position < 2; position++) {
                Thread.sleep(5);
                board.setShiftX(1);
                board.setShiftY(1);
        }
        for (int position = 1; position < 2; position++) {
                Thread.sleep(5);
                board.setShiftX(-1);
                board.setShiftY(-1);
        }
    }
}