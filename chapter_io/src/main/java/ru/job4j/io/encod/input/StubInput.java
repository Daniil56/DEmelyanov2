package ru.job4j.io.encod.input;

public class StubInput implements Input {

    private final String[] dialog;
    private int index;

    public StubInput(String[] dialog) {
        this.dialog = dialog;
    }


    @Override
    public String from()  {
        return this.dialog[index++];
    }
}
