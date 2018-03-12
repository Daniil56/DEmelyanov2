package ru.job4j.condition;

/**
 * author Daniil Emelyanov
 * @version $id$
 * @since  12/03/2018
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    /**
     * Метод вычисления полупериметра по длинам сторон.
     * <p>
     * (ab+ac+bc)/2
     *
     * @param ab расстояние между точками а и б
     * @param ac расстояние меду точками а и с
     * @param bc расстояние между двумя точками b и c
     * @return Периметр
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }
    /**
     * Метод должен вычислить площадь треугольника.
     *
     * @reurn rs1 Вернуть площадь если треугольник существует или - 1 если треугольника нет
     */
    public double area() {
        double rs1 = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rs1 = Math.sqrt(this.period(ab, ac, bc) * (this.period(ab, ac, bc) - ab) * (this.period(ab, ac, bc) - ac) * (this.period(ab, ac, bc) - bc));
        }
        return rs1;
    }
    /**
     * Метод проверяет можно ли построить треугольник с такими длинами сторон.
     *
     * @param ab Длина от точки a b.
     * @param ac Длина от точки a c.
     * @param bc Длина от точки b c.
     * @return булево условие существоания треуголика
     */
    private boolean exist(double ab, double ac, double bc) {
        if (!(((ab + ac) > bc) ? ((ab + bc) > ac) : ((bc + ab) > ac))) {
            return false;
        } else {
            return true;
        }
    }
}