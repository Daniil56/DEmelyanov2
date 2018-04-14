package ru.job4j.chess;

public class Cell {
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int distance(Cell that) {
        return (int) Math.sqrt(Math.pow(this.y - that.y, 2) + Math.pow(this.x - that.x, 2));

    }
}
