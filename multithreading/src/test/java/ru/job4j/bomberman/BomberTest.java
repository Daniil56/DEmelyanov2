package ru.job4j.bomberman;

import org.junit.Test;

public class BomberTest {
    @Test
    public void whenBomberAddThenIsBoardAlive() {
        Board board = new Board(5, 5);
        board.add(new Cell(2, 2));
        board.add(new Cell(4, 4));
    }
    @Test
    public void whenBoar() throws InterruptedException {
        Bomber bomb = new Bomber(new Cell(5, 5), new Board(10, 10));
        bomb.start();
        Thread.sleep(1000);
        bomb.interrupt();
    }
}