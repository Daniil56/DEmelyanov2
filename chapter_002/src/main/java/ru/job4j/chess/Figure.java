package ru.job4j.chess;

public abstract class Figure {
   private final Cell position  ;

    public  Figure(Cell position) {
        this.position = position;
    }

    public  abstract Cell[] way(Cell source, Cell dest)
            // dest - задает ячейку, куда следует пойти. Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
        //
        //    Если фигура туда пойти не может. выбросить исключение ImposibleMoveException
            throws  ImposableMoveException;

    public abstract Figure copy(Cell dest);

}
