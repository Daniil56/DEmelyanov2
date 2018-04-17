package ru.job4j.chess;

public class Bishop extends Figure {


    private Bishop(Cell dest) {
        super(dest);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        int count = 1;
        Cell[] currentCourse = new Cell[0];
        if (Math.abs(dest.getX() - source.getX()) == Math.abs(dest.getY() - source.getY()))
            if ((source.getX() - dest.getX()) <= -1 && (source.getY() - dest.getY()) <= -1) {
                for (int i = source.getX() + 1, j = source.getY() + 1; count <= Math.abs(dest.getX() - source.getX()); i++, j++) {
                    currentCourse[count - 1] = new Cell(i, j);
                    count++;
                }
                throw new ImposableMoveException("Move not be applied");
            }
        return currentCourse;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }


}
