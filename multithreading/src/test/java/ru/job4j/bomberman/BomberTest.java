package ru.job4j.bomberman;

import org.junit.Test;

import static org.junit.Assert.*;

public class BomberTest {
    @Test
    public void whenBomberAddThenIsBoardAlive() throws InterruptedException {
        Board board = new Board(5, 5);
        board.add(new Cell(2, 2));
        board.add(new Cell(4, 4));
       // board.moove((new Cell(2, 2)), (new Cell(1, 1)));
    }

}