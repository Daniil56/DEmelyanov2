package ru.job4j.calculator;

public class Converter {
    private double dollar = 60;
    private double euro = 70;
    /**
     * Конвертация рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public double rubleToEuro(double value) {
        this.euro =  value / euro;
        return this.euro;
    }
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллоры.
     */
    public double rubleToDollar(double value) {
        this.dollar = value / dollar;
        return this.dollar;
    }
    /**
     * Конвертируем доллары в рубли.
     * @param value рубли.
     * @return Доллоры.
     */
    public double dollarToRuble(double value) {
        this.dollar = dollar * value;
        return this.dollar;
    }
    /**
     * Конвертируем евров рубли.
     * @param value рубли.
     * @return Евро.
     */
    public double euroToRuble(double value) {
        this.euro = euro * value;
        return this.euro;
    }
}
