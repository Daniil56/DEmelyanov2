package ru.job4j.calculator;

public class Converter {
    private int dollar = 60;
    private int euro = 70;
    /**
     * Конвертация рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        this.euro =  value / euro;
        return this.euro;
    }
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллоры.
     */
    public int rubleToDollar(int value) {
        this.dollar = value / dollar;
        return this.dollar;
    }
    /**
     * Конвертируем доллары в рубли.
     * @param value рубли.
     * @return Доллоры.
     */
    public int dollarToRuble(int value) {
        this.dollar = dollar * value;
        return this.dollar;
    }
    /**
     * Конвертируем евров рубли.
     * @param value рубли.
     * @return Евро.
     */
    public int euroToRuble(int value) {
        this.euro = euro * value;
        return this.euro;
    }
}
