package ru.job4j.io.encod.input;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);


    @Override
    public String from()  {
        return scanner.next();
    }
}
