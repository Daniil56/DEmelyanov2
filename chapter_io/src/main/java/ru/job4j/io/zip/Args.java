package ru.job4j.io.zip;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Args {
    private final Map<String, String> argMap;

    public Args(String[] args) {
        this.argMap = createMapFromArgs(args);
        if (!isArgsKey(this.argMap)) {
            throw  new InvalidParameterException("Invalid key parameter line");
        }

    }

    public File directory() {
        return new File(this.argMap.get("-d"));
    }

    public String exclude() {
        return this.argMap.get("-e");
    }

    public File output() {
        return new File(this.argMap.get("-o"));
    }

    private boolean isArgsKey(Map<String, String> argsMap) {
        boolean result = false;
        if (Stream.of("-d", "-e", "-o").anyMatch(argsMap::containsKey)) {
            result = true;
        }
        return result;
    }

    private Map<String, String> createMapFromArgs(String[] args) {
        Map<String, String> result = new HashMap<>(args.length / 2);
        IntStream.range(0, args.length).filter(i -> i % 2 == 0).forEach(i -> {
            var key = args[i];
            var value = args[i + 1];
            result.put(key, value);
        });
        return result;
    }
}
