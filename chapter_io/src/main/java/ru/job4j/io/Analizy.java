package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analizy {
    private final String pathIn;
    private final String pathOut;
    private final List<String> unavailableList = new ArrayList<>();
    final AtomicBoolean server  = new AtomicBoolean(true);


    public Analizy(String in, String out) {
        this.pathIn = in;
        this.pathOut = out;
    }


    private static String[][] apply(String[] splitTarget) {
        String[] split = splitTarget[0].split(";");
        String[] split1 = splitTarget[splitTarget.length - 1].split(";");
        return new String[][]{split, split1};
    }


    public void init(Map<String, String> data, PrintWriter out) {
            data.forEach((key, value) ->
                    out.printf("%s %s%n", key, value));
    }

    public List<String> unavailable() {
        List<String> result = new ArrayList<>();
        try (BufferedReader readerIn = new BufferedReader(new FileReader(this.pathIn));
             PrintWriter out = new PrintWriter(new FileOutputStream(this.pathOut))) {
            addUnavailableFromList(readerIn, out);
            result = sourceWrite(unavailableList, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
       return result;
    }

    public void addUnavailableFromList(BufferedReader readerIn, PrintWriter out) {
        final String[] join = {""};
        readerIn.lines()
                .filter(Objects::nonNull)
                .filter(val -> !val.isEmpty())
                .map(str -> str.split(" "))
                .forEach(logg -> {
                    int status = Integer.parseInt(logg[1]);
                    String time = logg[0];
                    if (server.get() && status == 500 || status == 400) {
                        out.printf("%s;", time);
                        join[0] = join[0].concat(time + ";");
                        server.set(false);
                    }
                    if (!server.get() && status == 200 || status == 300) {
                        out.printf("%s;\n", time);
                        join[0] = join[0].concat(time + ";\n");
                        server.set(true);
                    }
                });
        unavailableList.add(join[0]);
    }

    public List<String> sourceWrite(List<String> unavailableList, PrintWriter inWrite) {
        List<String> result = new ArrayList<>();
        unavailableList.stream().map(str -> str.split(";\n")).map(Analizy::apply).forEach(period -> {
            result.add(period[0][0]);
            result.add(period[0][period[0].length - 1]);
            inWrite.printf("%s;%s;%n", period[0][0], period[0][period[0].length - 1]);
            result.add(period[period.length - 1][0]);
            result.add(period[period.length - 1][period[period.length - 1].length - 1]);
            inWrite.printf("%s;%s;%n", period[period.length - 1][0], period[1][period[period.length - 1].length - 1]);
        });
        unavailableList.clear();
        return result;
    }

    public List<String> getUnavailableList() {
        return unavailableList;
    }
}
