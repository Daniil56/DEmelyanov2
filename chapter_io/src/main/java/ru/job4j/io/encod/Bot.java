package ru.job4j.io.encod;

import ru.job4j.io.encod.output.Output;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot implements  Output, Closeable {
    private final BufferedReader reader;
    private static final List<String> BOT_LIST = new ArrayList<>();

    public Bot(String path) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(path));
    }

    public static String getAny() {
        return BOT_LIST.get(new Random().nextInt(BOT_LIST.size()));
    }

    @Override
    public void open() throws IOException {
        String next;
        try {
            while ((next = reader.readLine()) != null) {
                BOT_LIST.add(next);
            }
        } finally {
            this.close();
        }
    }

    @Override
    public void close() throws IOException {
        this.reader.close();
    }

}
