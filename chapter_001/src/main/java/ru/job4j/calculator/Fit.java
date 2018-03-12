package ru.job4j.calculator;

/**
 * Программа расчета идеального веса.
 */
public class Fit {
    private double weight;
    private final double idealHeightMan = 100;
    private final double idealHeightWooman = 110;
    private final double growthFactor = 1.15;
    /**
     * Идеальный вес ля мужчины.
     * @param height рост.
     * @return идеальный вес.
     */

    double manWeight(double height) {
        return  this.weight = (height - idealHeightMan) * growthFactor;

    }
    double womanWeight(double height) {
       return this.weight = (height - idealHeightWooman) * growthFactor;

    }
}
