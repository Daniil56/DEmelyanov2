package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {
    private final String  path;
    private final Map<String, String> values = new HashMap<>();


    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String value(String key) {
        String result;
       if (this.values.containsKey(key)) {
           result = this.values.get(key);
       } else {
           throw new UnsupportedOperationException("Don't impl this method yet!");
       }
       return result;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}

