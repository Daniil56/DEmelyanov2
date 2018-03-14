package ru.job4j.loop;

public class Board {

    public  String paint(int weidth, int hight) {
    StringBuilder screen = new StringBuilder();
        final String line = System.getProperty("line.separator");
    for (int vaH = 1;  vaH <= hight; vaH++) {
        for (int vaW = 1; vaW <= weidth; vaW++) {
            int sum = vaH + vaW;
            if (sum % 2 == 0) {
                screen.append("X");
            } else {
                screen.append(" ");
            }
        }
        screen.append(line);
    }
        return screen.toString();
    }
}
