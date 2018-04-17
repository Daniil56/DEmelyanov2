package ru.job4j.chess;

import java.util.Arrays;

/**
 * Класс шахматной доски.
 * @author Daniil Emelyanov
 * @since 17.04.2018
 * @version 0.1
 */
public class ChessBoard {
    /**
     * Массив фигур доски
     */
    Figure[] figures = new Figure[32];

    /**
     * Метод реализующий движение фигуры по доске.
     *
     * @param source начальная клетка.
     * @param dest   клетка куда нужно пойти.
     * @return вернет true если ход произошел, или false если нет.
     * @throws ImposableMoveException  ошибка попытки совершить ход противоречиющий правилам.
     * @throws OccupiedWayException    ошибка попытки движения фигуры, если на ее пути есть другие фигуры.
     * @throws FigureNotFoundException ошибка отсутсвия фигуры в начальной ячейке.
     */
    boolean move(Cell source, Cell dest) throws ImposableMoveException,
            OccupiedWayException,
            FigureNotFoundException {
        boolean result = false;
        // осуществляем проверку наличия фигуры в source
        for (Figure figure : figures) {
            result = figure.equals(source);
            // если фигуры нет выводим исключение FigureNotFoundException
            if (!result) {
                throw new FigureNotFoundException("Figure not found");
            }
            // индекс фигуры из массива
            int index = 0;
            // проверяем что фигура может так пойти
            for (Cell c1 : figures[index].way(source, dest)) {
                // инкрементируем индекс фигуры
                index++;
                for (Figure figure1 : figures) {
                    // если фигура так не ходит выводим исключение ImposableMoveException
                    if (figure1.way(source, c1).equals(figure.way(source, dest)))
                        throw new ImposableMoveException("The figure can not walk like this");
                    // если путь который должна пройти фигура занят, выводим исключение OccupiedWayException
                    if (figure1.equals(c1)) {
                        throw new OccupiedWayException("The path is busy");
                        // если все прошло удачно, то перемещаем фигуру на клетку.
                            figures[index] = figure1.copy(dest);
                    }
                }

            }
        }

        return result;
    }
    public Figure add (Figure figure){
        figures = Arrays.copyOf(figures, figures.length + 1);
        return figures[figures.length - 1];
    }
}
