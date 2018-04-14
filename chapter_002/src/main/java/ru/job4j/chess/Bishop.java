package ru.job4j.chess;

public class Bishop extends Figure {


    private Bishop(Cell dest) {
        super(dest);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {

        throw new ImposableMoveException("Move not be applied");
    }

    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }
}
