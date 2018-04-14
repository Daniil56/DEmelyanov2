package ru.job4j.chess;

public class ChessBoard {
    Figure[] figures = new Figure[32];
    int index = 0;

    public Figure add(Figure figure) {
        return figure;
    }

    boolean move(Cell source, Cell dest)
            throws ImposableMoveException, OccupiedWayException, FigureNotFoundException {


                throw new FigureNotFoundException("Figure not found");
            }
        }

