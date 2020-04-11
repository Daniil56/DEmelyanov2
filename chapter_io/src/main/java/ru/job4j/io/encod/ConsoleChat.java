package ru.job4j.io.encod;


import ru.job4j.io.encod.input.ConsoleInput;
import ru.job4j.io.encod.input.Input;
import ru.job4j.io.encod.input.ValidateInput;
import ru.job4j.io.encod.output.Output;

import java.io.IOException;

public class ConsoleChat {
    private final Input input;
    private final Output output;

    public ConsoleChat(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public static void main(String[] args) throws IOException {
        var dataBot = System.getProperty("java.io.tmpdir") + "random.txt";
        new ConsoleChat(new ValidateInput(new ConsoleInput()), new Bot(dataBot)).init();

    }

    private void init() throws IOException {
        final Menu menu = new Menu(this.output);
        menu.fillActions();
        this.output.open();
        String key;
        do {
            key = this.input.from();
            menu.select(key);
        }
        while (!key.equals("end"));
    }
}
