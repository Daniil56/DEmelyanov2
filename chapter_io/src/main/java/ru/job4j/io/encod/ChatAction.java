package ru.job4j.io.encod;

import ru.job4j.io.encod.output.Output;

import java.io.IOException;

public interface ChatAction {
    /**
     *
     * @param out output
     * @param key input key
     */
    void execute(Output out, String key) throws IOException;
}
