package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class Config {
    private final String  path;
    private final Map<String, String> values = new HashMap<>();

    public Map<String, String> getValues() {
        return values;
    }

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(Objects::nonNull)
                    .filter(val -> !val.isEmpty())
                    .filter(val -> !val.startsWith("#"))
                    .forEach(val -> {
                        String[] split = val.split(Pattern.quote("="));
                        String k = split[0];
                        String v = split[1];
                        values.put(k.trim(), v.trim());
                    });
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

