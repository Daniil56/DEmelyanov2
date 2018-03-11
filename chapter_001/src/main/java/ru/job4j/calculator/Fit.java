package ru.job4j.calculator;

/**
 * Программа расчета идеального веса.
 */
public class Fit {
    private double weight = 0;
    /**
     * Идеальный вес ля мужчины.
     * @param height рост.
     * @return идеальный вес.
     */
    double manWeight(double height) {
        this.weight = (height - 100) * 1.15;
        return  weight;
    }
    double womanWeight(double height) {
        this.weight = (height - 110) * 1.15;
        return  weight;
    }
}
