package ru.job4j.bomberman;

public class Stone {
     Stone(Cell cell, Board board) {
        board.tryLock(cell);
    }
}
