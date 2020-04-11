package ru.job4j.io.encod.input;

import java.util.Objects;

public class ValidateInput implements Input {
    private final Input input;



    public ValidateInput(Input input) {
        this.input = input;
    }



    @Override
    public String from()  {
        return Objects.requireNonNull(input.from(), "key not be null");
    }
}
