package ru.job4j.io.encod;

import java.io.IOException;
@FunctionalInterface
public interface ChatAction {
    /**
     *
     * @param out output
     * @param key input key
     */
    void execute(Bot out, String key) throws IOException;
}
