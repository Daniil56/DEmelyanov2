package ru.job4j.io.encod;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot implements Closeable {
    private final BufferedReader reader;
    private static final List<String> BOT_LIST = new ArrayList<>();

    public Bot(String path) throws IOException {
        this.reader = new BufferedReader(new FileReader(path));
        this.open();
    }

    public static String getAny() {
        return BOT_LIST.get(new Random().nextInt(BOT_LIST.size()));
    }

    private void open() throws IOException {
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
